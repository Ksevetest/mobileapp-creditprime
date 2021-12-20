package screens;

import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;

public class MyCreditScreen extends BaseScreen {
    /**
     * Mobile Elements
     */
    By myCreditScreenTitle = MobileBy.xpath("//*[@content-desc='Informație credit']"),
            noActiveLoanMessage = MobileBy.AccessibilityId("În prezent nu aveți împrumuturi active"),
            requestNewLoanButton = MobileBy.AccessibilityId("APLICĂ PENTRU UN ÎMPRUMUT NOU"),
            soldLoanMessage = MobileBy.AccessibilityId("Creditul a fost vândut");

    public void verifyMyCreditScreen() {
        waitFor(myCreditScreenTitle);
        tapByCoordinates(1280, 2300);
    }

    public void verifyMyCreditScreenClientWithoutLoan() {
        waitFor(noActiveLoanMessage);
        waitFor(requestNewLoanButton);
        click(requestNewLoanButton);
        waitForInvisibilityOfElement(noActiveLoanMessage);
//        String title = "Credit online - până la 4000 lei. Fără adeverință, fără comisioane ✅";
//        assertThat(driver.getTitle().contains(title));
    }

    public void verifyMyCreditScreenClientWithSoldLoan() {
        waitFor(soldLoanMessage);
    }
}
