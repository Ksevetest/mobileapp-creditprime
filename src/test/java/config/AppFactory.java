package config;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;


public class AppFactory {

    public static AppiumDriver<MobileElement> driver;

    public void androidDriverFactory() {
        DesiredCapabilities capability = new DesiredCapabilities();
        capability.setCapability(CapabilityType.VERSION, " 10");
        capability.setCapability("app", "Users/creditPrime.apk");
        capability.setCapability("deviceName", "Pixel XL");
        capability.setCapability("platformName", "Android");
        capability.setCapability("reset", true);
        capability.setCapability("appPackage", "com.dyninno.mobileapp.romania");
        capability.setCapability("appActivity", "com.dyninno.mobileapp.romania.MainActivity");

        try {
            driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capability);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void iOSDriverFactory() {
        // To create an object of Desired Capabilities
        DesiredCapabilities capability = new DesiredCapabilities();
        capability.setCapability("automationName", "XCUITest");
        capability.setCapability(CapabilityType.VERSION, " 15.0");
        capability.setCapability("app", "Users/creditPrime.ipa");
        capability.setCapability("deviceName", "iPhone 7 Plus");
        capability.setCapability("platformName", "iOS");
        capability.setCapability("reset", true);
        capability.setCapability("appPackage", "com.dyninno.mobileapp.romania");
        capability.setCapability("appActivity", "com.dyninno.mobileapp.romania.MainActivity");

        try {
            driver = new IOSDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capability);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void quitDriver() {
        driver.quit();
    }
}
