package com.roi.goliath.device;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;

import javax.enterprise.context.ApplicationScoped;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

@ApplicationScoped
public class DeviceLibraryProxyProvider {

    private static final String BASE_PACKAGE_NAME = "com.goliath.";

    private static final String JAR_EXT = ".jar";

    private static final int CLASS_EXT_LENGTH = ".class".length();

    private final Map<String, DeviceLibraryProxy> libraryProxyCache = new HashMap<>();

    public DeviceLibraryProxy findLibraryByName(String name) {
        // populating library cache to avoid class loading conflicts on next invokations
        if (!libraryProxyCache.containsKey(name)) {
            libraryProxyCache.put(name, loadLibraryFromJar(name));
        }
        return libraryProxyCache.get(name);
    }


    private DeviceLibraryProxy loadLibraryFromJar(String name) {
        //we know it should be set on a config file, but just for the prototype
        //purposes we hardcoded it here, this decision was documented.
        String pathToJar = "D:/temp/" + name + JAR_EXT;
        
        if (!new File(pathToJar).exists()) {
            throw new IllegalArgumentException(String.format("Library [%s] is not found!", name));
        }

        URL[] urls = {createUrl("jar:file:" + pathToJar + "!/")};
        // url classloader that loads classes by url
        URLClassLoader cl = URLClassLoader.newInstance(urls, getClass().getClassLoader());

        JarFile jar = openJar(pathToJar);
        Class mainClass = loadClassFromJar(jar, cl, BASE_PACKAGE_NAME + name);

        safeClose(cl);
        safeClose(jar);
        validateLibraryMethod(mainClass);
        return new DeviceLibraryProxy(ClassUtils.newInstance(mainClass));
    }

    private Class loadClassFromJar(JarFile file, URLClassLoader cl, String mainClassName) {
        Class mainClass = null;
        Enumeration<JarEntry> e = file.entries();
        while (e.hasMoreElements()) {
            JarEntry je = e.nextElement();
            if (je.isDirectory() || !je.getName().endsWith(".class")) {
                continue;
            }
            String className = je.getName().substring(0, je.getName().length() - CLASS_EXT_LENGTH);
            className = className.replace('/', '.');

            Class c = ClassUtils.loadClass(className, cl);
            if (className.equals(mainClassName)) {
                mainClass = c;
            }
        }
        return mainClass;
    }

    private void safeClose(Closeable c) {
        try {
            c.close();
        } catch (IOException ex) {
            throw new IllegalStateException(ex);
        }
    }

    private URL createUrl(String path) {
        try {
            return new URL(path);
        } catch (MalformedURLException ex) {
            throw new IllegalArgumentException(ex);
        }
    }

    private JarFile openJar(String path) {
        try {
            return new JarFile(path);
        } catch (IOException ex) {
            throw new IllegalArgumentException(ex);
        }
    }


    private void validateLibraryMethod(Class claz) {
        ClassUtils.getMethod(claz,
                DeviceLibraryProxy.DEVICE_LIBRARY_EXEC_METHOD,
                Map.class);
    }

}
