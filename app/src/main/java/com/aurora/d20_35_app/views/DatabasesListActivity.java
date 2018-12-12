package com.aurora.d20_35_app.views;

import android.view.MenuItem;

import com.aurora.d20_35_app.BR;
import com.aurora.d20_35_app.R;
import com.aurora.d20_35_app.databinding.ActivityDatabasesListBinding;
import com.aurora.d20_35_app.helper.BindingActivity;
import com.aurora.d20_35_app.viewModels.DatabasesListVM;

import androidx.core.app.NavUtils;

/**
 * An activity representing a list of Rules Sets. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link DatabasesListDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class DatabasesListActivity extends BindingActivity<ActivityDatabasesListBinding, DatabasesListVM> {


    @Override
    public DatabasesListVM onCreate() {
        setSupportActionBar(findViewById(R.id.toolbar));
        //toolbar.setTitle(getTitle());
        return new DatabasesListVM(this);
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_databases_list;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. Use NavUtils to allow users
            // to navigate up one level in the application structure. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            NavUtils.navigateUpFromSameTask(this);
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
