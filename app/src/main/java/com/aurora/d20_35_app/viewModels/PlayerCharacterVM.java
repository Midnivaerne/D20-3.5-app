package com.aurora.d20_35_app.viewModels;

import com.aurora.d20_35_app.R;
import com.aurora.d20_35_app.fragments.PlayerCharacterAllSectionsPagerAdapter;
import com.aurora.d20_35_app.helper.ActivityViewModel;
import com.aurora.d20_35_app.models.Hero;
import com.aurora.d20_35_app.views.PlayerCharacterActivity;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import lombok.Getter;
import lombok.Setter;

import static com.aurora.d20_35_app.utilsDatabase.DatabaseHolder.getDatabaseHolder;

public class PlayerCharacterVM extends ActivityViewModel<PlayerCharacterActivity> {

    private TabLayout mTabLayout;
    private PlayerCharacterAllSectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    @Getter
    @Setter
    private Hero hero;

    public PlayerCharacterVM(PlayerCharacterActivity activity) {
        super(activity);
        showBackButton();
        setHero(getDatabaseHolder(activity).HEROES_LIST.get(Integer.parseInt(activity.getIntent().getStringExtra(PlayerCharacterActivity.HERO_ID))));
        setTabs();
    }

    private void setTabs() {
        mTabLayout = (TabLayout) getActivity().findViewById(R.id.player_character_tabs);
        mSectionsPagerAdapter = new PlayerCharacterAllSectionsPagerAdapter(getActivity().getSupportFragmentManager());
        mViewPager = (ViewPager) getActivity().findViewById(R.id.player_character_container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.addOnPageChangeListener(new customTabChangeListener(mTabLayout));
        mTabLayout.addOnTabSelectedListener(new customTabSelectionListener(mViewPager));
    }
}

class customTabChangeListener extends TabLayout.TabLayoutOnPageChangeListener {
    customTabChangeListener(TabLayout tabLayout) {
        super(tabLayout);
    }
}

class customTabSelectionListener implements TabLayout.OnTabSelectedListener {
    customTabSelectionListener(ViewPager viewPager) {
        this.tabViewPager = viewPager;
    }

    private ViewPager tabViewPager;
    private int position;

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        position = tab.getPosition();
        tabViewPager.setCurrentItem(position);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
