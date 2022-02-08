package config;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

import static io.appium.java_client.remote.MobileCapabilityType.*;

public class AppFactory {

    public static AppiumDriver<MobileElement> driver;

    public void androidDriverFactory() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(VERSION, " 10");
        capabilities.setCapability(APP, "Users/creditPrime.apk");
        capabilities.setCapability(DEVICE_NAME, "Pixel XL");
        capabilities.setCapability(PLATFORM_NAME, "Android");
        capabilities.setCapability("reset", true);
        capabilities.setCapability("appPackage", "com.dyninno.mobileapp.romania");
        capabilities.setCapability("appActivity", "com.dyninno.mobileapp.romania.MainActivity");

        try {
            driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void iOSDriverFactory() {
        // To create an object of Desired Capabilities
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(AUTOMATION_NAME, "XCUITest");
        capabilities.setCapability(VERSION, " 15.0");
        capabilities.setCapability(APP, "Users/creditPrime.ipa");
        capabilities.setCapability(DEVICE_NAME, "iPhone 7 Plus");
        capabilities.setCapability(PLATFORM_NAME, "iOS");
        capabilities.setCapability("reset", true);
        capabilities.setCapability("appPackage", "com.dyninno.mobileapp.romania");
        capabilities.setCapability("appActivity", "com.dyninno.mobileapp.romania.MainActivity");

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
