package helpers;

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
    public static final String PASSWORD_RECOVERY_ROUTE = "/mobile-app/v1/password-recovery/sms/";
    public static final String CREDIT_LINE_ROUTE = "/mobile-app/v1/credit-line/";

    public static final String PHONE_CODE = "4";

    // ef_settings
    public static String API_DMS_DEV;
    public static String API_DMS_PROD;

    // Android Capabilities
    public static final String ANDROID_VERSION = "10";
    public static final String CREDIT_PRIME_RO_ANDROID = "Users/creditPrime.apk";
    public static final String PIXEL_XL = "Pixel XL";
    public static final String ANDROID = "Android";
    public static final String RO_BUNDLE_ID = "com.dyninno.mobileapp.romania";
    public static final String RO_APP_MAIN_ACTIVITY = "com.dyninno.mobileapp.romania.MainActivity";

    // IOS Capabilities
    public static final String XCUITest = "XCUITest";
    public static final String IOS_VERSION = "15.0";
    public static final String CREDIT_PRIME_RO_IOS = "Users/creditPrime.ipa";
    public static final String IPHONE_7_PLUS = "iPhone 7 Plus";
    public static final String IOS = "iOS";

    public static final String DEFAULT_SCREENSHOT_FOLDER_PATH = "screenshots";
}
