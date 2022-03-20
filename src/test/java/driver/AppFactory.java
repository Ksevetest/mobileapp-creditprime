package driver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


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
        capabilities.setCapability("appPackage", RO_BUNDLE_ID);
        capabilities.setCapability("appActivity", RO_APP_MAIN_ACTIVITY);

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
        capabilities.setCapability("appPackage", RO_BUNDLE_ID);
        capabilities.setCapability("appActivity", RO_APP_MAIN_ACTIVITY);

        try {
            driver = new IOSDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            getScreenshot();
        }
        driver.quit();
    }

    public void getScreenshot() {
        File scrFile = driver.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File(DEFAULT_SCREENSHOT_FOLDER_PATH + "/" + getTimestamp() + ".png"));
        } catch (IOException exception) {
            System.out.println("Failed to create screenshot file");
            exception.printStackTrace();
        }
    }

    public String getTimestamp() {
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy_HH:mm:ss");
        return dateFormat.format(date);
    }
}
