package BaseHttpClient;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;

public class ContentDownloader extends BaseHttpClient{
    public static ResponseData execute(
            String method,
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

            saveFile(response);

            return new ResponseData(200, new JSONObject("{\"success\":\"true\"}"));

        }
        catch (IOException | StatusCodeException exc) {
            return new ResponseData(-1, new JSONObject("{\"success\":\"false\"}"));
        }
    }

    private static void saveFile(CloseableHttpResponse response) throws IOException {
        InputStream inputStream = response.getEntity().getContent();
        OutputStream outputStream = new FileOutputStream("image.jpg");
        int read = 0;
        byte[] bytes = new byte[1024];
        while ((read = inputStream.read(bytes)) != -1) {
            outputStream.write(bytes, 0, read);
        }
        EntityUtils.consume(response.getEntity());
        outputStream.close();
        response.close();
    }
}
