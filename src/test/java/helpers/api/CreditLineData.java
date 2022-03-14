package helpers.api;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;


public class CreditLineData {

    JSONParser parse = new JSONParser();
    UrlConnector urlConnector = new UrlConnector();

    public static String loanId, status, amountUsed, amountAvailable, amountApproved, minPaymentOnDueDate, fullAmountToRepay, SABalance;

    public void getCreditLineDataFromJson(String username) throws IOException, ParseException {
        JSONObject jsonObject = (JSONObject) parse.parse(urlConnector.getCreditLineData(username));
        JSONObject result = (JSONObject) jsonObject.get("result");
        JSONObject data = (JSONObject) result.get("data");
        loanId = String.valueOf(data.get("loan_id"));
        status = String.valueOf(data.get("status"));
        amountUsed = String.valueOf(data.get("amount_used"));
        amountAvailable = String.valueOf(data.get("amount_available"));
        amountApproved = String.valueOf(data.get("amount_approved"));
        minPaymentOnDueDate = String.valueOf(data.get("min_payment_on_due_date"));
        fullAmountToRepay = String.valueOf(data.get("full_amount_to_repay"));
        SABalance = String.valueOf(data.get("statement_account_balance"));
    }

    public String getLoanId(String username) throws IOException, ParseException {
        getCreditLineDataFromJson(username);
        return loanId;
    }

    public String getStatus(String username) throws IOException, ParseException {
        getCreditLineDataFromJson(username);
        return status;
    }

    public String getAmountUsed(String username) throws IOException, ParseException {
        getCreditLineDataFromJson(username);
        return amountUsed;
    }

    public String getAmountAvailable(String username) throws IOException, ParseException {
        getCreditLineDataFromJson(username);
        return amountAvailable;
    }

    public String getAmountApproved(String username) throws IOException, ParseException {
        getCreditLineDataFromJson(username);
        return amountApproved;
    }

    public String getMinPaymentOnDueDate(String username) throws IOException, ParseException {
        getCreditLineDataFromJson(username);
        return minPaymentOnDueDate;
    }

    public String getFullAmountToRepay(String username) throws IOException, ParseException {
        getCreditLineDataFromJson(username);
        return fullAmountToRepay;
    }

    public String getSABalance(String username) throws IOException, ParseException {
        getCreditLineDataFromJson(username);
        return SABalance;
    }
}
