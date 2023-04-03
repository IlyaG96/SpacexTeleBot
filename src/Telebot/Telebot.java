package Telebot;

import BaseHttpClient.BaseHttpClient;
import BaseHttpClient.HttpMethod;
import Config.Config;

import java.io.ObjectInputFilter;
import java.util.HashMap;

public class Telebot {
    // send_message
    public static void sendMessage() {
        HashMap<String, String> queryParams = new HashMap<String, String>();
        String catsURL = "https://adonius.club/uploads/posts/2022-06/1654417445_74-adonius-club-p-utrom-kotenok-krasivo-foto-79.jpg";
        queryParams.put("photo", catsURL);
        queryParams.put("chat_id", Config.getProperty("MY_TG_ID"));

        BaseHttpClient.execute(
                HttpMethod.GET,
                ApiConstants.sendPhoto.value(),
                null,
                queryParams,
                null);
    }
}
