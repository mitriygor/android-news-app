package com.example.mytr.newsfeedapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

public class NewsAdapter extends ArrayAdapter<News> {

    private static final DataParser dataParser = new DataParser();
    private String NO_TITLE_MSG;
    /**
     * Constructs a new {@link NewsAdapter}.
     *
     * @param context of the app
     * @param news   is the list of news, which is the data source of the adapter
     */
    public NewsAdapter(Context context, List<News> news) {
        super(context, 0, news);
        NO_TITLE_MSG = context.getResources().getString(R.string.no_title);
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.news_list_item, parent, false);
        }

        News currentNews = getItem(position);
        String title = currentNews.getTitle();
        String section = currentNews.getSection();
        String date = currentNews.getDate();
        final String url = currentNews.getUrl();

        TextView titleTextView = (TextView) listItemView.findViewById(R.id.title);

        titleTextView.setText((title != null && !title.isEmpty()) ? title : NO_TITLE_MSG);

        if(section != null && !date.isEmpty()) {
            TextView sectionTextView = (TextView) listItemView.findViewById(R.id.section);
            sectionTextView.setText(section);
        }

        if(date != null && !date.isEmpty()) {
            TextView dateTextView = (TextView) listItemView.findViewById(R.id.date);
            dateTextView.setText(dataParser.getDate(date));
        }

        if(url != null && !url.isEmpty()) {
            listItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(url));
                    view.getContext().startActivity(intent);
                }
            });
        }

        return listItemView;
    }
}
