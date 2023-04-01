package BaseHttpClient;
import org.json.JSONObject;

public class ResponseData {
    private final int statusCode;
    private final JSONObject responseJson;
    public ResponseData(int code, JSONObject json) {
        this.statusCode = code;
        this.responseJson = json;
    }
    public int getStatusCode() {
        return statusCode;
    }
    public JSONObject getResponseJson() {
        return responseJson;
    }

}
