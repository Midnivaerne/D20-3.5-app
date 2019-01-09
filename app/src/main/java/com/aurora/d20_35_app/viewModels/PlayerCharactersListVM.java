package com.aurora.d20_35_app.viewModels;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aurora.d20_35_app.R;
import com.aurora.d20_35_app.fragments.PlayerCharactersListDetailFragment;
import com.aurora.d20_35_app.helper.ActivityViewModel;
import com.aurora.d20_35_app.models.Hero;
import com.aurora.d20_35_app.views.PlayerCharactersListActivity;
import com.aurora.d20_35_app.views.PlayerCharactersListFrameItemDetailActivity;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

import static com.aurora.d20_35_app.utilsDatabase.DatabaseHolder.getDatabaseHolder;
import static com.aurora.d20_35_app.utilsDatabase.TranslationsHolder.translate;

public class PlayerCharactersListVM extends ActivityViewModel<PlayerCharactersListActivity> {
    public PlayerCharactersListVM(PlayerCharactersListActivity activity) {
        super(activity);
        showBackButton();
        checkTwoPane(R.id.activity_player_characters_list_frame_horizontal_container);

        View recyclerView = getActivity().findViewById(R.id.activity_player_characters_list_frame_view);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);
    }

    public void addCharacterButtonOnClick(View view, Activity activity) {
        Snackbar.make(view, "Create new hero", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }


    private void setupRecyclerView(RecyclerView recyclerView) {
        recyclerView.setAdapter(new PCRecyclerViewAdapter(this.getActivity(), getDatabaseHolder(super.getActivity()).HEROES_LIST, mTwoPane));
    }

    public static class PCRecyclerViewAdapter extends RecyclerView.Adapter<PCRecyclerViewAdapter.ViewHolder> {

        private final PlayerCharactersListActivity mParentActivity;
        private final List<Hero> mValues;
        private final boolean mTwoPane;
        private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Hero hero = (Hero) view.getTag();
                if (mTwoPane) {
                    Bundle arguments = new Bundle();
                    arguments.putString(PlayerCharactersListDetailFragment.ARG_ITEM_ID, String.valueOf(hero.getItemID()));
                    PlayerCharactersListDetailFragment fragment = new PlayerCharactersListDetailFragment();
                    fragment.setArguments(arguments);
                    mParentActivity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.activity_player_characters_list_frame_horizontal_container, fragment)
                            .commit();
                } else {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, PlayerCharactersListFrameItemDetailActivity.class);
                    intent.putExtra(PlayerCharactersListDetailFragment.ARG_ITEM_ID, String.valueOf(hero.getItemID()));

                    context.startActivity(intent);
                }
            }
        };

        PCRecyclerViewAdapter(PlayerCharactersListActivity parent,
                              List<Hero> items,
                              boolean twoPane) {
            mValues = items;
            mParentActivity = parent;
            mTwoPane = twoPane;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.activity_player_characters_list_frame_item, parent, false);
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
                mIdView = (TextView) view.findViewById(R.id.id_pc_text);
                mContentView = (TextView) view.findViewById(R.id.pc_content);
            }
        }
    }

}
