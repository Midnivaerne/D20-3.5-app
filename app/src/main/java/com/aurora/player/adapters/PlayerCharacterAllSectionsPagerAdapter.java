package com.aurora.player.adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.aurora.player.fragments.PlayerCharacterDescriptionsPlaceholderFragment;
import com.aurora.player.fragments.PlayerCharacterEquipmentPlaceholderFragment;
import com.aurora.player.fragments.PlayerCharacterFeaturesPlaceholderFragment;
import com.aurora.player.fragments.PlayerCharacterSkillsPlaceholderFragment;
import com.aurora.player.fragments.PlayerCharacterStatisticsPlaceholderFragment;
import com.aurora.player.viewmodels.PlayerCharacterVM;

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to one of the sections/tabs/pages.
 */
public class PlayerCharacterAllSectionsPagerAdapter extends FragmentPagerAdapter {

  private PlayerCharacterVM playerCharacterVM;

  public PlayerCharacterAllSectionsPagerAdapter(FragmentManager fragmentManager, PlayerCharacterVM playerCharacterVM) {
    super(fragmentManager,FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    this.playerCharacterVM = playerCharacterVM;
  }

  @Override
  public Fragment getItem(int position) {
    switch (position) {
      case 0:
        return PlayerCharacterStatisticsPlaceholderFragment.newInstance(position, playerCharacterVM);
      case 1:
        return PlayerCharacterSkillsPlaceholderFragment.newInstance(position, playerCharacterVM);
      case 2:
        return PlayerCharacterEquipmentPlaceholderFragment.newInstance(position, playerCharacterVM);
      case 3:
        return PlayerCharacterFeaturesPlaceholderFragment.newInstance(position, playerCharacterVM);
      case 4:
        return PlayerCharacterDescriptionsPlaceholderFragment.newInstance(position, playerCharacterVM);
      default:
        return null;
    }
  }

  @Override
  public int getCount() {
    return 5;
  }
}
