package com.dmz.service.adapters;

import com.dmz.service.exceptions.GsonImprovedAdapterException;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.lang.reflect.Type;

/**
 * Created by dmz on 2016/11/22.
 */
public class ImprovedEnumType implements JsonDeserializer<SuperEnum> {

    private static final Logger LOG = LoggerFactory.getLogger(ImprovedEnumTypeAdapter.class);

    @Override
    public SuperEnum deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        String value = json.getAsString();
        SuperEnum superEnum;
        try {
            Class<?> clazz = Class.forName(typeOfT.getTypeName());
            Method parseMethod = clazz.getDeclaredMethod("parse", String.class);
            if (parseMethod == null) {
                LOG.error("{} Has Not parse Method", clazz.getTypeName());
            }
            superEnum = (SuperEnum) parseMethod.invoke(typeOfT.getClass(), value);

        } catch (Exception e) {
            throw new GsonImprovedAdapterException("Deserialize Error", e);
        }
        return superEnum;
    }

}
