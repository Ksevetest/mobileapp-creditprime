package screens;

import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;

import static org.assertj.core.api.Assertions.*;

public class MyCreditScreen extends BaseScreen {
    /**
     * Mobile Elements
     */
    By myCreditScreenTitle = MobileBy.xpath("//*[@content-desc='Informație credit']"),
            noActiveLoanMessage = MobileBy.AccessibilityId("În prezent nu aveți împrumuturi active"),
            requestNewLoanButton = MobileBy.AccessibilityId("APLICĂ PENTRU UN ÎMPRUMUT NOU"),
            soldLoanMessage = MobileBy.AccessibilityId("Creditul a fost vândut"),
            expandCreditInfo = MobileBy.xpath("//android.view.View[1]/android.widget.ImageView[2]"),
            totalUnpaid = MobileBy.AccessibilityId("Total sumă datorată");
    String usedAmount = ("//android.view.View[@content-desc='%s']");
    String unpaidAmount = ("//android.view.View[@content-desc='%s lei']");

    public void verifyMyCreditScreen() {
        waitFor(myCreditScreenTitle);
    }

    public void verifyMyCreditScreenWithoutLoan() {
        waitFor(noActiveLoanMessage);
    }

    public void verifyMyCreditScreenClientWithoutLoan() {
        waitFor(noActiveLoanMessage);
        waitFor(requestNewLoanButton);
        click(requestNewLoanButton);
        waitForInvisibilityOfElement(noActiveLoanMessage);
    }

    public void verifyMyCreditScreenClientWithSoldLoan() {
        waitFor(soldLoanMessage);
    }

    public void checkUsedAmount(String principalOpen) {
        usedAmount = String.format(usedAmount, principalOpen);
        assertThat(usedAmount).contains(principalOpen);
        driver.findElementByXPath(usedAmount);
    }

    public void checkUnpaidAmount(String principalOpen) {
        click(expandCreditInfo);
        waitFor(totalUnpaid);
        unpaidAmount = String.format(unpaidAmount, principalOpen);
        assertThat(unpaidAmount).contains(principalOpen);
        driver.findElementByXPath(unpaidAmount);
    }
}
