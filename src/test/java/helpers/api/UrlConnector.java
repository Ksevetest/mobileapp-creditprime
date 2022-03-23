package helpers.api;

import helpers.user.TestUser;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import static helpers.Configuration.*;

public class UrlConnector {

    protected static JSONParser parser = new JSONParser();
    TestUser testUser = new TestUser();

    public static String inline = "";

    public void passwordRecoveryDevUrl(String username) throws IOException, ParseException {
        String passwordRecoveryDev = getDMSDevUrl() + PASSWORD_RECOVERY_ROUTE + PHONE_CODE + testUser.getPhone(username);
        establishConnection(passwordRecoveryDev);
    }

    public void passwordRecoveryProdUrl(String username) throws IOException, ParseException {
        String passwordRecoveryProd = getDMSProdUrl() + PASSWORD_RECOVERY_ROUTE + PHONE_CODE + testUser.getPhone(username);
    }

    public void creditLineDevUrl(String username) throws IOException, ParseException {
        String creditLineDev = getDMSDevUrl() + CREDIT_LINE_ROUTE + testUser.getClientId(username);
        establishConnection(creditLineDev);
    }

    public void establishConnection(String dmsUrl) throws IOException {
        URL url = new URL(dmsUrl);
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

    public String getInlinePasswordRecovery(String username) throws IOException, ParseException {
        passwordRecoveryDevUrl(username);
        return inline;
    }

    public String getCreditLineData(String username) throws IOException, ParseException {
        creditLineDevUrl(username);
        return inline;
    }

    public void getConfigFromJson() throws IOException, ParseException {
        Object obj = parser.parse(new FileReader(EF_SETTINGS));
        JSONObject jsonObject = (JSONObject) obj;
        JSONObject config = (JSONObject) jsonObject.get("DMS");
        API_DMS_DEV = (String) config.get("api_dev");
        API_DMS_PROD = (String) config.get("api_prod");
    }

    public String getDMSDevUrl() throws IOException, ParseException {
        getConfigFromJson();
        return API_DMS_DEV;
    }

    public String getDMSProdUrl() throws IOException, ParseException {
        getConfigFromJson();
        return API_DMS_PROD;
    }
}