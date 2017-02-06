package com.example.mytr.newsfeedapp;

import android.content.AsyncTaskLoader;
import android.content.Context;
import org.json.JSONException;
import java.util.List;

public class NewsLoader extends AsyncTaskLoader<List<News>> {

    private static final String LOG_TAG = NewsLoader.class.getName();
    private String mUrl;

    /**
     * Constructs a new {@link NewsLoader}.
     *
     * @param context of the activity
     * @param url to load data from
     */
    public NewsLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<News> loadInBackground()   {
        if (mUrl == null) {
            return null;
        }

        List<News> books = null;

        try {
            books = QueryUtils.fetchNewsData(mUrl);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return books;
    }
}
