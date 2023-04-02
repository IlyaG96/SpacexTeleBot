package BaseHttpClient;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class BaseHttpClient {

    static void raiseForStatus(int statusCode) {
        if (statusCode >= 200 && statusCode < 300) {
            return;
        } throw new StatusCodeException("Status code is not OK");
    }

    public static boolean notEmptyJsonArray(JSONArray urls) {
        return !urls.isEmpty();
    }

    static String getFilenameFromURI(String path) {
        return path.replace("/", "");
    }

    public static JSONObject getJsonFromResponse(HttpEntity entity) throws IOException {
        try {
            if (entity != null) {
                InputStream inputStream = entity.getContent();
                String responseBody = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
                return new JSONObject(responseBody);
            }
            } catch (JSONException exc) {
                    return new JSONObject("{\"success\":\"false\"}");
                }
        return new JSONObject("{\"success\":\"false\"}");
    }

    public static ResponseData execute(
            HttpMethod method,
            String address,
            HashMap<String, String> tagsToReplace) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpRequestBase request = RequestBuilder.createRequest(
                    method,
                    address,
                    null,
                    null,
                    tagsToReplace);
            CloseableHttpResponse response = httpClient.execute(request);
            BaseHttpClient.raiseForStatus(response.getStatusLine().getStatusCode());

            JSONObject responseJson = getJsonFromResponse(response.getEntity());
            System.out.println("Get pack of images");
            return new ResponseData(
                    response.getStatusLine().getStatusCode(), responseJson);
        }
        catch (IOException | StatusCodeException exc) {
            return new ResponseData(-1, new JSONObject("{\"success\":\"false\"}"));
        }
    }
}
