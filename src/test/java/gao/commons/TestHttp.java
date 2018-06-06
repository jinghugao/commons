package gao.commons;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import gao.commons.entity.User;
import gao.commons.utility.HttpClient;
import okhttp3.Response;
import org.junit.Test;

import java.io.IOException;

import static gao.commons.utility.StoreUserThreadLocal.setUser;


public class TestHttp {
    private static final Gson gson = new Gson();

    @Test
    public void test() throws IOException {
        Response response = HttpClient.get("http://localhost:8081/fishsso/user/getUserInfo", "f3ef2436fee04e5f869259cb3726c739");
        System.out.println(response.body().string());

    }

    @Test
    public void test1() throws IOException {
        Response response = HttpClient.get("http://localhost:8081/fishsso/user/getUserInfo", "9bb8d9f24416489db6ae0de00faa96f5");
        String str = response.body().string();
        JsonObject jsonObject = (JsonObject) gson.fromJson(str, JsonObject.class);
        JsonObject result = jsonObject.get("result").getAsJsonObject();
        User user = (User) gson.fromJson(result, User.class);
    }
}
