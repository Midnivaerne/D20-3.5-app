package com.aurora.main.viewModels;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aurora.d20_35_app.R;
import com.aurora.main.fragments.RulesDetailFragment;
import com.aurora.d20_35_app.helper.ActivityViewModel;
import com.aurora.d20_35_app.helper.Rules;
import com.aurora.main.views.RulesActivity;
import com.aurora.main.views.RulesDetailActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import lombok.NonNull;

import static com.aurora.d20_35_app.utils.database.DatabaseHolder.getDatabaseHolder;

public class RulesVM extends ActivityViewModel<RulesActivity> {

    public RulesVM(RulesActivity activity) {
        super(activity);
        showBackButton();
        checkTwoPane(R.id.activity_rules_explanation_container);

        //todo load rules category

        FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.scrollTo(0, 0);//TODO implement mvvm version
            }
        });

        View recyclerView = getActivity().findViewById(R.id.activity_rules_list_inner);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(this.getActivity(), getDatabaseHolder(super.getActivity()).getRulesList(), mTwoPane));
    }

    public static class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final RulesActivity mParentActivity;
        private final List<Rules> mValues;
        private final boolean mTwoPane;
        private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Rules item = (Rules) view.getTag();
                if (mTwoPane) {
                    Bundle arguments = new Bundle();
                    arguments.putString(RulesDetailFragment.ARG_ITEM_ID, String.valueOf(item.getItemID()));
                    RulesDetailFragment fragment = new RulesDetailFragment();
                    fragment.setArguments(arguments);
                    mParentActivity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.activity_rules_explanation_container, fragment)
                            .commit();
                } else {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, RulesDetailActivity.class);
                    intent.putExtra(RulesDetailFragment.ARG_ITEM_ID, String.valueOf(item.getItemID()));

                    context.startActivity(intent);
                }
            }
        };

        SimpleItemRecyclerViewAdapter(RulesActivity parent,
                                      List<Rules> items,
                                      boolean twoPane) {
            mValues = items;
            mParentActivity = parent;
            mTwoPane = twoPane;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.rules_explanation_content, parent, false); //todo check rules_explanation_content vs activity_rules_list_content and other related xml files
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mIdView.setText(mValues.get(position).getName());
            holder.mContentView.setText(mValues.get(position).getContent());

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
                mIdView = (TextView) view.findViewById(R.id.id_explanation_text);
                mContentView = (TextView) view.findViewById(R.id.id_explanation_content);
            }
        }
    }

}
