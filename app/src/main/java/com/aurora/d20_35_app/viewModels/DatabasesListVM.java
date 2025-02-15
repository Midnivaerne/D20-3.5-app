package com.aurora.d20_35_app.viewModels;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aurora.d20_35_app.R;
import com.aurora.d20_35_app.fragments.DatabasesListDetailFragment;
import com.aurora.d20_35_app.helper.ActivityViewModel;
import com.aurora.d20_35_app.helper.Item;
import com.aurora.d20_35_app.utilsDatabase.DatabaseHolder;
import com.aurora.d20_35_app.views.DatabasesListActivity;
import com.aurora.d20_35_app.views.DatabasesListDetailActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;
import lombok.NonNull;

public class DatabasesListVM extends ActivityViewModel<DatabasesListActivity> {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    @RequiresApi(api = Build.VERSION_CODES.N)
    public DatabasesListVM(DatabasesListActivity activity) {
        super(activity);
        showBackButton();

        FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        if (getActivity().findViewById(R.id.activity_databases_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }

        View recyclerView = getActivity().findViewById(R.id.activity_databases_list_inner);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(this.getActivity(), DatabaseHolder.getDatabaseHolder(super.getActivity()).getDatabasesList(), mTwoPane));
    }

    public static class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final DatabasesListActivity mParentActivity;
        private final List<String> mValues;
        private final boolean mTwoPane;
        private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Item item = (Item) view.getTag();
                if (mTwoPane) {
                    Bundle arguments = new Bundle();
                    arguments.putString(DatabasesListDetailFragment.ARG_ITEM_ID, String.valueOf(item.getItemID()));
                    DatabasesListDetailFragment fragment = new DatabasesListDetailFragment();
                    fragment.setArguments(arguments);
                    mParentActivity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.activity_databases_detail_container, fragment)
                            .commit();
                } else {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, DatabasesListDetailActivity.class);
                    intent.putExtra(DatabasesListDetailFragment.ARG_ITEM_ID, String.valueOf(item.getItemID()));

                    context.startActivity(intent);
                }
            }
        };

        SimpleItemRecyclerViewAdapter(DatabasesListActivity parent,
                                      List<String> items,
                                      boolean twoPane) {
            mValues = items;
            mParentActivity = parent;
            mTwoPane = twoPane;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.activity_databases_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mIdView.setText(mValues.get(position));
            holder.mContentView.setText(mValues.get(position));

            holder.itemView.setTag(mValues.get(position));
            holder.itemView.setOnClickListener(mOnClickListener);
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            final TextView mIdView;
            final TextView mContentView;

            ViewHolder(View view) {
                super(view);
                mIdView = (TextView) view.findViewById(R.id.id_database_text);
                mContentView = (TextView) view.findViewById(R.id.database_content);
            }
        }
    }
}
