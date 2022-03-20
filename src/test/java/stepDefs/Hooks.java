package stepDefs;

import driver.AppFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

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
    public void quitApp(Scenario scenario) {
        tearDown(scenario);
    }
}
