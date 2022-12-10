package live.cnpm_web.util;

import com.google.gson.*;
import live.cnpm_web.util.serializer.LocalDateAdapter;
import live.cnpm_web.util.serializer.LocalDateTimeAdapter;

import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
            .setExclusionStrategies(new ReferenceExclusionStrategy())
            .create();

    public static JsonObject parseJSONObject(HttpServletRequest request) throws IOException {
        BufferedReader reader = request.getReader();
        return gson.fromJson(reader, JsonObject.class);
    }

    public static String getJSONString(Object object) {
        return gson.toJson(object);
    }


    // stupid way because this allows only ManyToOne, but it works :))
    public static class ReferenceExclusionStrategy implements ExclusionStrategy {
        public boolean shouldSkipClass(Class<?> klass) {

            return ((klass.getAnnotation(OneToOne.class) != null) ||
                    (klass.getAnnotation(OneToMany.class) != null) ||
                    (klass.getAnnotation(ManyToMany.class) != null));
        }

        public boolean shouldSkipField(FieldAttributes f) {
            return ((f.getAnnotation(OneToOne.class) != null) ||
                    (f.getAnnotation(OneToMany.class) != null) ||
                    (f.getAnnotation(ManyToMany.class) != null));
        }
    }
}
