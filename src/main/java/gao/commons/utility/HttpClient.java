package gao.commons.utility;

import okhttp3.*;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

public class HttpClient {
    private static final OkHttpClient client = new OkHttpClient().newBuilder().build();

    public static Response get(String url, String headerToken) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .addHeader("X-FISH-TOKEN", headerToken)
                .build();
        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) {
            throw new RuntimeException("连接错误" + response.code());
        }
        return response;
    }

    public static Response post(String url, String headerToken, String json) throws IOException {
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .addHeader("X-FISH-TOKEN", headerToken)
                .post(body)
                .build();

        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) {
            throw new RuntimeException("连接错误" + response.code());
        }
        return response;
    }

    public static Response postForm(String url, String headerToken, Map<String, String> map) throws IOException {
        FormBody.Builder builder = new FormBody.Builder();
        Set<Map.Entry<String, String>> set = map.entrySet();
        for (Map.Entry<String, String> entry : set) {
            builder.add(entry.getKey(), entry.getValue());
        }
        RequestBody formBody = builder.build();

        Request request = new Request.Builder()
                .url(url)
                .addHeader("X-FISH-TOKEN", headerToken)
                .post(formBody)
                .build();
        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) {
            throw new RuntimeException("连接错误" + response.code());
        }
        return response;
    }
}
