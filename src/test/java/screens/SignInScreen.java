package screens;

import helpers.user.TestUser;
import io.appium.java_client.MobileBy;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;

import java.io.IOException;

public class SignInScreen extends BaseScreen {

    TestUser testUser = new TestUser();

    /**
     * Mobile Elements
     */
    By welcomeMessage = MobileBy.xpath("//*[@content-desc='Salut!']"),
            signInButton = MobileBy.xpath("//*[@content-desc='LOGARE']"),
            termsAndConditions = MobileBy.xpath("//*[@content-desc='Termeni și condiții']"),
            acceptButton = MobileBy.xpath("//*[@content-desc='ACCEPT']"),
            phoneNumber = MobileBy.xpath("//*[contains(@text,'Număr de telefon')]"),
            phoneNumberEdit = MobileBy.xpath("//android.widget.EditText[1]"),
            passwordField = MobileBy.xpath("//*[contains(@text,'Parola')]"),
            passwordFieldEdit = MobileBy.xpath("//android.widget.EditText[2]"),
            confirmPINMessage = MobileBy.xpath("//*[@content-desc='Confirmă codul PIN']"),
            skipButton = MobileBy.xpath("//*[@content-desc='MAI DEPARTE']"),
            insertPINMessage = MobileBy.xpath("//*[@content-desc='Introdu codul PIN']"),
            forgotPIN = MobileBy.xpath("//*[@content-desc='Am uitat PIN code']"),
            congratulationMessage = MobileBy.AccessibilityId("Felicitări!"),
            finishButton = MobileBy.AccessibilityId("Finalizare"),
            myCreditTab = MobileBy.xpath("//android.widget.ImageView[contains(@content-desc, 'Creditele mele')]");

    public void verifySignInScreen() {
        waitFor(welcomeMessage);
    }

    public void verifyTermsAndConditions() {
        click(signInButton);
        waitFor(termsAndConditions);
        click(acceptButton);
    }

    public void insertCredentials(String username) throws IOException, ParseException {
        click(phoneNumber);
        sendKeys(phoneNumberEdit, testUser.getPhone(username));
        click(passwordField);
        sendKeys(passwordFieldEdit, testUser.getPassword(username));
        click(proceedButton);
        waitForInvisibilityOfElement(proceedButton);
    }

    public void setAndConfirmPIN() {
        waitFor(setPIN);
        insertPIN(By.xpath("//*[@content-desc='0']"));
        waitFor(confirmPINMessage);
        insertPIN(By.xpath("//*[@content-desc='0']"));
    }

    public void skipFaceTouchID() {
        driver.navigate().back();
        waitFor(skipButton);
        click(skipButton);
    }

    public void skipCongratulationMessage() {
        try {
            driver.findElement(congratulationMessage);
            click(finishButton);
        } catch (Exception ignored) {
        }
        waitFor(myCreditTab);
    }

    public void reopenMobileApp() {
        reopenApplication();
    }

    public void forgotPIN() {
        waitFor(insertPINMessage);
        click(forgotPIN);
    }

    public void setIncorrectPIN() {
        waitFor(insertPINMessage);
        insertPIN(By.xpath("//*[@content-desc='1']"));
    }

    public void logInWithNewPassword(String username) throws IOException, ParseException {
        click(phoneNumber);
        sendKeysAction(testUser.getPhone(username));
        click(passwordField);
        sendKeysAction(testUser.getNewPassword(username));
        click(proceedButton);
        waitForInvisibilityOfElement(proceedButton);
    }
}
