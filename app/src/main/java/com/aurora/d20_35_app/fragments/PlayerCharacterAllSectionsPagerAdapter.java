package com.aurora.d20_35_app.fragments;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class PlayerCharacterAllSectionsPagerAdapter extends FragmentPagerAdapter {

    public PlayerCharacterAllSectionsPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return PlayerCharacterFirstPlaceholderFragment.newInstance(position);
            case 1:
                return PlayerCharacterSecondPlaceholderFragment.newInstance(position);
            case 2:
                return PlayerCharacterThirdPlaceholderFragment.newInstance(position);
            case 3:
                return PlayerCharacterFourthPlaceholderFragment.newInstance(position);
            case 4:
                return PlayerCharacterFifthPlaceholderFragment.newInstance(position);
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 5;
    }
}
