package com.dmz.service.adapters;

import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by dmz on 2016/7/26.
 */
public final class ImprovedDateTypeAdapter extends TypeAdapter<Date> {

    private final DateFormat enUsFormat;
    private final DateFormat localFormat;
    private final DateFormat iso8601Format;
    private final DateFormat ymd;
    private final DateFormat ymdhms;

    public ImprovedDateTypeAdapter() {
        this.enUsFormat = DateFormat.getDateTimeInstance(2, 2, Locale.US);
        this.localFormat = DateFormat.getDateTimeInstance(2, 2);
        this.iso8601Format = buildIso8601Format();
        this.ymd = new SimpleDateFormat("yy-MM-dd");
        this.ymdhms = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    private static DateFormat buildIso8601Format() {
        SimpleDateFormat iso8601Format = new SimpleDateFormat("yyyy-MM-dd\'T\'HH:mm:ss\'Z\'", Locale.US);
        iso8601Format.setTimeZone(TimeZone.getTimeZone("UTC"));
        return iso8601Format;
    }

    public Date read(JsonReader in) throws IOException {
        if (in.peek() == JsonToken.NULL) {
            in.nextNull();
            return null;
        } else {
            return this.deserializeToDate(in.nextString());
        }
    }

    private synchronized Date deserializeToDate(String json) {
        try {
            return this.localFormat.parse(json);
        } catch (ParseException var5) {
            try {
                return this.enUsFormat.parse(json);
            } catch (ParseException var4) {
                try {
                    return this.iso8601Format.parse(json);
                } catch (ParseException var3) {
                    try {
                        return this.ymdhms.parse(json);
                    } catch (ParseException var2) {
                        try {
                            return this.ymd.parse(json);
                        } catch (ParseException var1) {
                            try {
                                return new Date(Long.parseLong(json));
                            } catch (Exception e) {
                                throw new JsonSyntaxException(json, e);
                            }
                        }
                    }
                }
            }
        }
    }

    public synchronized void write(JsonWriter out, Date value) throws IOException {
        if (value == null) {
            out.nullValue();
        } else {
            String dateFormatAsString = this.enUsFormat.format(value);
            out.value(dateFormatAsString);
        }
    }
}
