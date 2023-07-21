package sample.project.domain.component.accessibility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class BrowserListener implements WebDriverListener {
    private static final String BLANK_TAB = "about:blank";
    private final WebDriver driver;

    public BrowserListener(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public void afterGet(WebDriver driver, String url) {
        if (!driver.getCurrentUrl().contains(BLANK_TAB)) {
//            AccessibilityWrapper.checkPageForAccessibility(driver);
        }
    }

    @Override
    public void afterClick(WebElement element) {
        if (!driver.getCurrentUrl().contains(BLANK_TAB)) {
//            AccessibilityWrapper.checkPageForAccessibility(driver);
        }
    }

    @Override
    public void afterBack(WebDriver.Navigation navigation) {
        if (!driver.getCurrentUrl().contains(BLANK_TAB)) {
//            AccessibilityWrapper.checkPageForAccessibility(driver);
        }
    }

    @Override
    public void afterForward(WebDriver.Navigation navigation) {
        if (!driver.getCurrentUrl().contains(BLANK_TAB)) {
//            AccessibilityWrapper.checkPageForAccessibility(driver);
        }
    }

    @Override
    public void onError(Object target, Method method, Object[] args, InvocationTargetException e) {
        if (!driver.getCurrentUrl().contains(BLANK_TAB)) {
//            AccessibilityWrapper.checkPageForAccessibility(driver);
        }

    }
}
