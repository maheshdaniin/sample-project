package sample.project.domain.component.ui.pages;

import net.serenitybdd.core.Serenity;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sample.project.domain.component.accessibility.AccessibilityWrapper;

import java.util.List;
import java.util.stream.Collectors;

public class GovUkSearchResultPage extends GovHomePage {

    private static final Logger LOGGER = LoggerFactory.getLogger(GovUkSearchResultPage.class);

    //WebDriver driver;

    @FindBy(xpath = "//*[@id='js-results']//ul")
    List<WebElement> elementresults2;
    @FindBy(xpath = "//ul[@class='gem-c-document-list gem-c-document-list--equal-item-spacing govuk-!-margin-bottom-5']/li")
    List<WebElement> elementresults;

    public boolean hasAtLeastOneMatchingSearchResult(String expectedResultTerm) {
        List<WebElement> elementsWithMatchingTerm;
        elementsWithMatchingTerm = elementresults.stream().filter(e -> e.getText().toLowerCase().contains(expectedResultTerm.toLowerCase())).collect(Collectors.toList());
        final List<WebElement> elementsWithMatchingTerm2 = elementresults2.stream().filter(e -> !(e.getText().toLowerCase().contains(expectedResultTerm.toLowerCase()))).collect(Collectors.toList());
        return !elementsWithMatchingTerm.isEmpty() || !elementsWithMatchingTerm2.isEmpty();
    }

    public void verifyIsOnSearchResultPage() {
        LOGGER.info("verify user is on search result page");
        String originalSearchTerm = Serenity.sessionVariableCalled("searchTerm").toString();
        Assert.assertTrue(getDriver().getCurrentUrl().contains("keywords=" + originalSearchTerm));
    }


    public void runAccessibilityChecks(){

        AccessibilityWrapper.checkPageForAccessibility(getDriver());
    }
}
