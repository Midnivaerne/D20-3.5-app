package com.aurora.d20_35_app.views;

import com.aurora.d20_35_app.BR;
import com.aurora.d20_35_app.R;
import com.aurora.d20_35_app.databinding.ActivityDatabasesBinding;
import com.aurora.d20_35_app.helper.BindingActivity;
import com.aurora.d20_35_app.viewModels.DatabasesVM;

import static com.aurora.d20_35_app.utils.database.TranslationsHolder.translate;

/**
 * An activity representing a list of Rules Sets. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link DatabasesListItemDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class DatabasesActivity extends BindingActivity<ActivityDatabasesBinding, DatabasesVM> {


    @Override
    public DatabasesVM onCreate() {
        setSupportActionBar(findViewById(R.id.toolbar));
        return new DatabasesVM(this);
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_databases;
    }

    @Override
    public void onFragmentAttached() {
    }

    @Override
    public void onFragmentDetached(String tag) {
    }

    @Override
    protected void setTranslatedTexts() {
        getSupportActionBar().setTitle(translate("title_rulesset_list"));
    }
}
