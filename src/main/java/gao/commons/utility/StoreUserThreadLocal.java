package gao.commons.utility;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import gao.commons.entity.User;
import okhttp3.Response;

import java.io.IOException;

public class StoreUserThreadLocal {

    private static final Gson gson = new Gson();

    public static ThreadLocal<User> userThreadLocal = new ThreadLocal<User>();

    public static void setUser(User user) {
        userThreadLocal.set(user);
    }

    public static void storeUser(String url, String headerToken) throws IOException {
        Response response = HttpClient.get(url, headerToken);
        String str = response.body().string();
        JsonObject jsonObject = gson.fromJson(str, JsonObject.class);
        JsonObject result = jsonObject.get("result").getAsJsonObject();
        User user = gson.fromJson(result, User.class);
        setUser(user);
    }
}
