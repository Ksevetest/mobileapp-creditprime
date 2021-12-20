package stepDefs;

import config.AppFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;

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
    public void quitApp() {
        quitDriver();
    }
}
