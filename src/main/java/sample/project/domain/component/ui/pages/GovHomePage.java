package sample.project.domain.component.ui.pages;

import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@DefaultUrl("https://www.gov.uk/")
public class GovHomePage extends PageObject {

    @FindBy(xpath = "(//input[contains(@id,\"search-main-\")])[2]")
    WebElement searchMain;

    @FindBy(xpath = "//button[@class=\"gem-c-search__submit\"] [@data-track-category=\"homepageClicked\"]")
    WebElement searchSubmit;

    public void Search(String term) {
        searchMain.sendKeys(term);
        searchSubmit.click();
    }

}
