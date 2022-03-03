package screens;

import helpers.user.TestUser;
import io.appium.java_client.MobileBy;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;

import java.io.IOException;

public class MyProfileScreen extends BaseScreen {

    TestUser testUser = new TestUser();

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
            passwordIsChangedMessage = MobileBy.AccessibilityId("Parola ta a fost schimbată"),
            myProfileButton = MobileBy.xpath("//android.widget.ImageView[contains(@content-desc, 'Profilul meu')]"),
            confirmNewPINMessage = MobileBy.xpath("//*[@content-desc='Confirmă noul cod PIN']");

    public void openMyProfileScreen() {
        waitFor(myProfileButton);
        click(myProfileButton);
    }

    public void logout() {
        scrollTo(700, 1100, 700, 300);
        waitFor(logoutButton);
        click(logoutButton);
        waitFor(logoutPopUpButton);
        click(logoutPopUpButton);
        waitFor(welcomeMessage);
    }

    public void logoutAfterPasswordChange() {
        waitForInvisibilityOfElement(passwordIsChangedMessage);
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
        sendKeysAction(testUser.getPassword(username));
        click(proceedButton);
        waitFor(newPasswordScreen);
        click(editPasswordField);
        sendKeysAction(testUser.getNewPassword(username));
        click(proceedButton);
    }

    public void changeToOldPassword(String username) throws IOException, ParseException {
        click(changePasswordButton);
        click(editField);
        sendKeysAction(testUser.getNewPassword(username));
        click(proceedButton);
        waitFor(newPasswordScreen);
        click(editField);
        sendKeysAction(testUser.getPassword(username));
        click(proceedButton);
    }

    public void setAndConfirmNewPIN() {
        waitFor(setPIN);
        insertPIN(By.xpath("//*[@content-desc='0']"));
        waitFor(confirmNewPINMessage);
        insertPIN(By.xpath("//*[@content-desc='0']"));
    }
}
