package screens;

import config.AppFactory;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseScreen extends AppFactory {

    /**
     * Mobile Elements
     * common buttons
     */
    By proceedButton = MobileBy.AccessibilityId("URMĂTORUL"),
            editField = MobileBy.className("android.widget.EditText");

    public void waitFor(By element) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    public void waitForInvisibilityOfElement(By element) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
    }

    public void click(By element) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        driver.findElement(element).click();
    }

    public void sendKeysAction(String inputText) {
        Actions action = new Actions(driver);
        action.sendKeys(inputText).perform();
    }

    // works faster than loop
    public void insertPINByMultipleClick(MobileElement element) {
        Actions action = new Actions(driver);
        action.click(element).click(element).click(element).click(element).perform();
    }

    public void insertPIN(By element) {
        for (int i = 0; i < 4; i++) {
            driver.findElement(element).click();
        }
    }

    public void tapByCoordinates(int x, int y) {
        TouchAction<?> tapByCoordinates = new TouchAction<>(driver);
        tapByCoordinates.tap(PointOption.point(x, y)).release().perform();
    }

    public void scrollTo(int fromX, int fromY, int toX, int toY) {
        TouchAction<?> scrollTo = new TouchAction<>(driver);
        scrollTo.press(PointOption.point(fromX, fromY)).moveTo(PointOption.point(toX, toY)).release().perform();
    }

    public void reopenApplication() {
        // will not reset mobile data
        driver.terminateApp(bundleID);
        driver.activateApp(bundleID);
    }

    public void relaunchApplication() {
        // also resets mobile data
        driver.closeApp();
        driver.launchApp();
    }
}
