package BaseHttpClient;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;

import java.util.HashMap;
import java.util.Map;

public class RequestBuilder {

    public static HttpRequestBase createRequest(String method,
                                                String address,
                                                HashMap<String, String> body,
                                                HashMap<String, String> payload,
                                                HashMap<String, String> tagsToReplace) {
        if (tagsToReplace != null) {
            address = replaceTags(address, tagsToReplace);
        }

        if (payload != null) {
            address = setQuery(payload, address);
        }

        if (method.equalsIgnoreCase("POST")) {
            HttpPost request  = new HttpPost(address);
            if (body != null) {
                request.setEntity(buildJsonBody(body));
            }
            return request;
        } else if (method.equalsIgnoreCase("GET")) {
            return new HttpGet(address);
        } else {
            throw new StatusCodeException("111");
        }
    }

    private static String replaceTags(String address, HashMap<String, String> tagsToReplace) {
        for (Map.Entry<String, String> map : tagsToReplace.entrySet()) {
            address = address.replace(map.getKey(), map.getValue());
        }
        return address;
    }

    private static StringEntity buildJsonBody(HashMap<String, String> bodyContent) {
        StringBuilder body = new StringBuilder("{");
        for (Map.Entry<String, String> map : bodyContent.entrySet()) {
            body.append("\"").append(map.getKey()).append("\"").append(":").append("\"").append(map.getValue()).append("\"");
        }
        body.append("}");
        return new StringEntity(String.valueOf(body), ContentType.APPLICATION_JSON);
    }
    private static String buildQueryParams(HashMap<String, String> bodyContent) {
        StringBuilder body = new StringBuilder();
        for (Map.Entry<String, String> map : bodyContent.entrySet()) {
            body.append("?").append(map.getKey()).append("=").append(map.getValue()).append("&");
        }

        return body.toString();
    }
    private static String setQuery(HashMap<String, String> payload, String address) {
        String queryParams = buildQueryParams(payload);
        address += queryParams;
        return address;
    }
}
