package app;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.URL;


public class ConfigurationManager {

    private static final Logger log = LoggerFactory.getLogger(ConfigurationManager.class);

    PropertiesConfiguration configuration;

    public ConfigurationManager() {
        String env = getEnvironment();
        System.out.println("Reading config for environment: " + env);

        URL resource = this.getClass().getClassLoader().getResource("config.properties");

        try {
            configuration = new PropertiesConfiguration(resource);
        } catch (ConfigurationException e) {
            log.error("Failed to load configuration file", e);
            System.exit(-1);
        }
    }

    public String getWebappRoot(){
        String baseDir = new File("").getAbsolutePath();
        return "file:///" + baseDir + configuration.getString("webapp.dir");
    }

    public String getEnvironment(){
        String env = System.getProperty("env", null);
        if (env == null) throw new RuntimeException("Environment has not been correctly specified: -Denv=dev");
        return env;
    }
}
