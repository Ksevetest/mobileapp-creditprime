package screens;

import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;

public class MyProfileScreen extends BaseScreen {
    /**
     * Mobile Elements
     */
    By logoutButton = MobileBy.AccessibilityId("Ieșire"),
            logoutPopUpButton = MobileBy.xpath("//android.widget.Button[@content-desc='Ieșire']"),
            clientName = MobileBy.AccessibilityId("TEST-SVETLANA TEST-CIOCAN"),
            welcomeMessage = MobileBy.xpath("//*[@content-desc='Salut!']");

    public void verifyMyProfileScreen() {
        scrollTo(700, 1100, 700, 300);
    }

    public void logout() {
//        String user = driver.findElement(clientName).getText();
//        assertThat(user).contains(client.getName());
        click(logoutButton);
        waitFor(logoutPopUpButton);
        click(logoutPopUpButton);
        waitFor(welcomeMessage);
    }
}
