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
import com.warrier.cinephile.utils.RoundedTransformation;
import com.warrier.cinephile.utils.ViewHolderUtils;

import static com.warrier.cinephile.utils.Constants.COL_VIDEOS_KEY;
import static com.warrier.cinephile.utils.Constants.COL_VIDEOS_NAME;

/**
 * Created by Galahad on 17/11/2017.
 */

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder>  {

    private final Cursor cursor;
    private Context context;
    private ViewHolderUtils.SetOnClickListener listener;

    public VideoAdapter(Cursor cursor) {
        this.cursor = cursor;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.list_video_movie, parent, false);

        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int position) {
        cursor.moveToPosition(position);
        final String trailer_name = cursor.getString(COL_VIDEOS_NAME);
        final String source = cursor.getString(COL_VIDEOS_KEY);
        viewHolder.trailer.setText(trailer_name);
        final String BASE_URL = "http://img.youtube.com/vi/";
        final String url = BASE_URL + source + "/0.jpg";

        Picasso
                .with(context)
                .load(url)
                .networkPolicy(NetworkPolicy.OFFLINE)
                .transform(new RoundedTransformation(10, 10))
                .fit()
                .centerCrop()
                .into(viewHolder.trailerImg, new Callback() {
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
                                .into(viewHolder.trailerImg, new Callback() {
                                    @Override
                                    public void onSuccess() {
                                    }

                                    @Override
                                    public void onError() {
                                    }
                                });
                    }
                });
        viewHolder.trailerImg.setAdjustViewBounds(true);
        viewHolder.setItemClickListener(listener);


    }
    public Cursor getCursor() {
        return cursor;
    }

    public void setOnClickListener(ViewHolderUtils.SetOnClickListener clickListener) {
        this.listener = clickListener;
    }

    @Override
    public int getItemCount() {
        if (cursor != null) {
            return cursor.getCount();
        }
        return 0;
    }

    public interface SetOnClickListener extends ViewHolderUtils.SetOnClickListener {
        void onItemClick(int position);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        final TextView trailer;
        final ImageView trailerImg;
        private ViewHolderUtils.SetOnClickListener listener;

        ViewHolder(final View view) {
            super(view);
            trailer = (TextView) view.findViewById(R.id.trailer_name);
            trailerImg = (ImageView) view.findViewById(R.id.youtubeImg);
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
