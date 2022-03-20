package helpers.user;

import helpers.api.UrlConnector;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

import static helpers.Configuration.PREDEFINED_USERS;

public class TestUser {

    public static String name, clientId, phone, password, newPassword, temporaryPassword, message;

    protected static JSONParser parser = new JSONParser();

    public void TestUserData(String username) throws IOException, ParseException {
        Object obj = parser.parse(new FileReader(PREDEFINED_USERS));
        JSONObject jsonObject = (JSONObject) obj;
        JSONObject user = (JSONObject) jsonObject.get(username);

        name = (String) user.get("name");
        clientId = (String) user.get("clientId");
        phone = (String) user.get("phone");
        password = (String) user.get("password");
        newPassword = (String) user.get("newPassword");
    }

    public String getName(String username) throws IOException, ParseException {
        TestUserData(username);
        return name;
    }

    public String getClientId(String username) throws IOException, ParseException {
        TestUserData(username);
        return clientId;
    }

    public String getPhone(String username) throws IOException, ParseException {
        TestUserData(username);
        return phone;
    }

    public String getPassword(String username) throws IOException, ParseException {
        TestUserData(username);
        return password;
    }

    public String getNewPassword(String username) throws IOException, ParseException {
        TestUserData(username);
        return newPassword;
    }

    public void getTempPasswordFromJson(String username) throws ParseException, IOException {
        UrlConnector urlConnector = new UrlConnector();
        JSONParser parse = new JSONParser();
        JSONObject jsonObject = (JSONObject) parse.parse(urlConnector.getInlinePasswordRecovery(username));
        JSONObject result = (JSONObject) jsonObject.get("result");
        JSONObject data = (JSONObject) result.get("data");
        message = (String) data.get("message");
        temporaryPassword = message.replace("Ai solicitat resetarea parolei de acces in contul tau CreditPrime.ro. Pentru a te loga foloseste parola temporara: ", "");
    }

    public String getTemporaryPassword(String username) throws IOException, ParseException {
        getTempPasswordFromJson(username);
        System.out.println(temporaryPassword);
        return temporaryPassword;
    }

}
