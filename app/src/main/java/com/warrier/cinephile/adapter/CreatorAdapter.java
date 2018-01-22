package com.warrier.cinephile.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.warrier.cinephile.R;
import com.warrier.cinephile.utils.Constants;
import com.warrier.cinephile.utils.ViewHolderUtils;

/**
 * Created by Galahad on 17/11/2017.
 */

public class CreatorAdapter  extends
        RecyclerView.Adapter<CreatorAdapter.ViewHolder> {
    private static final String LOG_TAG = CreatorAdapter.class.getSimpleName();

    private final Cursor cursor;
    private ViewHolderUtils.SetOnClickListener listener;

    public CreatorAdapter(Cursor cursor) {
        this.cursor = cursor;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.list_crew_movie, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        cursor.moveToPosition(position);
        final String crew_name = cursor.getString(Constants.CREATOR_COL_NAME);
        final String crew_department = "Creator";
        holder.name.setText(crew_name);
        holder.department.setText(crew_department);
        holder.setItemClickListener(listener);
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

        final TextView name;
        final TextView department;
        private ViewHolderUtils.SetOnClickListener listener;

        ViewHolder(final View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.crew_name);
            department = (TextView) view.findViewById(R.id.crew_department);
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
