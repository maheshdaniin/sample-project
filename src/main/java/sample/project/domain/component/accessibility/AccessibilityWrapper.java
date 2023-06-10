package sample.project.domain.component.accessibility;

import com.deque.html.axecore.args.AxeRunOnlyOptions;
import com.deque.html.axecore.args.AxeRunOptions;
import com.deque.html.axecore.results.Results;
import com.deque.html.axecore.results.Rule;
import com.deque.html.axecore.selenium.AxeBuilder;
import lombok.experimental.UtilityClass;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@UtilityClass
public class AccessibilityWrapper {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccessibilityWrapper.class);
    private static final List<String> DEFAULT_AXE_STANDARDS = List.of("wcag2a", "wcag2aa", "wcag2aaa", "wcag21aa", "best-practice");
//    private static final String DEFAULT_AXE_REPORTING_LEVEL = "VIOLATION";

    public static void checkPageForAccessibility(WebDriver driver) {

        AxeRunOnlyOptions runOnlyOptions = new AxeRunOnlyOptions();
        runOnlyOptions.setType("tag");
        runOnlyOptions.setValues(DEFAULT_AXE_STANDARDS);
        AxeRunOptions options = new AxeRunOptions();
        options.setRunOnly(runOnlyOptions);
        AxeBuilder axe = new AxeBuilder().withOptions(options);
        Results result = axe.analyze(driver);
        List<Rule> axeViolations = result.getViolations();

        // axe violations from this evaluation
        if (axeViolations == null) {
            axeViolations = List.of();
        }

        for (Rule rule : axeViolations) {
            LOGGER.info("Rule: {} - Desc: {}", rule.getId(), rule.getHelp());
        }
    }
}

