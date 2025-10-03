package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

// Runner file for executing feature files with TestNG
// "features" → path of feature files
// "glue" → package where Step Definitions are present
// "plugin" → reporting formats
// "monochrome" → makes console output more readable

@CucumberOptions(
        features = "src/test/java/cucumber",    // path to your feature file folder
        glue = "taransinghlearning.Stepdefination",  // package where step defs are located
        monochrome = true,
        tags="@Regression",
        plugin = {
            "pretty",                          // readable console output
            "html:target/cucumber.html",       // HTML report
            "json:target/cucumber.json"        // JSON report for integrations
        }
)
// while using JUNIT we dont need to extend anything internally it will settle.
public class TestNGTestRunner extends AbstractTestNGCucumberTests {
}
