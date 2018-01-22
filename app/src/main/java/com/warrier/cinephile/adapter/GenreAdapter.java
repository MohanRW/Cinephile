package com.warrier.cinephile.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.warrier.cinephile.R;

import static com.warrier.cinephile.utils.Constants.COL_GENRE_NAME;

/**
 * Created by Galahad on 17/11/2017.
 */

public class GenreAdapter extends
        RecyclerView.Adapter<GenreAdapter.ViewHolder> {
    private static final String LOG_TAG = GenreAdapter.class.getSimpleName();
    private final Cursor cursor;

    public GenreAdapter(Cursor cursor) {
        this.cursor = cursor;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.list_genre_movies, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        cursor.moveToPosition(position);
        final String genre = cursor.getString(COL_GENRE_NAME);
        holder.genre.setText(genre);
    }

    @Override
    public int getItemCount() {
        if (cursor != null) {
            return cursor.getCount();
        }
        return 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        final TextView genre;

        ViewHolder(View view) {
            super(view);
            genre = (TextView) view.findViewById(R.id.genre);
        }
    }
}
