package testRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/Features/"},
        glue = "stepDefinitions",
        dryRun = false,
        monochrome = true,
        plugin = {"pretty","html:test-output"},
        tags = "@Sanity or @Regression"
)
public class TestRunner {
    //48MIN STOP
}