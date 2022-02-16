package helpers;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class JsonParser {

    private static String inline = "";

    public static String name, phone, password, temporaryPassword;

    protected static JSONParser parser = new JSONParser();
    protected static String PREDEFINED_USERS = "src/test/resources/features/predefinedClients.json";
    protected static String EF_SETTINGS = "src/test/java/helpers/ef_settings.json";
    protected static String API_DMS_DEV;
    protected static String ENV = "api_dev";
    protected static String PASSWORD_RECOVERY_ROUT = "/mobile-app/v1/password-recovery/sms/";

    public static void main(String[] args) throws IOException, ParseException {
        //self-check
        getTemporaryPassword("TEST-PASSWORD");
        getPhoneNumber("TEST-PASSWORD");
    }

    public static void establishConnection(String username) throws IOException, ParseException {
        URL url = new URL(getConfigFromJson(ENV) + PASSWORD_RECOVERY_ROUT + "4" + getPhoneNumber(username));
        HttpURLConnection setUpConnection = (HttpURLConnection) url.openConnection();
        setUpConnection.setRequestMethod("GET");
        setUpConnection.connect();
        int responsecode = setUpConnection.getResponseCode();

        if (responsecode != 200)
            throw new RuntimeException("HttpResponseCode: " + responsecode);
        else {
            Scanner setUpScanner = new Scanner(url.openStream());
            while (setUpScanner.hasNext()) {
                inline = setUpScanner.nextLine();
            }
            setUpScanner.close();
        }
        setUpConnection.disconnect();
    }

    public static void getTemporaryPassword(String username) throws ParseException, IOException {
        establishConnection(username);
        JSONParser parse = new JSONParser();
        JSONObject jsonObject = (JSONObject) parse.parse(inline);
        JSONObject result = (JSONObject) jsonObject.get("result");
        JSONObject data = (JSONObject) result.get("data");
        String dataReplace = data.toString();
        Object replaceText = dataReplace.replace("{" + "\"message\":\"Ai solicitat resetarea parolei de acces in contul tau CreditPrime.ro. Pentru a te loga foloseste parola temporara: ", "");
        String replaceText2 = replaceText.toString();
        temporaryPassword = replaceText2.replace("\"" + "}", "");
    }

    public static void getJsonDataFromFile(String username) throws IOException, ParseException {
        Object obj = parser.parse(new FileReader(PREDEFINED_USERS));
        JSONObject jsonObject = (JSONObject) obj;
        JSONArray users = (JSONArray) jsonObject.get(username);
        for (Object testData : users) {
            JSONObject jsonData = (JSONObject) testData;
            name = (String) jsonData.get("name");
            phone = (String) jsonData.get("phone");
            password = (String) jsonData.get("password");
        }
    }

    public static String getPhoneNumber(String username) throws IOException, ParseException {
        getJsonDataFromFile(username);
        return phone;
    }

    public static String getConfigFromJson(String env) throws IOException, ParseException {
        Object obj = parser.parse(new FileReader(EF_SETTINGS));
        JSONObject jsonObject = (JSONObject) obj;
        JSONObject config = (JSONObject) jsonObject.get("DMS");
        API_DMS_DEV = (String) config.get(env);
        return API_DMS_DEV;
    }
}