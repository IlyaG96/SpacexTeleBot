package BaseHttpClient;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import static BaseHttpClient.BaseHttpClient.getFilenameFromURI;

public class ContentDownloader implements Runnable {

    public final JSONArray urls;
    public ContentDownloader(JSONArray urls) {
        this.urls = urls;
    }
    private static void download(
            HttpMethod method,
            String address) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpRequestBase request = RequestBuilder.createRequest(
                    method,
                    address,
                    null,
                    null,
                    null);
            CloseableHttpResponse response = httpClient.execute(request);
            BaseHttpClient.raiseForStatus(response.getStatusLine().getStatusCode());

            saveFile(response, address);

        }
        catch (IOException | StatusCodeException exc) {
            exc.printStackTrace();
        }
    }

    private static void saveFile(CloseableHttpResponse response, String address) throws IOException {
        InputStream inputStream = response.getEntity().getContent();
        OutputStream outputStream = new FileOutputStream(getFilenameFromURI(new URL(address).getPath()));
        int read;
        byte[] bytes = new byte[1024];
        while ((read = inputStream.read(bytes)) != -1) {
            outputStream.write(bytes, 0, read);
        }
        EntityUtils.consume(response.getEntity());
        outputStream.close();
        response.close();
    }

    @Override
    public void run() {
        for (Object address: urls) {
            download(HttpMethod.GET, address.toString());
            System.out.println("downloaded " + address.toString());
        }
    }
}
