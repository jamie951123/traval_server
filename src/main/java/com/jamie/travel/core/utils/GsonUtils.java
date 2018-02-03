package com.jamie.travel.core.utils;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class GsonUtils {

	public static Gson getGson(){
		GsonBuilder gsonBuilder = new GsonBuilder();
	    gsonBuilder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
	        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        @Override
	        public Date deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context)
	                throws JsonParseException {
	            try {
	                return df.parse(json.getAsString());
	            } catch (Exception e) {
	                return null;
	            }
	        }
	    });
		Gson gson = gsonBuilder.create();
		return gson;
	}
}