package live.cnpm_web.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import live.cnpm_web.util.serializer.LocalDateAdapter;
import live.cnpm_web.util.serializer.LocalDateTimeAdapter;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class XHRUtil {
    private static final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
            .serializeNulls()
            .create();

    public static JsonObject parseJSONObject(HttpServletRequest request) throws IOException {
        BufferedReader reader = request.getReader();
        return gson.fromJson(reader, JsonObject.class);
    }

    public static String getJSONString(Object object) {
        return gson.toJson(object);
    }
}
