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

public class SmokeTestSteps {
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
    }

    @Then("client sees My Credit page")
    public void clientSeesMyCreditPage() {
        myCreditScreen.verifyMyCreditScreen();
    }

    @And("opens My Profile page")
    public void opensMyProfilePage() {
        myProfileScreen.verifyMyProfileScreen();
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
}