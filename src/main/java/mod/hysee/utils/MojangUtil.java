package mod.hysee.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.function.Consumer;

public class MojangUtil {

    public static String apiKey = "";

    public static JsonObject getJson(String urlS) {
        try {
            URL url = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36");
            conn.setRequestProperty("accept", "application/json");
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String input;
            while ((input = in.readLine()) != null)
                response.append(input);
            in.close();
            Gson gson = new Gson();
            return gson.fromJson(response.toString(), JsonObject.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static String getUUID(String ign) {
        JsonObject mojangResponse = getJsonResponse("https://api.mojang.com/users/profiles/minecraft/" + ign);
        if (mojangResponse == null || !mojangResponse.has("id")) {
            return null;
        }
        return mojangResponse.get("id").getAsString();
    }

}
