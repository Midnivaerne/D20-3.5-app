package com.aurora.player.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aurora.d20_35_app.R;
import com.aurora.player.viewModels.PlayerCharacterVM;

import androidx.fragment.app.Fragment;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlayerCharacterStatisticsPlaceholderFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    private PlayerCharacterVM playerCharacterVM;

    public PlayerCharacterStatisticsPlaceholderFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static PlayerCharacterStatisticsPlaceholderFragment newInstance(int sectionNumber, PlayerCharacterVM playerCharacterVM) {
        PlayerCharacterStatisticsPlaceholderFragment fragment = new PlayerCharacterStatisticsPlaceholderFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        fragment.playerCharacterVM = playerCharacterVM;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_player_character_statistics, container, false);
        loadHeroDataFromVMtoView(rootView);
        return rootView;
    }

    private void loadHeroDataFromVMtoView(View rootView) {
    }
}
