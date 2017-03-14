package edu.bsuir.totema.model;

import java.util.ResourceBundle;

class DatabaseManager {

    static final String DRIVER_NAME = "driver";
    static final String USER = "user";
    static final String PASSWORD = "password";
    static final String URL = "url";
    static final String ENCODING = "characterEncoding";
    static final String AUTORECONNECT = "autoReconnect";
    static final String USE_UNICODE = "useUnicode";

    private static final String RESOURCE_NAME = "database";
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle(RESOURCE_NAME);

    private DatabaseManager() {
    }

    static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
