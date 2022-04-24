package stepDefs;

import driver.AppFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import io.qameta.allure.model.Status;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hooks extends AppFactory {

    @Before(value = "@Android")
    public void setUpAndroid() {
        androidDriverFactory();
    }

    @Before(value = "@IOS")
    public void setUpIOS() {
        iOSDriverFactory();
    }

    @After
    public void takeScreenshotIfScenarioIsFailed(Scenario scenario) {
        if (scenario.isFailed()) {
            try {
                byte[] screenshot = ((TakesScreenshot) driver)
                        .getScreenshotAs(OutputType.BYTES);
                Allure.getLifecycle()
                        .addAttachment(
                                "Screenshot",
                                "image/png",
                                "png",
                                screenshot);
            } catch (Exception error) {
                Allure.step("Test is failed", Status.FAILED);
                Allure.getLifecycle().updateStep(stepResult -> stepResult.setStatus(Status.FAILED));
                error.printStackTrace();
            }
        }
    }
}