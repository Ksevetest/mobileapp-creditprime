package screens;

import config.AppFactory;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileReader;
import java.io.IOException;

public class BaseScreen extends AppFactory {
    String name, phone, password;
    JSONParser parser = new JSONParser();

    public void waitFor(By element) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    public void waitForInvisibilityOfElement(By element) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
    }

    public void click(By element) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        driver.findElement(element).click();
    }

    public void sendKeysAction(String inputText) {
        Actions action = new Actions(driver);
        action.sendKeys(inputText).perform();
    }

    public void insertPIN(MobileElement element) {
        Actions action = new Actions(driver);
        action.click(element).click(element).click(element).click(element).perform();
    }

    public void tapByCoordinates(int x, int y) {
        TouchAction<?> tapByCoordinates = new TouchAction<>(driver);
        tapByCoordinates.tap(PointOption.point(x, y)).release().perform();
    }

    public void scrollTo(int fromX, int fromY, int toX, int toY) {
        TouchAction<?> scrollTo = new TouchAction<>(driver);
        scrollTo.press(PointOption.point(fromX, fromY)).moveTo(PointOption.point(toX, toY)).release().perform();
    }

    public void getJsonData(String username) throws IOException, ParseException {
        Object obj = parser.parse(new FileReader("src/test/resources/features/predefinedClients.json"));
        JSONObject jsonObject = (JSONObject) obj;
        JSONArray users = (JSONArray) jsonObject.get(username);
        for (Object testData : users) {
            JSONObject jsonData = (JSONObject) testData;
            name = (String) jsonData.get("name");
            phone = (String) jsonData.get("phone");
            password = (String) jsonData.get("password");
        }
    }
}
