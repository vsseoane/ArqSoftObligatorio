package com.roi.goliath.device;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 *
 * @author Alejandro
 */
public final class ClassUtils {

    private ClassUtils() {
    }

    public static Class loadClass(String name, ClassLoader cl) {
        try {
            return Class.forName(name, true, cl);
        } catch (ClassNotFoundException ex) {
            throw new IllegalStateException(ex);
        }
    }

    public static Object newInstance(Class c) {
        try {
            return c.newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            throw new IllegalStateException(ex);
        }
    }

    public static Object newInstance(Constructor<?> c, Object... args) {
        try {
            return c.newInstance(args);
        } catch (InstantiationException | IllegalAccessException
                | InvocationTargetException ex) {
            throw new IllegalStateException(ex);
        }
    }

    public static Method getMethod(Class claz, String name,
            final Class... arguments) {
        try {
            Method m = claz.getMethod(name, arguments);
            m.setAccessible(true);
            return m;
        } catch (NoSuchMethodException | SecurityException ex) {
            throw new IllegalArgumentException(String
                    .format("Method [%s] does not exist", name));
        }
    }

    public static Object invoke(Method m, Object instance, Object... args) {
        try {
            return m.invoke(instance, args);
        } catch (IllegalAccessException | IllegalArgumentException
                | InvocationTargetException ex) {
            throw new IllegalArgumentException(ex);
        }
    }

    public static Constructor<?> getContructor(Class c, Class... argTypes) {
        try {
            return c.getDeclaredConstructor(argTypes);
        } catch (NoSuchMethodException | SecurityException ex) {
            throw new IllegalArgumentException(ex);
        }
    }

}
