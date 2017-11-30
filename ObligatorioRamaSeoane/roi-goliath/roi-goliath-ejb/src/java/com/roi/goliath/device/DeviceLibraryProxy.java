package com.roi.goliath.device;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class DeviceLibraryProxy {

    public static final String DEVICE_LIBRARY_EXEC_METHOD = "execute";

    private static final Pattern COMMAND_PATTERN = Pattern
            .compile("(?<method>[^\\(\\)\\s]+)(?:\\((?<arg>\\S+)\\))?");

    private static final SimpleDateFormat DATE_ARG_FORMAT
            = new SimpleDateFormat("yyyy-mm-dd HH:mm");

    private final Object libraryInstance;

    public DeviceLibraryProxy(Object instance) {
        this.libraryInstance = instance;
    }

    /**
     * Parses commands and runs them on library driver instance
     *
     * @param commands from the UI
     */
    public void executeCommands(List<String> commands) {
        Map<String, String> map = new HashMap<>();
        commands.forEach((command) -> {
            Map.Entry<String, String> entry = parseCommand(command);
            map.put(entry.getKey(), entry.getValue());
        });
        runCommands(map);
    }

    private Map.Entry<String, String> parseCommand(String command) {
        // Parse input command with REGEXP
        String method = command.split("\\(")[0];
        String value = command.split("\\(")[1].replace(')', ' ').trim();
        return new AbstractMap.SimpleEntry<String, String>(method, value);
    }

    /**
     * Runs commands on libraryInstance
     *
     * @param map
     */
    private void runCommands(Map<String, String> map) {
        // fetch method from class definition
        Method m = ClassUtils.getMethod(libraryInstance.getClass(),
                DEVICE_LIBRARY_EXEC_METHOD, Map.class);
        // invoke method of the library instance with argument "deviceCommand"        ;
        ClassUtils.invoke(m, libraryInstance, map);
    }

}
