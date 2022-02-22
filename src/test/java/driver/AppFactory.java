package driver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

import static io.appium.java_client.remote.MobileCapabilityType.*;
import static helpers.Configuration.*;

public class AppFactory {

    public static AppiumDriver<MobileElement> driver;

    public void androidDriverFactory() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(VERSION, ANDROID_VERSION);
        capabilities.setCapability(APP, CREDIT_PRIME_RO_ANDROID);
        capabilities.setCapability(DEVICE_NAME, PIXEL_XL);
        capabilities.setCapability(PLATFORM_NAME, ANDROID);
        capabilities.setCapability("reset", true);
        capabilities.setCapability("appPackage", roBundleID);
        capabilities.setCapability("appActivity", roAppMainActivity);

        try {
            driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void iOSDriverFactory() {
        // To create an object of Desired Capabilities
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(AUTOMATION_NAME, XCUITest);
        capabilities.setCapability(VERSION, IOS_VERSION);
        capabilities.setCapability(APP, CREDIT_PRIME_RO_IOS);
        capabilities.setCapability(DEVICE_NAME, IPHONE_7_PLUS);
        capabilities.setCapability(PLATFORM_NAME, IOS);
        capabilities.setCapability("reset", true);
        capabilities.setCapability("appPackage", roBundleID);
        capabilities.setCapability("appActivity", roAppMainActivity);

        try {
            driver = new IOSDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void quitDriver() {
        if (driver != null)
            driver.quit();
    }
}
