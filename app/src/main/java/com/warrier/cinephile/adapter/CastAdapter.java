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

public class CastAdapter extends RecyclerView.Adapter<CastAdapter.ViewHolder>{

    private static final String LOG_TAG = CastAdapter.class.getSimpleName();
    private final Cursor cursor;
    private ViewHolderUtils.SetOnClickListener listener;
    private Context context;

    public CastAdapter(Cursor cursor) {
        this.cursor = cursor;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.list_cast_movie, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        cursor.moveToPosition(position);
        final String cast_name = cursor.getString(Constants.CAST_COL_NAME);
        final String cast_character = cursor.getString(Constants.CAST_COL_CHARACTER);
        holder.name.setText(cast_name);
        holder.character.setText(cast_character);

        final String PROFILE_BASE_URL = "http://image.tmdb.org/t/p/w92";
        final String profileURL = PROFILE_BASE_URL + cursor.getString(Constants.CAST_COL_PROFILE_PATH);

        Picasso
                .with(context)
                .load(profileURL)
                .networkPolicy(NetworkPolicy.OFFLINE)
                .transform(new RoundedTransformation(10, 10))
                .fit()
                .centerCrop()
                .into(holder.profile, new Callback() {
                    @Override
                    public void onSuccess() {
                    }

                    @Override
                    public void onError() {
                        Picasso
                                .with(context)
                                .load(profileURL)
                                .fit()
                                .centerCrop()
                                .into(holder.profile, new Callback() {
                                    @Override
                                    public void onSuccess() {
                                    }

                                    @Override
                                    public void onError() {
                                    }
                                });
                    }
                });
        holder.profile.setAdjustViewBounds(true);
        holder.setItemClickListener(listener);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView profile;
        final TextView name;
        final TextView character;
        private ViewHolderUtils.SetOnClickListener listener;

        ViewHolder(final View view) {
            super(view);
            profile = (ImageView) view.findViewById(R.id.cast_image);
            name = (TextView) view.findViewById(R.id.cast_name);
            character = (TextView) view.findViewById(R.id.cast_character);
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
    public void setOnClickListener(ViewHolderUtils.SetOnClickListener clickListener) {
        this.listener = clickListener;
    }

    public interface SetOnClickListener extends ViewHolderUtils.SetOnClickListener {
        void onItemClick(int position);
    }

    public Cursor getCursor() {
        return cursor;
    }
}
