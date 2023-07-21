package sample.project.domain.component.ui.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.Assert.assertTrue;

@DefaultUrl("https://www.google.com/")
public class GoogleHomePage extends PageObject {


    @FindBy(xpath = "//*[@id=\"APjFqb\"]")
    WebElement searchTextArea;

    @FindBy(xpath = "/html/body/div[1]/div[3]/form/div[1]/div[1]/div[4]/center/input[1]")
    WebElement googleSearchButton;

    String searchText="nothing";


    public void searchForAtext(String text) {
        searchText = text;
        searchTextArea.sendKeys(text);
        googleSearchButton.click();
    }

    public void validateOnSearchResult() {
       assertTrue(getDriver().getCurrentUrl().contains(searchText));

    }

    public void clickAcceptAllOnPopUp() {
        final List<WebElement> acceptAllElements = getDriver().findElements(By.xpath("/html/body/div[2]/div[3]/div[3]/span/div/div/div/div[3]/div[1]/button[2]/div"));

        if (!acceptAllElements.isEmpty()) {
            acceptAllElements.get(0).click();
        }

    }
}
