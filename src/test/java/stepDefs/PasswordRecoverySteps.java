package stepDefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.simple.parser.ParseException;
import screens.MyCreditScreen;
import screens.MyProfileScreen;
import screens.PasswordRecoveryScreen;
import screens.SignInScreen;

import java.io.IOException;

public class PasswordRecoverySteps {

    SignInScreen signInScreen = new SignInScreen();
    PasswordRecoveryScreen passwordRecoveryScreen = new PasswordRecoveryScreen();
    MyCreditScreen myCreditScreen = new MyCreditScreen();
    MyProfileScreen myProfileScreen = new MyProfileScreen();

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
        passwordRecoveryScreen.confirmNewPassword(username);
    }

    @Then("client successfully logs in and sees My Credit page")
    public void clientSuccessfullyLogsInAndSeesMyCreditPage() {
        signInScreen.setAndConfirmPIN();
        signInScreen.skipFaceTouchID();
        myCreditScreen.verifyMyCreditScreenClientWithoutLoan();
    }

    @And("goes to My Profile page")
    public void goesToMyProfilePage() {
        myCreditScreen.verifyMyCreditScreenWithoutLoan();
        myProfileScreen.openMyProfileScreen();
    }

    @When("{word} changes password")
    public void changesPassword(String username) throws IOException, ParseException {
        myProfileScreen.changePassword(username);
        myProfileScreen.setAndConfirmNewPIN();
    }

    @And("{word} new password is set")
    public void newPasswordIsSet(String username) throws IOException, ParseException {
        myProfileScreen.logoutAfterPasswordChange();
        signInScreen.verifySignInScreen();
        signInScreen.verifyTermsAndConditions();
        signInScreen.logInWithNewPassword(username);

    }

    @Then("{word} changes password to old one")
    public void clientChangesPasswordToOldOne(String username) throws IOException, ParseException {
        signInScreen.setAndConfirmPIN();
        signInScreen.skipFaceTouchID();
        myCreditScreen.verifyMyCreditScreenWithoutLoan();
        myProfileScreen.openMyProfileScreen();
        myProfileScreen.changeToOldPassword(username);
        myProfileScreen.setAndConfirmNewPIN();
    }

    @And("old {word} password is set")
    public void oldPasswordIsSet(String username) throws IOException, ParseException {
        myProfileScreen.logoutAfterPasswordChange();
        signInScreen.verifySignInScreen();
        signInScreen.verifyTermsAndConditions();
        signInScreen.insertCredentials(username);
    }
}
