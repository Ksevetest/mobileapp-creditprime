package screens;

import helpers.JsonParser;
import io.appium.java_client.MobileBy;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;

import java.io.IOException;

public class PasswordRecoveryScreen extends BaseScreen {

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
        JsonParser.getJsonDataFromFile(username);
        click(editField);
        sendKeysAction(JsonParser.phone);
        click(passwordRequestButton);
    }

    public void verifySMSIsSentMessage() {
        waitFor(SMSSuccessMessage);
        click(gotItButton);
    }

    public void enterTempPassword(String username) throws ParseException, IOException {
        waitFor(tempPasswordScreen);
        JsonParser.getTemporaryPassword(username);
        click(passwordEditField);
        sendKeysAction(JsonParser.temporaryPassword);
        click(proceedButton);
    }

    public void chooseNewPassword(String username) throws IOException, ParseException {
        waitFor(newPasswordScreen);
        click(editField);
        JsonParser.getJsonDataFromFile(username);
        sendKeysAction(JsonParser.password);
        click(proceedButton);
    }

    public void confirmNewPassword() {
        waitFor(confirmPasswordScreen);
        click(editField);
        sendKeysAction(JsonParser.password);
        click(proceedButton);
    }
}
