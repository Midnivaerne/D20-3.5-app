package com.aurora.d20_35_app.viewModels;

import android.app.Activity;

import com.aurora.d20_35_app.R;
import com.aurora.d20_35_app.fragments.PlayerCharacterAllSectionsPagerAdapter;
import com.aurora.d20_35_app.helper.ActivityViewModel;
import com.aurora.d20_35_app.models.userData.HeroPlayer;
import com.aurora.d20_35_app.utils.database.DatabaseHolder;
import com.aurora.d20_35_app.views.PlayerCharacterActivity;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import lombok.Getter;
import lombok.Setter;

import static com.aurora.d20_35_app.utils.database.DatabaseHolder.getDatabaseHolder;

public class PlayerCharacterVM extends ActivityViewModel<PlayerCharacterActivity> {

    private TabLayout mTabLayout;
    private PlayerCharacterAllSectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    @Getter
    @Setter
    private HeroPlayer hero;

    @Getter
    private String heroTextValues[];

    public PlayerCharacterVM(PlayerCharacterActivity activity) {
        super(activity);
        showBackButton();
        loadHeroData(activity);
        setTabs();
    }

    private void loadHeroData(Activity activity) {
        DatabaseHolder databaseHolder = getDatabaseHolder(activity);
        this.setHero(databaseHolder.HEROES_PLAYER_MAP.get(Integer.parseInt(activity.getIntent().getStringExtra(PlayerCharacterActivity.HERO_PLAYER_ID))));
        String classesAndLevelText = getHero().getHeroDescription().getHeroClassAndLevelStringFromId(databaseHolder);
        String raceText = getHero().getHeroDescription().getRaceStringFromId(databaseHolder);
        String alignmentText = getHero().getHeroDescription().getAlignmentStringFromId(databaseHolder);
        String deityText = getHero().getHeroDescription().getDeityStringFromId(databaseHolder);
        String sizeText = getHero().getHeroDescription().getSizeStringFromId(databaseHolder);
        heroTextValues = new String[]{
                getHero().getName(),
                getHero().getHeroDescription().getHeroPlayer(),
                classesAndLevelText,
                raceText,
                alignmentText,
                deityText,
                sizeText,
                String.valueOf(getHero().getHeroDescription().getHeroAge()),
                getHero().getHeroDescription().getHeroGender(),
                getHero().getHeroDescription().getHeroHeight(),
                getHero().getHeroDescription().getHeroWeight(),
                getHero().getHeroDescription().getHeroEyes(),
                getHero().getHeroDescription().getHeroHair(),
                getHero().getHeroDescription().getHeroSkin()};
    }

    private void setTabs() {
        mTabLayout = (TabLayout) getActivity().findViewById(R.id.player_character_tabs);
        mSectionsPagerAdapter = new PlayerCharacterAllSectionsPagerAdapter(getActivity().getSupportFragmentManager(), this);
        mViewPager = (ViewPager) getActivity().findViewById(R.id.player_character_container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.addOnPageChangeListener(new customTabChangeListener(mTabLayout));
        mTabLayout.addOnTabSelectedListener(new customTabSelectionListener(mViewPager));
    }

    public void menuOnClick() {
        getActivity().drawerActions();
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
