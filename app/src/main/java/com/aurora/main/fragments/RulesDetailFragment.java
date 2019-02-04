package com.aurora.main.fragments;

import com.aurora.d20_35_app.BR;
import com.aurora.d20_35_app.helper.BindingFragment;

public class RulesDetailFragment extends BindingFragment {
    public static String ARG_ITEM_ID;


    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return 0; //todo add R.layout.activity_rules_list_inner_detail_fragment; ?
    }

    @Override
    protected void setTranslatedTexts() {

    }
}
