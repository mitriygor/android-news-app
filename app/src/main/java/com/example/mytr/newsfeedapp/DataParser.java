package com.example.mytr.newsfeedapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DataParser {
    public JSONArray getJsonArray(String jsonString) throws JSONException {
        System.out.println(jsonString);
        JSONObject jsonObject = new JSONObject(jsonString);
        return jsonObject.getJSONObject("response").getJSONArray("results");
    }

    public ArrayList<News> parseNews(JSONArray jsonArray) throws JSONException {
        ArrayList<News> items = new ArrayList<>();
        int lengthJsonArr = jsonArray.length();
        for (int i = 0; i < lengthJsonArr; i++) {
            News item = new News();
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            if (jsonObject.has("sectionName")) {
                item.setSection(jsonObject.getString("sectionName"));
            }
            if (jsonObject.has("webTitle")) {
                item.setTitle(jsonObject.getString("webTitle"));
            }
            if (jsonObject.has("webPublicationDate")) {
                item.setDate(jsonObject.getString("webPublicationDate"));
            }
            if (jsonObject.has("webUrl")) {
                item.setUrl(jsonObject.getString("webUrl"));
            }
            items.add(item);
        }
        return items;
    }

    public String getFormDate() {
        Date today = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyy-mm-dd");
        return dateFormat.format(today).toString();
    }

    public String getDate(String str) {

        Date date = new Date();
        try {
            date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").parse(str.replaceAll("Z$", "+0000"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return new SimpleDateFormat("MMM d, yyyy").format(date).toString();
    }
}
