package sample.project.domain.component.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Optional;
import java.util.Properties;

public class Configuration {

    private Configuration() {
        //explicit constructor
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(Configuration.class);
    private static Properties configuration;
    private static Properties tempCredentialProperties = new Properties();
    private static Properties localProperties = new Properties();


    static {
        initConfig();
    }

    public static String get(String string) {
        return configuration.getProperty(string);
    }

    public static String readCredentials(String propertyFile, String property) {
        Properties vaultConfig;
        tempCredentialProperties.clear();
        try (InputStream propertyFileInputStream = getClassPathResourceStream(propertyFile)) {
            loadProperties(tempCredentialProperties, propertyFileInputStream);
        } catch (IOException e) {
            LOGGER.error("Could not load credentials properties file" + e.getMessage());
            throw new RuntimeException("Could not load credentials properties file", e);
        } finally {
            vaultConfig = tempCredentialProperties;
        }
        return vaultConfig.getProperty(property);
    }

    public static void initConfig() {
        String chartName = Optional.ofNullable(System.getenv("CHART_NAME")).orElse("default");
        String propertyFile = "properties/" + chartName + ".properties";
        try (InputStream propertyFileInputStream = getClassPathResourceStream(propertyFile)) {
            loadProperties(localProperties, propertyFileInputStream);
        } catch (IOException e) {
            LOGGER.error("Could not load properties" + e.getMessage());
            throw new RuntimeException("Could not load properties", e);
        } finally {
            configuration = localProperties;
        }
    }

    private static void loadProperties(Properties props, InputStream inputStream) {
        try {
            props.load(new InputStreamReader(inputStream));
        } catch (IOException ioexception) {
            LOGGER.error(ioexception.getMessage());
        }
    }

    public static InputStream getClassPathResourceStream(String classpathResourceLoc) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream(classpathResourceLoc);
    }

    public static void setConfigurationProperty(String key, String value) {
        configuration.setProperty(key, value);
    }

}
