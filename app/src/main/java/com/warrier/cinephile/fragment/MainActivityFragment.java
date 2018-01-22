package com.warrier.cinephile.fragment;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.warrier.cinephile.MainActivity;
import com.warrier.cinephile.R;
import com.warrier.cinephile.adapter.MovieArrayAdapter;
import com.warrier.cinephile.asynctask.FetchMovieTask;
import com.warrier.cinephile.data.MovieContract;
import com.warrier.cinephile.model.MovieInfo;
import com.warrier.cinephile.utils.AsyncResponse;
import com.warrier.cinephile.utils.Constants;
import com.warrier.cinephile.utils.Utility;

import java.util.ArrayList;

/**
 * Created by Galahad on 17/11/2017.
 */

public class MainActivityFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>, AsyncResponse

    {

    private static final String SELECTED_KEY = "selected_position";
    private static final int MOVIE_LOADER = 0;
    private MovieArrayAdapter listAdapter;
    private GridView gridView;
    private ArrayList<MovieInfo> movieList;
    private SwipeRefreshLayout swipeRefreshLayout;
    private View rootView;
    private String MODE;
    private boolean isMovie;
    private boolean isBookmark;
        private int mPosition = ListView.INVALID_POSITION;


    private void updateMovieList() {
        FetchMovieTask weatherTask = new FetchMovieTask(getActivity());
        weatherTask.response = this;
        weatherTask.execute(MODE);
    }


      @Override
        public void onSaveInstanceState(Bundle outState) {

            outState.putParcelableArrayList("movieList", movieList);
            outState.putInt(SELECTED_KEY, gridView.getFirstVisiblePosition());
            super.onSaveInstanceState(outState);
        }




      @Override
      public void onViewStateRestored(@Nullable Bundle state){
        super.onViewStateRestored(state);
        if(state != null){
            movieList = state.getParcelableArrayList("movieList");
            int pos = state.getInt(SELECTED_KEY,mPosition);
            gridView.smoothScrollToPosition(pos);
        }
      }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null || !savedInstanceState.containsKey("movieList")) {
            movieList = new ArrayList<>();
        } else {
            movieList = savedInstanceState.getParcelableArrayList("movieList");
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        loadArguments();
        if (!isBookmark) {
            reLoadData();
            getLoaderManager().restartLoader(MOVIE_LOADER, null, this);
        } else if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setEnabled(false);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getActivity().getWindow().setStatusBarColor(ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark));
        }
    }

    public void loadArguments() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            MODE = arguments.getString(Intent.EXTRA_TEXT);
        }

        if (MODE != null) {
            switch (MODE) {
                case MainActivity.FRAGMENT_TAG_MOVIE_BOOKMARKS:
                    isBookmark = true;
                    isMovie = true;
                    break;

                case MainActivity.FRAGMENT_TAG_MOV_NOW_PLAYING:
                case MainActivity.FRAGMENT_TAG_MOV_POPULAR:
                case MainActivity.FRAGMENT_TAG_MOV_TOP_RATED:
                case MainActivity.FRAGMENT_TAG_MOV_UPCOMING:
                    isMovie = true;
                    isBookmark = false;
                    break;

                    default:
                    isMovie = false;
                    isBookmark = false;

                    break;
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final Activity mActivity = getActivity();

        rootView = inflater.inflate(R.layout.fragment_main, container, false);
        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.main_swipe_refresh);

        Toolbar toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);

        DrawerLayout drawer = (DrawerLayout) mActivity.findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                mActivity, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();



        loadArguments();

        if ((isBookmark) && swipeRefreshLayout != null) {
            swipeRefreshLayout.setEnabled(false);
        }

        toolbar.setTitle(MODE);

        listAdapter =
                new MovieArrayAdapter(
                        getActivity(), isMovie, isBookmark,  null, 0);
        // Get a reference to the GridView, and attach this adapter to it.
        gridView = (GridView) rootView.findViewById(R.id.gridview_movie);
        gridView.setAdapter(listAdapter);

        if (mActivity != null) {
            gridView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {

                    final int size = gridView.getWidth();
                    int numCol = (int) Math.round((double) size / (double) mActivity.getResources().getDimensionPixelSize(R.dimen.poster_width));
                    gridView.setNumColumns(numCol);
                }
            });
        }

        final Fragment current = this;

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                Cursor cursor = (Cursor) adapterView.getItemAtPosition(position);
                if (cursor != null) {
                    ImageView imageView = (ImageView) view.findViewById(R.id.grid_item_poster);

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        imageView.setTransitionName("TRANS_NAME" + position);
                    }

                    ((Callback) getActivity())
                            .onItemSelected(cursor.getString(isBookmark ? Constants.MOV_DETAILS_COL_MOVIE_ID : Constants.MOV_COL_MOVIE_ID ), imageView, current, isMovie);
                }
                mPosition = position;
            }
        });

        if (savedInstanceState != null && savedInstanceState.containsKey(SELECTED_KEY)) {

            mPosition = savedInstanceState.getInt(SELECTED_KEY);
        }


        return rootView;
    }

    private void reLoadData() {
        Activity mActivity = getActivity();
        if (mActivity != null) {
            final boolean PREF_CHILD;
            final String PREF_LANGUAGE;
            final String PREF_REGION;

            String KEY_ADULT = "child_mode";
            String KEY_LANGUAGE = "language";
            String KEY_REGION = "region";

            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mActivity);
            PREF_CHILD = sharedPreferences.getBoolean(KEY_ADULT, true);
            PREF_LANGUAGE = sharedPreferences.getString(KEY_LANGUAGE, "");
            PREF_REGION = sharedPreferences.getString(KEY_REGION, "");
            int count = 0;

            Uri Uri = MovieContract.Movies.buildMovieUri();
            Cursor cursor = mActivity.getContentResolver().query(Uri,
                    Constants.MOVIE_COLUMNS_MIN,
                    MovieContract.Movies.MODE + " = ? AND " + MovieContract.Movies.PREF_ADULT + " = ? AND " + MovieContract.Movies.PREF_LANGUAGE + " = ? AND " + MovieContract.Movies.PREF_REGION + " = ? ",
                    new String[]{MODE, String.valueOf(!PREF_CHILD), PREF_LANGUAGE, PREF_REGION},
                    null);
            if (cursor != null) {
                count = cursor.getCount();
                cursor.close();
            }

            if (count == 0) {
                if (!Utility.hasNetworkConnection(getActivity())) {
                    Toast.makeText(getContext(), "Network Not Available!", Toast.LENGTH_LONG).show();
                } else {
                    swipeRefreshLayout.post(new Runnable() {
                        @Override
                        public void run() {
                            swipeRefreshLayout.setRefreshing(true);
                        }
                    });
                    updateMovieList();
                }
            }

            swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    if (Utility.hasNetworkConnection(getActivity())) {
                        getActivity().getContentResolver().delete(MovieContract.Movies.CONTENT_URI,
                                MovieContract.Movies.MODE + " = ? AND " + MovieContract.Movies.PREF_ADULT + " = ? AND " + MovieContract.Movies.PREF_LANGUAGE + " = ? AND " + MovieContract.Movies.PREF_REGION + " = ? ",
                                new String[]{MODE, String.valueOf(!PREF_CHILD), PREF_LANGUAGE, PREF_REGION});
                        updateMovieList();
                    } else {
                        Toast.makeText(getContext(), "Network Not Available!", Toast.LENGTH_SHORT).show();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }
            });
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        getLoaderManager().initLoader(MOVIE_LOADER, null, this);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {

        if (isBookmark) {
            Uri Uri = MovieContract.MovieDetails.buildMovieDetailsUri();
            return new CursorLoader(getActivity(),
                    Uri,
                    Constants.MOVIE_DETAILS_COLUMNS,
                    MovieContract.MovieDetails.FAVOURED + " = ? ",
                    new String[]{"1"},
                    " CAST ( " + MovieContract.Movies._ID + " AS REAL ) ASC");
        }


        final boolean PREF_CHILD;
        final String PREF_LANGUAGE;
        final String PREF_REGION;

        String KEY_ADULT = "child_mode";
        String KEY_LANGUAGE = "language";
        String KEY_REGION = "region";
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        PREF_CHILD = sharedPreferences.getBoolean(KEY_ADULT, true);
        PREF_LANGUAGE = sharedPreferences.getString(KEY_LANGUAGE, "");
        PREF_REGION = sharedPreferences.getString(KEY_REGION, "");

        Uri Uri = MovieContract.Movies.buildMovieUri();
        return new CursorLoader(getActivity(),
                Uri,
                Constants.MOVIE_COLUMNS,
                MovieContract.Movies.MODE + " = ? AND " + MovieContract.Movies.PREF_ADULT + " = ? AND " + MovieContract.Movies.PREF_LANGUAGE + " = ? AND " + MovieContract.Movies.PREF_REGION + " = ? ",
                new String[]{MODE, String.valueOf(!PREF_CHILD), PREF_LANGUAGE, PREF_REGION},
                " CAST ( " + MovieContract.Movies.POPULARITY + " AS REAL ) DESC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        listAdapter.swapCursor(cursor);
        swipeRefreshLayout.setRefreshing(false);
        if (mPosition != ListView.INVALID_POSITION) {
            // If we don't need to restart the loader, and there's a desired position to restore
            // to, do so now.
            gridView.post(new Runnable() {
                @Override
                public void run() {
                    gridView.setSelection(mPosition);
                    View v = gridView.getChildAt(mPosition);
                    if (v != null) {
                        v.requestFocus();
                    }
                }
            });
        }
        try {
            TextView info = (TextView) rootView.findViewById(R.id.empty);
            if (listAdapter.getCount() == 0) {
                if (isBookmark) {
                    info.setText(R.string.bookmarks_empty);
                } else {
                    info.setText(R.string.loading);
                }
                info.setVisibility(View.VISIBLE);
            } else {
                info.setVisibility(View.GONE);
            }
        } catch (Exception ignored) {
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {
        listAdapter.swapCursor(null);
    }

    @Override
    public void onFinish(boolean isData) {
        swipeRefreshLayout.setRefreshing(false);
        listAdapter.notifyDataSetChanged();
        if (!isData) {
            TextView info = (TextView) rootView.findViewById(R.id.empty);
            if (listAdapter.getCount() == 0) {
                info.setText(R.string.no_data);
                info.setVisibility(View.VISIBLE);
            } else {
                info.setVisibility(View.GONE);
            }
        }
    }


    public interface Callback {

        void onItemSelected(String movieUri, ImageView view, Fragment current, boolean isMovie);
    }
}
