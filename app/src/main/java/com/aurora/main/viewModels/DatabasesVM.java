package com.aurora.main.viewModels;

import static com.aurora.d20_35_app.database.DatabaseHolder.getDatabaseHolder;
import static com.aurora.d20_35_app.database.TranslationsHolder.translate;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.aurora.d20_35_app.R;
import com.aurora.d20_35_app.helper.ActivityViewModel;
import com.aurora.d20_35_app.models.Databases;
import com.aurora.main.fragments.DatabasesListDetailFragment;
import com.aurora.main.views.DatabasesActivity;
import com.aurora.main.views.DatabasesListItemDetailActivity;
import com.google.android.material.snackbar.Snackbar;
import java.util.List;
import lombok.NonNull;

public class DatabasesVM extends ActivityViewModel<DatabasesActivity> {


    public DatabasesVM(DatabasesActivity activity) {
        super(activity);
        showBackButton();
        checkTwoPane(R.id.activity_databases_detail_container);

        View recyclerView = getActivity().findViewById(R.id.activity_databases_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);
    }

    public void addDatabaseOnClick(View view, Activity activity) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new DatabasesRecyclerViewAdapter(this.getActivity(), getDatabaseHolder(super.getActivity()).DATABASES_LIST, mTwoPane));
    }

    public static class DatabasesRecyclerViewAdapter extends RecyclerView.Adapter<DatabasesRecyclerViewAdapter.ViewHolder> {

        private final DatabasesActivity mParentActivity;
        private final List<Databases> mValues;
        private final boolean mTwoPane;
        private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Databases databases = (Databases) view.getTag();
                if (mTwoPane) {
                    Bundle arguments = new Bundle();
                    arguments.putString(DatabasesListDetailFragment.ARG_ITEM_ID, String.valueOf(databases.getItemID()));
                    DatabasesListDetailFragment fragment = new DatabasesListDetailFragment();
                    fragment.setArguments(arguments);
                    mParentActivity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.activity_databases_detail_container, fragment)
                            .commit();
                } else {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, DatabasesListItemDetailActivity.class);
                    intent.putExtra(DatabasesListDetailFragment.ARG_ITEM_ID, String.valueOf(databases.getItemID()));

                    context.startActivity(intent);
                }
            }
        };

        DatabasesRecyclerViewAdapter(DatabasesActivity parent,
                                     List<Databases> items,
                                     boolean twoPane) {
            mValues = items;
            mParentActivity = parent;
            mTwoPane = twoPane;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.activity_databases_list_item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mIdView.setText(translate(mValues.get(position).getName()));
            holder.mContentView.setText(translate(mValues.get(position).getContent()));

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
