package com.aurora.d20_35_app.views;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aurora.d20_35_app.R;
import com.aurora.d20_35_app.models.DatabaseManager;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import androidx.fragment.app.Fragment;

/**
 * A fragment representing a single Rules set detail screen.
 * This fragment is either contained in a {@link DatabasesListActivity}
 * in two-pane mode (on tablets) or a {@link DatabasesListDetailActivity}
 * on handsets.
 */
public class DatabasesListDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private DatabaseManager.ADatabase aDatabase;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public DatabasesListDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            aDatabase = DatabaseManager.databasesMap.get(getArguments().getString(ARG_ITEM_ID));

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(aDatabase.getContent());
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_databases_list_inner_detail_fragment, container, false);

        if (aDatabase != null) {
            ((TextView) rootView.findViewById(R.id.rulesset_detail)).setText(aDatabase.getDetails());
        }

        return rootView;
    }
}
