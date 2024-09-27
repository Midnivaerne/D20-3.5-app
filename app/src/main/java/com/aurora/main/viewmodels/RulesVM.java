package com.aurora.main.viewmodels;

import static com.aurora.core.database.DatabaseHolder.getDatabaseHolder;

import lombok.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import com.aurora.core.R;
import com.aurora.core.helper.ActivityViewModel;
import com.aurora.core.database.models.helpers.Rules;
import com.aurora.main.fragments.RulesDetailFragment;
import com.aurora.main.views.RulesActivity;
import com.aurora.main.views.RulesDetailActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

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
    recyclerView
        .setAdapter(
            new SimpleItemRecyclerViewAdapter(this.getActivity(), getDatabaseHolder(super.getActivity()).getRulesList(), vmTwoPane));
  }

  public static class SimpleItemRecyclerViewAdapter
      extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

    private final RulesActivity rulesActivity;
    private final List<Rules> rulesList;
    private final boolean twoPaneMode;
    private final View.OnClickListener onClickListener = new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Rules item = (Rules) view.getTag();
        if (twoPaneMode) {
          Bundle arguments = new Bundle();
          arguments.putString(RulesDetailFragment.ARG_ITEM_ID, String.valueOf(item.getItemID()));
          RulesDetailFragment fragment = new RulesDetailFragment();
          fragment.setArguments(arguments);
          rulesActivity.getSupportFragmentManager().beginTransaction()
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
      rulesList = items;
      rulesActivity = parent;
      twoPaneMode = twoPane;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      View view = LayoutInflater.from(parent.getContext())
          .inflate(R.layout.rules_explanation_content, parent,
              false); //todo check rules_explanation_content vs activity_rules_list_content and other related xml files
      return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
      holder.idView.setText(rulesList.get(position).getName());
      holder.contentView.setText(rulesList.get(position).getContent());

      holder.itemView.setTag(rulesList.get(position));
      holder.itemView.setOnClickListener(onClickListener);
    }

    @Override
    public int getItemCount() {
      return rulesList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

      final TextView idView;
      final TextView contentView;

      ViewHolder(View view) {
        super(view);
        idView = (TextView) view.findViewById(R.id.id_explanation_text);
        contentView = (TextView) view.findViewById(R.id.id_explanation_content);
      }
    }
  }

}
