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

    public String passwordRecoveryDevUrl(String username) throws IOException, ParseException {
        return getDMSDevUrl(DEV_ENV) + PASSWORD_RECOVERY_ROUTE + PHONE_CODE + testUser.getPhone(username);
    }

    public String passwordRecoveryProdUrl(String username) throws IOException, ParseException {
        return getDMSProdUrl(PROD_ENV) + PASSWORD_RECOVERY_ROUTE + PHONE_CODE + testUser.getPhone(username);
    }

    public void establishConnection(String username) throws IOException, ParseException {

        URL url = new URL(passwordRecoveryDevUrl(username));
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

    public void getConfigFromJson(String env) throws IOException, ParseException {
        Object obj = parser.parse(new FileReader(EF_SETTINGS));
        JSONObject jsonObject = (JSONObject) obj;
        JSONObject config = (JSONObject) jsonObject.get("DMS");
        API_DMS_DEV = (String) config.get(env);
        API_DMS_PROD = (String) config.get(env);

    }

    public String getDMSDevUrl(String env) throws IOException, ParseException {
        getConfigFromJson(env);
        return API_DMS_DEV;
    }

    public String getDMSProdUrl(String env) throws IOException, ParseException {
        getConfigFromJson(env);
        return API_DMS_PROD;
    }
}