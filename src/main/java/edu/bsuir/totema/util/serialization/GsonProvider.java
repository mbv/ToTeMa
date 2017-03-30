package edu.bsuir.totema.util.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonProvider {
    private final static GsonBuilder builder = new GsonBuilder();
    private final static Gson gson;
    static  {
        builder.excludeFieldsWithoutExposeAnnotation();
        gson  = builder.create();
    }
    private GsonProvider() {

    }
    public static Gson getGson() {
        return gson;
    }
}
