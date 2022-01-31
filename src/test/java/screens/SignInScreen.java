package screens;

import io.appium.java_client.MobileBy;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import java.io.IOException;

public class SignInScreen extends BaseScreen {
    /**
     * Mobile Elements
     */
    By welcomeMessage = MobileBy.xpath("//*[@content-desc='Salut!']"),
            signInButton = MobileBy.xpath("//*[@content-desc='LOGARE']"),
            termsAndConditions = MobileBy.xpath("//*[@content-desc='Termeni și condiții']"),
            acceptButton = MobileBy.xpath("//*[@content-desc='ACCEPT']"),
            phoneNumber = MobileBy.xpath("//*[contains(@text,'Număr de telefon')]"),
            passwordField = MobileBy.xpath("//*[contains(@text,'Parola')]"),
            proceedButton = MobileBy.xpath("//*[@content-desc='URMĂTORUL']"),
            setPIN = MobileBy.xpath("//*[@content-desc='0']"),
            confirmPINMessage = MobileBy.xpath("//*[@content-desc='Confirmă codul PIN']"),
            skipButton = MobileBy.xpath("//*[@content-desc='MAI DEPARTE']");

    public void verifySignInScreen() {
        waitFor(welcomeMessage);
        click(signInButton);
    }

    public void verifyTermsAndConditions() {
        waitFor(termsAndConditions);
        click(acceptButton);
    }

    public void insertCredentials(String username) throws IOException, ParseException {
        getJsonData(username);
        click(phoneNumber);
        sendKeysAction(phone);
        click(passwordField);
        sendKeysAction(password);
        click(proceedButton);
        waitForInvisibilityOfElement(proceedButton);
        }

    public void setAndConfirmPIN() {
        waitFor(setPIN);
        insertPIN(driver.findElement(By.xpath("//*[@content-desc='0']")));
        waitFor(confirmPINMessage);
        insertPIN(driver.findElement(By.xpath("//*[@content-desc='0']")));
    }

    public void skipFaceTouchID() {
        driver.navigate().back();
        waitFor(skipButton);
        click(skipButton);
    }
}
