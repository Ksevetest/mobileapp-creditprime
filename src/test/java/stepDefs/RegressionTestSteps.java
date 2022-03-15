package stepDefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.simple.parser.ParseException;
import screens.MyCreditScreen;
import screens.MyProfileScreen;
import screens.SignInScreen;

import java.io.IOException;

public class RegressionTestSteps {
    SignInScreen signInScreen = new SignInScreen();
    MyCreditScreen myCreditScreen = new MyCreditScreen();
    MyProfileScreen myProfileScreen = new MyProfileScreen();

    @Given("client opens creditPrime mobile application")
    public void clientOpensCreditPrimeMobileApplication() {
        signInScreen.verifySignInScreen();
    }

    @When("client accepts terms and conditions")
    public void clientAcceptsTermsAndConditions() {
        signInScreen.verifyTermsAndConditions();
    }

    @And("inserts {word} valid credentials")
    public void insertsValidCredentials(String username) throws IOException, ParseException {
        signInScreen.insertCredentials(username);
    }

    @And("sets up and confirms PIN code")
    public void setsUpAndConfirmsPINCode() {
        signInScreen.setAndConfirmPIN();
        signInScreen.skipFaceTouchID();
        signInScreen.skipCongratulationMessage();
    }

    @Then("client sees My Credit page")
    public void clientSeesMyCreditPage() {
        myCreditScreen.verifyMyCreditScreen();
    }

    @And("opens My Profile page")
    public void opensMyProfilePage() {
        myProfileScreen.openMyProfileScreen();
    }

    @And("logs out from the mobile app")
    public void logsOutFromTheMobileApp() {
        myProfileScreen.logout();
    }

    @Then("client sees My Credit page without Active loan")
    public void clientSeesMyCreditPageWithoutActiveLoan() {
        myCreditScreen.verifyMyCreditScreenClientWithoutLoan();
    }

    @Then("client sees My Credit page with SOLD loan information")
    public void clientSeesMyCreditPageWithSOLDLoanInformation() {
        myCreditScreen.verifyMyCreditScreenClientWithSoldLoan();
    }

    @Given("client logs in with {word} credentials")
    public void clientLogsInWithTESTSVETLANACredentials(String username) throws IOException, ParseException {
        signInScreen.verifySignInScreen();
        signInScreen.verifyTermsAndConditions();
        signInScreen.insertCredentials(username);
        signInScreen.setAndConfirmPIN();
        signInScreen.skipFaceTouchID();
        signInScreen.skipCongratulationMessage();
    }

    @When("client reopens the creditPrime mobile application")
    public void clientReopensTheCreditPrimeMobileApplication() {
        signInScreen.reopenMobileApp();
    }

    @And("client presses forgot PIN code")
    public void clientPressesForgotPINCode() {
        signInScreen.forgotPIN();
    }

    @Then("client sees initial welcome screen")
    public void clientSeesInitialWelcomeScreen() {
        signInScreen.verifySignInScreen();
    }

    @And("client presses incorrect PIN code {int} times in the row")
    public void clientPressesIncorrectPINCodeTimesInTheRow(int badLoginCount) {
        for (int i = 0; i < badLoginCount; i++) {
            signInScreen.setIncorrectPIN();
        }
    }

    @Then("{word} sees My Credit page with ACTIVE loan and principal_open {int}")
    public void clientSeesMyCreditPageWithACTIVELoanAndPrincipal_open(String username, Integer principalOpen) throws IOException, ParseException {
        myCreditScreen.checkUsedAmount(username, principalOpen);
        myCreditScreen.checkUnpaidAmount(username, principalOpen);
    }

    @Then("{word} sees My Credit page with ACTIVE loan and principal_open greater than {int}")
    public void clientSeesMyCreditPageWithACTIVELoanAndPrincipal_openGreaterThanZero(String username, Integer principalOpen) throws IOException, ParseException {
        myCreditScreen.checkThatUsedAmountGreaterThanZero(username, principalOpen);
    }

    @Then("{word} sees My Credit page with LATE loan warning message")
    public void clientSeesMyCreditPageWithLATELoanWarningMessage(String username) throws IOException, ParseException {
        myCreditScreen.checkLateLoan(username);
    }

    @Then("{word} sees My Credit page with LATE loan {int}+ dpd message")
    public void clientSeesMyCreditPageWithDpdMessage(String username, Integer dpd) throws IOException, ParseException {
        myCreditScreen.check91DPDLoan(username, dpd);
    }
}