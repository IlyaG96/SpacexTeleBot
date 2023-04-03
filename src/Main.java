import BaseHttpClient.BaseHttpClient;
import BaseHttpClient.ResponseData;
import BaseHttpClient.ContentDownloader;
import Config.Config;
import Telebot.Telebot;
import org.json.JSONArray;
import org.json.JSONException;
import BaseHttpClient.HttpMethod;

import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        HashMap<String, String> tagsToReplace = new HashMap<>();

        Telebot.sendMessage();

//        for (int i = 0; i<100; i++) {
//                try {
//                    tagsToReplace.put("flight", "" + i);
//                    ResponseData data = BaseHttpClient.execute(HttpMethod.GET, Config.getProperty("SPACEX_ADDRESS"),null, null, tagsToReplace);
//                    JSONArray images = data.getResponseJson().getJSONObject("links").getJSONArray("flickr_images");
//                    if (BaseHttpClient.notEmptyJsonArray(images)) {
//                        System.out.println("Get pack of images");
//                        ContentDownloader downloader = new ContentDownloader(images);
//                        Thread thread = new Thread(downloader);
//                        thread.start();
//                    }
//            } catch (JSONException ignored) {
//
//                }
//            }
    }
}