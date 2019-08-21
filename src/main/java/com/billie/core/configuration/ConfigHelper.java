package com.billie.core.configuration;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class ConfigHelper {
    private static final Config CONFIG = ConfigFactory.load();

    public static String getBaseUri() {
        return CONFIG.getString("baseApiUri");
    }
}
