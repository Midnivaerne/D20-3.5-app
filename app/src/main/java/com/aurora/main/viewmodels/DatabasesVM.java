package com.aurora.main.viewmodels;

import static com.aurora.core.database.DatabaseHolder.getDatabaseHolder;
import static com.aurora.core.database.TranslationsHolder.translate;

import lombok.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
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
import com.aurora.core.database.models.Databases;
import com.aurora.main.fragments.DatabasesListDetailFragment;
import com.aurora.main.views.DatabasesActivity;
import com.aurora.main.views.DatabasesListItemDetailActivity;
import com.google.android.material.snackbar.Snackbar;

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
    recyclerView
        .setAdapter(new DatabasesRecyclerViewAdapter(this.getActivity(), getDatabaseHolder(super.getActivity()).databasesList, vmTwoPane));
  }

  public static class DatabasesRecyclerViewAdapter extends RecyclerView.Adapter<DatabasesRecyclerViewAdapter.ViewHolder> {

    private final DatabasesActivity parentDatabaseActivity;
    private final List<Databases> databasesList;
    private final boolean twoPaneMode;
    private final View.OnClickListener onClickListener = new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Databases databases = (Databases) view.getTag();
        if (twoPaneMode) {
          Bundle arguments = new Bundle();
          arguments.putString(DatabasesListDetailFragment.ARG_ITEM_ID, String.valueOf(databases.getItemID()));
          DatabasesListDetailFragment fragment = new DatabasesListDetailFragment();
          fragment.setArguments(arguments);
          parentDatabaseActivity.getSupportFragmentManager().beginTransaction()
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
      databasesList = items;
      parentDatabaseActivity = parent;
      twoPaneMode = twoPane;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      View view = LayoutInflater.from(parent.getContext())
          .inflate(R.layout.activity_databases_list_item, parent, false);
      return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
      holder.idView.setText(translate(databasesList.get(position).getName()));
      holder.contentView.setText(translate(databasesList.get(position).getContent()));

      holder.itemView.setTag(databasesList.get(position));
      holder.itemView.setOnClickListener(onClickListener);
    }

    @Override
    public int getItemCount() {
      return databasesList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

      final TextView idView;
      final TextView contentView;

      ViewHolder(View view) {
        super(view);
        idView = (TextView) view.findViewById(R.id.id_database_text);
        contentView = (TextView) view.findViewById(R.id.database_content);
      }
    }
  }
}
