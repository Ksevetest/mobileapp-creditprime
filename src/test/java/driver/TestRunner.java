package driver;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "io.qameta.allure.cucumber6jvm.AllureCucumber6Jvm"},
        features = "src/test/resources/features",
        glue = "stepDefs",
        tags = "@regression or @smoke"
)

public class TestRunner {

}