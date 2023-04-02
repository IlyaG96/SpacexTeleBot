import BaseHttpClient.BaseHttpClient;
import BaseHttpClient.ResponseData;
import BaseHttpClient.ContentDownloader;
import org.json.JSONArray;
import org.json.JSONException;
import BaseHttpClient.HttpMethod;

import java.io.IOException;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws IOException {
        HashMap<String, String> tagsToReplace = new HashMap<>();

        for (int i = 0; i<100; i++) {
                try {
                    tagsToReplace.put("flight", ""+i);
                    ResponseData data = BaseHttpClient.execute(HttpMethod.GET, Config.getProperty("SPACEX_ADDRESS"), tagsToReplace);
                    JSONArray images = data.getResponseJson().getJSONObject("links").getJSONArray("flickr_images");
                    if (BaseHttpClient.notEmptyJsonArray(images)) {
                        ContentDownloader downloader = new ContentDownloader(images);
                        Thread thread = new Thread(downloader);
                        thread.start();
                    }
            } catch (JSONException ignored) {

                }
            }
    }
}