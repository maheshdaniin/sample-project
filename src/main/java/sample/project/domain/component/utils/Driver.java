package sample.project.domain.component.utils;

import net.thucydides.core.webdriver.DriverSource;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sample.project.domain.component.accessibility.BrowserListener;

import java.util.Optional;
import java.util.logging.Level;


public class Driver implements DriverSource {

    private static final Logger LOGGER = LoggerFactory.getLogger(Driver.class);

    private String os = System.getProperty("os.name");
    private String browser = Configuration.get("webdriver.browser");

    @Override
    public WebDriver newDriver() {
        new LoggingPreferences().enable(LogType.PERFORMANCE, Level.ALL);

        LOGGER.info(String.format("Driver Current Browser: %s", browser));
        WebDriver driver = getDriver();
        if (driver == null) {
            LOGGER.error("Unable to create driver '{}' on '{}'", browser, this.os);
            return null;
        }
        EventFiringDecorator eventFiringWebDriver = new EventFiringDecorator(new BrowserListener(driver));
        return eventFiringWebDriver.decorate(driver);
    }

    public WebDriver getDriver() {
        LOGGER.info("Creating default local Driver '{}' on '{}'", browser, this.os);

        switch (browser) {
            case "firefox":
                return new FirefoxDriver(getDefaultFirefoxOptions());
            case "chrome":
                return new ChromeDriver(getDefaultChromeOptions());
            default:
                return null;
        }
    }


    /**
     * Returns FirefoxOptions used to configure basic settings
     * shared across all driver instances.
     */
    private FirefoxOptions getDefaultFirefoxOptions() {
        FirefoxOptions options = new FirefoxOptions();
        options.setHeadless(true);
        options.setAcceptInsecureCerts(false);
        options.addArguments("-allow-origins");
        options.setCapability("webSocketUrl", true);
//        options.addArguments("-remote-allow-origins");
        options.addArguments("-no-sandbox");
        options.addArguments("-disable-dev-shm-usage");
        options.addArguments("-allow-host=localhost");
        if ("true".equalsIgnoreCase(Configuration.get("ignore_certificate_errors"))) {
            options.setAcceptInsecureCerts(true);
            LOGGER.info("Test is ignoring invalid certificates");
        }

        return options;
    }

    private ChromeOptions getDefaultChromeOptions() {
        System.setProperty("webdriver.chrome.logfile", "/tmp/chromedriver.log");
        System.setProperty("webdriver.chrome.verboseLogging", "true");
        ChromeOptions options = new ChromeOptions();
        options.addArguments(new String[]{"--disable-extensions"});
        boolean headless = Boolean.parseBoolean(System.getProperty("headless", "false"));
        options.addArguments(new String[]{"--remote-allow-origins=*"});
        options.addArguments(new String[]{"--no-first-run"});
        options.addArguments(new String[]{"--window-size=1920,1080"});
        if (!headless && !this.os.contains("Linux")) {
            if (this.os.contains("Windows")) {
                options.addArguments(new String[]{"start-maximized"});
            }
        } else {
            options.addArguments(new String[]{"--headless=new"});
            options.addArguments(new String[]{"--no-sandbox"});
            options.addArguments(new String[]{"--disable-gpu"});
            options.addArguments(new String[]{"--disable-dev-shm-usage"});
        }

        if ("chrome-zapProxy".equals(this.browser)) {
            options.addArguments(new String[]{"--ignore-certificate-errors"});
            options.addArguments(new String[]{"--disable-dev-shm-usage"});
            options.setPageLoadStrategy(PageLoadStrategy.NONE);
        }

        if (((String) Optional.ofNullable(Configuration.get("ignore_certificate_errors")).orElse("false")).equals("true")) {
            options.addArguments(new String[]{"--ignore-certificate-errors"});
            LOGGER.info("Test is ignoring invalid certificates");
        }
        return options;
    }


    @Override
    public boolean takesScreenshots() {
        return true;
    }

}
