package com.aurora.player.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import com.aurora.d20_35_app.R;
import com.aurora.player.viewModels.PlayerCharacterVM;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlayerCharacterEquipmentPlaceholderFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    public PlayerCharacterEquipmentPlaceholderFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static PlayerCharacterEquipmentPlaceholderFragment newInstance(int sectionNumber, PlayerCharacterVM playerCharacterVM) {
        PlayerCharacterEquipmentPlaceholderFragment fragment = new PlayerCharacterEquipmentPlaceholderFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_player_character_equipment, container, false);
        loadHeroDataFromVMtoView(rootView);
        return rootView;
    }

    private void loadHeroDataFromVMtoView(View rootView) {
    }
}
