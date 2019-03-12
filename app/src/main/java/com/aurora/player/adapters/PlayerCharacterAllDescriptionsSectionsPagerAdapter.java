package com.aurora.player.adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.aurora.player.fragments.PlayerCharacterDescriptionAppearancePlaceholderFragment;
import com.aurora.player.fragments.PlayerCharacterDescriptionJournalPlaceholderFragment;
import com.aurora.player.fragments.PlayerCharacterDescriptionQuestsPlaceholderFragment;
import com.aurora.player.viewModels.PlayerCharacterVM;

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to one of the sections/tabs/pages.
 */
public class PlayerCharacterAllDescriptionsSectionsPagerAdapter extends FragmentPagerAdapter {

  private PlayerCharacterVM playerCharacterVM;

  public PlayerCharacterAllDescriptionsSectionsPagerAdapter(FragmentManager fragmentManager, PlayerCharacterVM playerCharacterVM) {
    super(fragmentManager);
    this.playerCharacterVM = playerCharacterVM;
  }

  @Override
  public Fragment getItem(int position) {
    switch (position) {
      case 0:
        return PlayerCharacterDescriptionAppearancePlaceholderFragment.newInstance(position, playerCharacterVM);
      case 1:
        return PlayerCharacterDescriptionQuestsPlaceholderFragment.newInstance(position, playerCharacterVM);
      case 2:
        return PlayerCharacterDescriptionJournalPlaceholderFragment.newInstance(position, playerCharacterVM);
      default:
        return null;
    }
  }

  @Override
  public int getCount() {
    return 3;
  }
}
