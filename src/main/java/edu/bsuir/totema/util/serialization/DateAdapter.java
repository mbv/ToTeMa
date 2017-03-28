package edu.bsuir.totema.util.serialization;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class DateAdapter extends TypeAdapter<Date> {
    private final static DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    @Override
    public java.sql.Date read(JsonReader arg0) throws IOException {
        return null;
    }

    @Override
    public void write(JsonWriter arg0, java.sql.Date arg1) throws IOException {
        arg0.value(dateFormat.format(arg1));
    }
}