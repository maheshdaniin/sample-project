package sample.project.domain.component.ui.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import net.thucydides.core.annotations.Steps;
import sample.project.domain.component.ui.stepLib.UiStepLib;

@Slf4j
public class StepDefinitions {

    @Steps
    UiStepLib uiStepLib;

    @Given("I use the {} browser")
    public void iUseTheBrowserBrowser(String browser) {
        uiStepLib.setBrowser(browser);
    }

    @Given("^I open Gov.uk home page$")
    public void iOpenGoogle() {
        uiStepLib.navigateToGovUkHomePage();
    }

    @When("^I search for \"([^\"]*)\"$")
    public void iSearchFor(String term) {
        uiStepLib.searchForTerm(term);
    }

    @Then("I am on search result page")
    public void iAmOnSearchResultPage() {
        uiStepLib.verifyUserIsOnSearchResultPage();
    }


    @Then("^I should find \"([^\"]*)\" related results$")
    public void iShouldFind(String expectedResultTerm) {
        uiStepLib.verifySearchTermInResult(expectedResultTerm);

    }


    @And("I run accessibility check")
    public void iRunAccessibilityCheck() {
        uiStepLib.checkAccessiblity();
    }
}
