package helpers.api;

import helpers.Configuration;
import helpers.user.TestUser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import static helpers.Configuration.*;

public class UrlConnector {
    TestUser testUser = new TestUser();
    Configuration configuration = new Configuration();

    public static String inline = "";

    public String passwordRecoveryUrl(String username) throws IOException, ParseException {
        return configuration.getConfigFromJson(DEV_ENV) + PASSWORD_RECOVERY_ROUTE + PHONE_CODE + testUser.getPhone(username);
    }

    public void establishConnection(String username) throws IOException, ParseException {

        URL url = new URL(passwordRecoveryUrl(username));
        System.out.println(url);
        HttpURLConnection setUpConnection = (HttpURLConnection) url.openConnection();
        setUpConnection.setRequestMethod("GET");
        setUpConnection.connect();
        int responsecode = setUpConnection.getResponseCode();

        if (responsecode != 200) throw new RuntimeException("HttpResponseCode: " + responsecode);

        Scanner setUpScanner = new Scanner(url.openStream());
        inline = setUpScanner.nextLine();
        setUpScanner.close();

        setUpConnection.disconnect();
    }

    public String getInline(String username) throws IOException, ParseException {
        establishConnection(username);
        return inline;
    }
}