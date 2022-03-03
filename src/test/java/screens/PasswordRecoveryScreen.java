package screens;

import helpers.user.TestUser;
import io.appium.java_client.MobileBy;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;

import java.io.IOException;

public class PasswordRecoveryScreen extends BaseScreen {

    TestUser testUser = new TestUser();

    /**
     * Mobile Elements
     */
    By passwordRecoveryButton = MobileBy.xpath("//*[@content-desc='Ai uitat parola?']"),
            passwordRequestScreen = MobileBy.xpath("//*[@content-desc='Pentru a recupera parola utilizează același număr de telefon pe care îl folosești în contul tău web']"),
            passwordRequestButton = MobileBy.AccessibilityId("TRIMITE PAROLA"),
            SMSSuccessMessage = MobileBy.AccessibilityId("Ți s-a trimis un SMS cu o parolă temporară!"),
            gotItButton = MobileBy.AccessibilityId("BINE, AM ÎNȚELES"),
            tempPasswordScreen = MobileBy.AccessibilityId("Introdu numărul de telefon și parola temporară"),
            passwordEditField = MobileBy.xpath("//android.widget.EditText[2]"),
            newPasswordScreen = MobileBy.AccessibilityId("Crează o nouă parolă"),
            confirmPasswordScreen = MobileBy.AccessibilityId("Confirmă parola nouă");

    public void verifyPasswordRecoveryScreen() {
        click(passwordRecoveryButton);
        waitFor(passwordRequestScreen);
    }

    public void enterPhoneNumber(String username) throws IOException, ParseException {
        click(editField);
        sendKeysAction(testUser.getPhone(username));
        click(passwordRequestButton);
    }

    public void verifySMSIsSentMessage() {
        waitFor(SMSSuccessMessage);
        click(gotItButton);
    }

    public void enterTempPassword(String username) throws ParseException, IOException {
        waitFor(tempPasswordScreen);
        click(passwordEditField);
        sendKeysAction(testUser.getTemporaryPassword(username));
        click(proceedButton);
    }

    public void chooseNewPassword(String username) throws IOException, ParseException {
        waitFor(newPasswordScreen);
        click(editField);
        sendKeysAction(testUser.getPassword(username));
        click(proceedButton);
    }

    public void confirmNewPassword(String username) throws IOException, ParseException {
        waitFor(confirmPasswordScreen);
        click(editField);
        sendKeysAction(testUser.getPassword(username));
        click(proceedButton);
    }
}
