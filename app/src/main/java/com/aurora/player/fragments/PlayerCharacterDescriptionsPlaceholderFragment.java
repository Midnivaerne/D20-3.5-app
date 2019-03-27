package com.aurora.player.fragments;

import lombok.Getter;
import lombok.Setter;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aurora.core.R;
import com.aurora.player.adapters.CustomTabChangeListener;
import com.aurora.player.adapters.CustomTabSelectionListener;
import com.aurora.player.adapters.PlayerCharacterAllDescriptionsSectionsPagerAdapter;
import com.aurora.player.viewmodels.PlayerCharacterVM;
import com.google.android.material.tabs.TabLayout;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlayerCharacterDescriptionsPlaceholderFragment extends Fragment {

  /**
   * The fragment argument representing the section number for this fragment.
   */
  private static final String ARG_SECTION_NUMBER = "section_number";
  @Setter
  @Getter
  private PlayerCharacterVM playerCharacterVM;
  private ViewPager innerViewPager;
  private TabLayout innerTabLayout;
  private PlayerCharacterAllDescriptionsSectionsPagerAdapter descriptionsSectionsPagerAdapter;

  public PlayerCharacterDescriptionsPlaceholderFragment() {
  }

  /**
   * Returns a new instance of this fragment for the given section number.
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
    descriptionsSectionsPagerAdapter = new PlayerCharacterAllDescriptionsSectionsPagerAdapter(getChildFragmentManager(),
        playerCharacterVM);
    innerTabLayout = (TabLayout) playerCharacterVM.getActivity().findViewById(R.id.player_character_description_tabs);
    innerViewPager = (ViewPager) playerCharacterVM.getActivity().findViewById(R.id.player_character_description_container);
    innerViewPager.setAdapter(descriptionsSectionsPagerAdapter);
    innerViewPager.addOnPageChangeListener(new CustomTabChangeListener(innerTabLayout));
    innerTabLayout.addOnTabSelectedListener(new CustomTabSelectionListener(innerViewPager));
  }


}
