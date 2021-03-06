package com.warrier.cinephile.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.warrier.cinephile.R;
import com.warrier.cinephile.utils.Constants;
import com.warrier.cinephile.utils.RoundedTransformation;
import com.warrier.cinephile.utils.ViewHolderUtils;

/**
 * Created by Galahad on 17/11/2017.
 */

public class SimilarMovieArrayAdapter extends
        RecyclerView.Adapter<SimilarMovieArrayAdapter.ViewHolder> {

    private final Cursor cursor;
    private Context context;
    private ViewHolderUtils.SetOnClickListener listener;

    public SimilarMovieArrayAdapter(Cursor cursor) {
        this.cursor = cursor;}


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.list_item_similar_movies, parent, false);

        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int position) {
        cursor.moveToPosition(position);

        final String url = cursor.getString(Constants.SIMILAR_MOV_COL_POSTER_PATH);
        Picasso
                .with(context)
                .load(url)
                .networkPolicy(NetworkPolicy.OFFLINE)
                .transform(new RoundedTransformation(10, 10))
                .fit()
                .centerCrop()
                .into(viewHolder.imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                    }

                    @Override
                    public void onError() {
                        Picasso
                                .with(context)
                                .load(url)
                                .error(R.mipmap.ic_launcher)
                                .fit()
                                .centerCrop()
                                .into(viewHolder.imageView, new Callback() {
                                    @Override
                                    public void onSuccess() {
                                    }

                                    @Override
                                    public void onError() {
                                    }
                                });
                    }
                });
        viewHolder.imageView.setAdjustViewBounds(true);
        viewHolder.setItemClickListener(listener);
    }
    @Override
    public int getItemCount() {
        if (cursor != null) {
            return cursor.getCount();
        }
        return 0;
    }
    public Cursor getCursor() {
        return cursor;
    }

    public void setOnClickListener(ViewHolderUtils.SetOnClickListener clickListener) {
        this.listener = clickListener;
    }
    public interface SetOnClickListener extends ViewHolderUtils.SetOnClickListener {
        void onItemClick(int position);
    }
    static class ViewHolder extends RecyclerView.ViewHolder {

        final ImageView imageView;
        final TextView name;
        private ViewHolderUtils.SetOnClickListener listener;

        ViewHolder(final View view) {
            super(view);
            imageView = (ImageView) view.findViewById(R.id.similar_movie_poster);
            name = (TextView) view.findViewById(R.id.name);
            view.setClickable(true);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onItemClick(getAdapterPosition());
                    }
                }
            });
        }

        void setItemClickListener(ViewHolderUtils.SetOnClickListener itemClickListener) {
            this.listener = itemClickListener;
        }

    }
}
