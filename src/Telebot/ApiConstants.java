package Telebot;

import Config.Config;

public enum ApiConstants {
    sendMessage ("https://api.telegram.org/bot%s/sendMessage".formatted(Config.getProperty("TG_TOKEN"))),
    sendPhoto ("https://api.telegram.org/bot%s/sendPhoto".formatted(Config.getProperty("TG_TOKEN")));
    private final String apiUrl;
    ApiConstants(String apiUrl) {
        this.apiUrl = apiUrl;
    }
    public String value() {
        return apiUrl;
    }

}
