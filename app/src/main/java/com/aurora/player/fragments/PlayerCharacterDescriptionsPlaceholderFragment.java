package com.aurora.player.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aurora.d20_35_app.R;
import com.aurora.player.adapters.CustomTabChangeListener;
import com.aurora.player.adapters.CustomTabSelectionListener;
import com.aurora.player.adapters.PlayerCharacterAllDescriptionsSectionsPagerAdapter;
import com.aurora.player.viewModels.PlayerCharacterVM;
import com.google.android.material.tabs.TabLayout;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import lombok.Getter;
import lombok.Setter;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlayerCharacterDescriptionsPlaceholderFragment extends Fragment {

    @Setter
    @Getter
    private PlayerCharacterVM playerCharacterVM;
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    private ViewPager mInnerViewPager;
    private TabLayout mInnerTabLayout;
    private PlayerCharacterAllDescriptionsSectionsPagerAdapter mDescriptionsSectionsPagerAdapter;

    public PlayerCharacterDescriptionsPlaceholderFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static PlayerCharacterDescriptionsPlaceholderFragment newInstance(int sectionNumber, PlayerCharacterVM playerCharacterVM) {
        PlayerCharacterDescriptionsPlaceholderFragment fragment = new PlayerCharacterDescriptionsPlaceholderFragment();
        fragment.setPlayerCharacterVM(playerCharacterVM);
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_player_character_description, container, false);
        loadHeroDataFromVMtoView(rootView);
        return rootView;
    }

    private void loadHeroDataFromVMtoView(View rootView) {
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        setInnerTabs();
    }

    private void setInnerTabs() {
        mDescriptionsSectionsPagerAdapter = new PlayerCharacterAllDescriptionsSectionsPagerAdapter(getChildFragmentManager(), playerCharacterVM);
        mInnerTabLayout = (TabLayout) playerCharacterVM.getActivity().findViewById(R.id.player_character_description_tabs);
        mInnerViewPager = (ViewPager) playerCharacterVM.getActivity().findViewById(R.id.player_character_description_container);
        mInnerViewPager.setAdapter(mDescriptionsSectionsPagerAdapter);
        mInnerViewPager.addOnPageChangeListener(new CustomTabChangeListener(mInnerTabLayout));
        mInnerTabLayout.addOnTabSelectedListener(new CustomTabSelectionListener(mInnerViewPager));
    }


}
