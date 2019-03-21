package com.aurora.main.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import com.aurora.core.BR;
import com.aurora.core.R;
import com.aurora.core.databinding.ActivityDatabasesListItemDetailBinding;
import com.aurora.core.helper.BindingActivity;
import com.aurora.main.fragments.DatabasesListDetailFragment;
import com.aurora.main.viewmodels.DatabasesListItemDetailVM;

/**
 * An activity representing a single Rules set detail screen. This activity is only used on narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items in a {@link DatabasesActivity}.
 */
public class DatabasesListItemDetailActivity extends BindingActivity<ActivityDatabasesListItemDetailBinding, DatabasesListItemDetailVM> {

  @Override
  public DatabasesListItemDetailVM onCreate() { //todo move it?
    setSupportActionBar(findViewById(R.id.activity_databases_list_frame_item_detail_toolbar));

    if (getIsSavedInstanceStateNull()) {
      Bundle arguments = new Bundle();
      arguments.putString(DatabasesListDetailFragment.ARG_ITEM_ID,
          getIntent().getStringExtra(DatabasesListDetailFragment.ARG_ITEM_ID));
      DatabasesListDetailFragment fragment = new DatabasesListDetailFragment();
      fragment.setArguments(arguments);
      getSupportFragmentManager().beginTransaction()
          .add(R.id.activity_databases_item_detail_container, fragment)
          .commit();
    }
    return new DatabasesListItemDetailVM(this);
  }

  @Override
  public int getBindingVariable() {
    return BR.viewModel;
  }

  @Override
  public int getLayoutId() {
    return R.layout.activity_databases_list_item_detail;
  }

  @Override
  protected void setTranslatedTexts() {

  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();
    if (id == android.R.id.home) {
      navigateUpTo(new Intent(this, DatabasesActivity.class));
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  @Override
  public void onFragmentAttached() {

  }

  @Override
  public void onFragmentDetached(String tag) {

  }
}
