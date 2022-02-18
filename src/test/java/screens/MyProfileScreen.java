package screens;

import helpers.JsonParser;
import io.appium.java_client.MobileBy;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;

import java.io.IOException;

public class MyProfileScreen extends BaseScreen {
    /**
     * Mobile Elements
     */
    By logoutButton = MobileBy.AccessibilityId("Ieșire"),
            logoutPopUpButton = MobileBy.xpath("//android.widget.Button[@content-desc='Ieșire']"),
            welcomeMessage = MobileBy.xpath("//*[@content-desc='Salut!']"),
            changePasswordButton = MobileBy.AccessibilityId("Schimbă parola"),
            currentPasswordScreen = MobileBy.xpath("//android.view.View/android.view.View[contains(@content-desc,'Introdu parola curentă')]"),
            editPasswordField = MobileBy.xpath("//android.widget.EditText[contains(@text,'Parola')]"),
            newPasswordScreen = MobileBy.xpath("//android.view.View/android.view.View[contains(@content-desc,'Introdu parola nouă.')]"),
            PasswordIsChangedMessage = MobileBy.AccessibilityId("Parola ta a fost schimbată"),
            confirmNewPINMessage = MobileBy.xpath("//*[@content-desc='Confirmă noul cod PIN']");

    public void verifyMyProfileScreen() {
        scrollTo(700, 1100, 700, 300);
    }

    public void logout() {
        waitFor(logoutButton);
        click(logoutButton);
        waitFor(logoutPopUpButton);
        click(logoutPopUpButton);
        waitFor(welcomeMessage);
    }

    public void logoutAfterPasswordChange() {
        waitForInvisibilityOfElement(PasswordIsChangedMessage);
        scrollTo(700, 1100, 700, 300);
        waitFor(logoutButton);
        click(logoutButton);
        waitFor(logoutPopUpButton);
        click(logoutPopUpButton);
        waitFor(welcomeMessage);
    }

    public void changePassword(String username) throws IOException, ParseException {
        click(changePasswordButton);
        waitFor(currentPasswordScreen);
        click(editPasswordField);
        JsonParser.getJsonDataFromFile(username);
        sendKeysAction(JsonParser.password);
        click(proceedButton);
        waitFor(newPasswordScreen);
        click(editPasswordField);
        sendKeysAction(JsonParser.newPassword);
        click(proceedButton);
    }

    public void changeToOldPassword(String username) throws IOException, ParseException {
        click(changePasswordButton);
        click(editField);
        JsonParser.getJsonDataFromFile(username);
        sendKeysAction(JsonParser.newPassword);
        click(proceedButton);
        waitFor(newPasswordScreen);
        click(editField);
        sendKeysAction(JsonParser.password);
        click(proceedButton);
    }

    public void setAndConfirmNewPIN() {
        waitFor(setPIN);
        insertPIN(By.xpath("//*[@content-desc='0']"));
        waitFor(confirmNewPINMessage);
        insertPIN(By.xpath("//*[@content-desc='0']"));
    }
}
