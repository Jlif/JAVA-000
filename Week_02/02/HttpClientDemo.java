package java0.nio01;

import java.io.IOException;

import okhttp3.*;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpClientDemo {
    private static final Logger logger = LoggerFactory.getLogger(HttpClientDemo.class);

    static OkHttpClient client = new OkHttpClient();

    static final String URL = "http://localhost:8801";

    static final Request request = new Request.Builder()
            .url(URL)
            .build();

    public static void main(String[] args) {
        BasicConfigurator.configure();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                logger.error("onFailure");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                logger.info("onResponse: " + response.body().string());
            }
        });
    }
}
