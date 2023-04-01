import BaseHttpClient.BaseHttpClient;
import BaseHttpClient.ResponseData;
import BaseHttpClient.ContentDownloader;
import org.json.JSONException;

import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        HashMap<String, String> tagsToReplace = new HashMap<>();
            for (int i = 0; i<100; i++) {
                try {
                    tagsToReplace.put("flight", ""+i);
                    ResponseData data = BaseHttpClient.execute("GET",Config.getProperty("SPACEX_ADDRESS"), tagsToReplace);
                    System.out.println(data.getResponseJson().getJSONObject("links").getJSONArray("flickr_images"));
    //            ContentDownloader.execute("GET", "https://images2.imgbox.com/3c/0e/T8iJcSN3_o.png", tagsToReplace);
            } catch (JSONException ignored) {

                }
            }
    }
}