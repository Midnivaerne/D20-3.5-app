package com.aurora.d20_35_app.fragments;

import com.aurora.d20_35_app.viewModels.PlayerCharacterVM;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class PlayerCharacterAllSectionsPagerAdapter extends FragmentPagerAdapter {

    private PlayerCharacterVM playerCharacterVM;

    public PlayerCharacterAllSectionsPagerAdapter(FragmentManager fragmentManager, PlayerCharacterVM playerCharacterVM) {
        super(fragmentManager);
        this.playerCharacterVM = playerCharacterVM;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return PlayerCharacterFirstPlaceholderFragment.newInstance(position, playerCharacterVM);
            case 1:
                return PlayerCharacterSecondPlaceholderFragment.newInstance(position, playerCharacterVM);
            case 2:
                return PlayerCharacterThirdPlaceholderFragment.newInstance(position, playerCharacterVM);
            case 3:
                return PlayerCharacterFourthPlaceholderFragment.newInstance(position, playerCharacterVM);
            case 4:
                return PlayerCharacterDescriptionPlaceholderFragment.newInstance(position, playerCharacterVM);
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 5;
    }
}
