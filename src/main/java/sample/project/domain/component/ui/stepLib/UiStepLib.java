package sample.project.domain.component.ui.stepLib;

import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.StepEventBus;
import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sample.project.domain.component.ui.pages.GovHomePage;
import sample.project.domain.component.ui.pages.GovUkSearchResultPage;
import sample.project.domain.component.utils.Configuration;


public class UiStepLib {
    private GovHomePage govHomePage;
    private GovUkSearchResultPage searchResultPage;

    private static final Logger LOGGER = LoggerFactory.getLogger(UiStepLib.class);

    @Step
    public void navigateToGovUkHomePage() {
        govHomePage.open();
    }


    @Step
    public void searchForTerm(String term) {
        Serenity.setSessionVariable("searchTerm").to(term);
        govHomePage.Search(term);
    }

    @Step
    public void verifySearchTermInResult(String expectedResultTerm) {
        Assert.assertTrue("cannot find matching result", searchResultPage.hasAtLeastOneMatchingSearchResult(expectedResultTerm));
    }

    @Step
    public void verifyUserIsOnSearchResultPage() {
        searchResultPage.verifyIsOnSearchResultPage();
    }

    @Step
    public void setBrowser(String browser) {
        LOGGER.info("Setting browser to {}", browser);
        Configuration.setConfigurationProperty("webdriver.browser", browser);
        // update the test title
        String originalTitle = StepEventBus.getEventBus().getBaseStepListener().getCurrentTestOutcome().getTitle();
        String newTitle = String.format("(%s) - %s", StringUtils.capitalize(browser), originalTitle);
        StepEventBus.getEventBus().getBaseStepListener().getCurrentTestOutcome().setTitle(newTitle);
    }

}

