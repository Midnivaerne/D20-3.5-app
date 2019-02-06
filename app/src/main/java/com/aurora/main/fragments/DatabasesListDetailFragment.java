package com.aurora.main.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.aurora.d20_35_app.BR;
import com.aurora.d20_35_app.R;
import com.aurora.d20_35_app.helper.BindingFragment;
import com.aurora.d20_35_app.models.Databases;
import com.aurora.main.views.DatabasesActivity;
import com.aurora.main.views.DatabasesListItemDetailActivity;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import static com.aurora.d20_35_app.database.DatabaseHolder.getDatabaseHolder;
import static com.aurora.d20_35_app.database.TranslationsHolder.translate;

/**
 * A fragment representing a single Rules set detail screen.
 * This fragment is either contained in a {@link DatabasesActivity}
 * in two-pane mode (on tablets) or a {@link DatabasesListItemDetailActivity}
 * on handsets.
 */
public class DatabasesListDetailFragment extends BindingFragment {

    public static final String ARG_ITEM_ID = "item_id";
    private Databases item;

    public DatabasesListDetailFragment() {
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_databases_list_inner_detail_fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            item = getDatabaseHolder(super.getContext()).DATABASES_LIST.get(Integer.parseInt(getArguments().getString(ARG_ITEM_ID)) - 1);
        }
    }

    @Override
    protected void setTranslatedTexts() {
        if (item != null) {
            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.databases_toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(translate(item.getName()));
            }
            ((TextView) getMRootView().findViewById(R.id.database_detail)).setText(translate(item.getName()));
        }
    }
}
