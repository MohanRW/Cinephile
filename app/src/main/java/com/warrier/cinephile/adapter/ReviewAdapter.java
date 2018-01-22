package com.warrier.cinephile.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.warrier.cinephile.R;

import java.util.Locale;

import static com.warrier.cinephile.utils.Constants.COL_REVIEW_AUTHOR;
import static com.warrier.cinephile.utils.Constants.COL_REVIEW_CONTENT;
import static com.warrier.cinephile.utils.Constants.COL_REVIEW_URL;

/**
 * Created by Galahad on 17/11/2017.
 */

public class ReviewAdapter extends
        RecyclerView.Adapter<ReviewAdapter.ViewHolder>{
    private final Cursor cursor;

    public ReviewAdapter(Cursor cursor) {
        this.cursor = cursor;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.list_review_movies, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        cursor.moveToPosition(position);
        final String author_name = cursor.getString(COL_REVIEW_AUTHOR);
        final String content = cursor.getString(COL_REVIEW_CONTENT);
        final String url = cursor.getString(COL_REVIEW_URL);

        viewHolder.author.setText(Html.fromHtml("<font color=\"#212121\">By " + author_name + ":</font>"));
        viewHolder.contentView.setText(content);
        viewHolder.urlView.setText(String.format(Locale.ENGLISH, "Look more at:  %s", url));
    }

    @Override
    public int getItemCount() {
        if (cursor != null) {
            return cursor.getCount();
        }
        return 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        final TextView urlView;
        final TextView author;
        final TextView contentView;

        ViewHolder(View view) {
            super(view);
            author = (TextView) view.findViewById(R.id.review_author);
            contentView = (TextView) view.findViewById(R.id.review_content);
            urlView = (TextView) view.findViewById(R.id.review_url);
        }
    }
}
