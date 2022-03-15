package screens;

import helpers.api.CreditLineData;
import io.appium.java_client.MobileBy;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;

import java.io.IOException;

import static org.assertj.core.api.Assertions.*;
import static helpers.api.CreditLineData.*;

public class MyCreditScreen extends BaseScreen {

    CreditLineData creditLineData = new CreditLineData();

    /**
     * Mobile Elements
     */
    By myCreditScreenTitle = MobileBy.xpath("//*[@content-desc='Informație credit']"),
            noActiveLoanMessage = MobileBy.AccessibilityId("În prezent nu aveți împrumuturi active"),
            requestNewLoanButton = MobileBy.AccessibilityId("APLICĂ PENTRU UN ÎMPRUMUT NOU"),
            soldLoanMessage = MobileBy.AccessibilityId("Creditul a fost vândut"),
            expandCreditInfo = MobileBy.xpath("//android.view.View[1]/android.widget.ImageView[2]"),
            totalUnpaid = MobileBy.xpath("//android.view.View[contains(@content-desc,'Total sumă datorată')]");
    String creditLineUsedAmount = ("//android.view.View[@content-desc='%s" + ".00']");
    String creditLineUnpaidAmount = ("//android.view.View[@content-desc='%s" + ".00 lei']");
    String paymentOnDueDate = ("//android.view.View[contains(@content-desc, '%s" + "0 lei')]");
    String lateLoanMessage = ("//android.view.View[contains(@content-desc, 'Ai %s zile de întârziere la plată! Te rugăm să achiți cât mai rapid suma indicată mai sus.')]");

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

    public void checkUsedAmount(String username, String principalOpen) throws IOException, ParseException {
        amountUsed = creditLineData.getAmountUsed(username);
        assertThat(amountUsed).isEqualTo(principalOpen);
        creditLineUsedAmount = String.format(creditLineUsedAmount, amountUsed);
        System.out.println(creditLineUsedAmount);
        waitFor(By.xpath(creditLineUsedAmount));
    }

    public void checkUnpaidAmount(String username, String principalOpen) throws IOException, ParseException {
        click(expandCreditInfo);
        waitFor(totalUnpaid);
        fullAmountToRepay = creditLineData.getFullAmountToRepay(username);
        assertThat(fullAmountToRepay).isEqualTo(principalOpen);
        creditLineUnpaidAmount = String.format(creditLineUnpaidAmount, fullAmountToRepay);
        System.out.println(creditLineUnpaidAmount);
        driver.findElementByXPath(creditLineUnpaidAmount);
    }

    public void checkThatUsedAmountGreaterThanZero(String username, String principalOpen) throws IOException, ParseException {
        amountUsed = creditLineData.getAmountUsed(username);
        assertThat(amountUsed).isGreaterThan(principalOpen);
        creditLineUsedAmount = String.format(creditLineUsedAmount, amountUsed);
        System.out.println(creditLineUsedAmount);
        waitFor(By.xpath(creditLineUsedAmount));
    }

    public void checkLateLoan(String username) throws IOException, ParseException {
        minPaymentOnDueDate = creditLineData.getMinPaymentOnDueDate(username);
        paymentOnDueDate = String.format(paymentOnDueDate, minPaymentOnDueDate);
        System.out.println(paymentOnDueDate);
        waitFor(By.xpath(paymentOnDueDate));
        currentDPD = creditLineData.getCurrentDPDCount(username);
        lateLoanMessage = String.format(lateLoanMessage, currentDPD);
        System.out.println(lateLoanMessage);
        waitFor(By.xpath(lateLoanMessage));
    }
}
