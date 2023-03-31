import BaseHttpClient.BaseHttpClient;
import BaseHttpClient.ResponseData;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        HashMap<String, String> tagsToReplace = new HashMap<>();
        tagsToReplace.put("flight", "1");
        ResponseData data = BaseHttpClient.execute("GET",Config.getProperty("SPACEX_ADDRESS"), tagsToReplace);
        System.out.println(data);
    }
}