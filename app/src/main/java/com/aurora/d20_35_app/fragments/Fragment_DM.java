package com.aurora.d20_35_app.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aurora.d20_35_app.R;

public class Fragment_DM extends Fragment {

    private final String TAG = "com.aurora.d20_35_app.fragments.Fragment_DM";

    private Activity mActivity;

    public void onAttach(Activity act)
    {
        super.onAttach(act);

        this.mActivity = act;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_dm, container, false);

        //do whatever you want here - like adding a listview or anything

        return view;
    }
}
