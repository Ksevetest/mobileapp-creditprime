package stepDefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.simple.parser.ParseException;
import screens.MyCreditScreen;
import screens.PasswordRecoveryScreen;
import screens.SignInScreen;

import java.io.IOException;

public class PasswordRecoverySteps {

    SignInScreen signInScreen = new SignInScreen();
    PasswordRecoveryScreen passwordRecoveryScreen = new PasswordRecoveryScreen();
    MyCreditScreen myCreditScreen = new MyCreditScreen();

    @Given("client goes to password recovery page")
    public void clientTESTPASSWORDGoesToPasswordRecoveryPage() {
        signInScreen.verifySignInScreen();
        signInScreen.verifyTermsAndConditions();
        passwordRecoveryScreen.verifyPasswordRecoveryScreen();
    }

    @And("{word} enters his phone number")
    public void entersHisPhoneNumber(String username) throws IOException, ParseException {
        passwordRecoveryScreen.enterPhoneNumber(username);
    }

    @And("sees success message that SMS is sent")
    public void seesSuccessMessageThatSMSIsSent() {
        passwordRecoveryScreen.verifySMSIsSentMessage();
    }

    @When("{word} enters temporary password that he received by SMS")
    public void clientEntersTemporaryPasswordThatHeReceivedBySMS(String username) throws ParseException, IOException {
        passwordRecoveryScreen.enterTempPassword(username);
    }

    @And("{word} chooses new password")
    public void choosesNewPassword(String username) throws IOException, ParseException {
        passwordRecoveryScreen.chooseNewPassword(username);
        passwordRecoveryScreen.confirmNewPassword();
    }

    @Then("client successfully logs in and sees My Credit page")
    public void clientSuccessfullyLogsInAndSeesMyCreditPage() {
        signInScreen.setAndConfirmPIN();
        signInScreen.skipFaceTouchID();
        myCreditScreen.verifyMyCreditScreenClientWithoutLoan();
    }
}
