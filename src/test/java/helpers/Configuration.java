package helpers;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class Configuration {

    //testUsers
    public static final String PREDEFINED_USERS = "src/test/java/helpers/user/predefinedClients.json";

    // api urls
    public static final String EF_SETTINGS = "src/test/java/helpers/api/ef_settings.json";

    // env
    public static final String DEV_ENV = "api_dev";
    public static final String STAGING_ENV = "api_staging";
    public static final String PROD_ENV = "api_prod";

    // api routs
    public static final String PASSWORD_RECOVERY_ROUT = "/mobile-app/v1/password-recovery/sms/";

    public static final String PHONE_CODE = "4";

    // ef_settings
    public static String API_DMS_DEV;

    // Android Capabilities
    public static final String ANDROID_VERSION = "10";
    public static final String CREDIT_PRIME_RO_ANDROID = "Users/creditPrime.apk";
    public static final String PIXEL_XL = "Pixel XL";
    public static final String ANDROID = "Android";
    public static final String roBundleID = "com.dyninno.mobileapp.romania";
    public static final String roAppMainActivity = "com.dyninno.mobileapp.romania.MainActivity";

    // IOS Capabilities
    public static final String XCUITest = "XCUITest";
    public static final String IOS_VERSION = "15.0";
    public static final String CREDIT_PRIME_RO_IOS = "Users/creditPrime.ipa";
    public static final String IPHONE_7_PLUS = "iPhone 7 Plus";
    public static final String IOS = "iOS";

    // Json parser
    protected static JSONParser parser = new JSONParser();

    public  String getConfigFromJson(String env) throws IOException, ParseException {
        Object obj = parser.parse(new FileReader(EF_SETTINGS));
        JSONObject jsonObject = (JSONObject) obj;
        JSONObject config = (JSONObject) jsonObject.get("DMS");
        API_DMS_DEV = (String) config.get(env);
        return API_DMS_DEV;
    }
}
