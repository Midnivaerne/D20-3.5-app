package com.aurora.player.viewModels;

import android.app.Activity;

import com.aurora.d20_35_app.R;
import com.aurora.d20_35_app.helper.ActivityViewModel;
import com.aurora.d20_35_app.models.userData.HeroPlayer;
import com.aurora.d20_35_app.database.DatabaseHolder;
import com.aurora.player.adapters.CustomTabChangeListener;
import com.aurora.player.adapters.CustomTabSelectionListener;
import com.aurora.player.adapters.PlayerCharacterAllSectionsPagerAdapter;
import com.aurora.player.views.PlayerCharacterActivity;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import lombok.Getter;
import lombok.Setter;

import static com.aurora.d20_35_app.database.DatabaseHolder.getDatabaseHolder;

public class PlayerCharacterVM extends ActivityViewModel<PlayerCharacterActivity> {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private PlayerCharacterAllSectionsPagerAdapter mSectionsPagerAdapter;

    @Getter
    @Setter
    private HeroPlayer hero;

    @Getter
    private String heroDescriptionsTextValues[];

    public PlayerCharacterVM(PlayerCharacterActivity activity) {
        super(activity);
        showBackButton();
        loadHeroData(activity);
        setTabs();
    }

    private void loadHeroData(Activity activity) {
        DatabaseHolder databaseHolder = getDatabaseHolder(activity);
        this.setHero(databaseHolder.HEROES_PLAYER_MAP.get(Integer.parseInt(activity.getIntent().getStringExtra(PlayerCharacterActivity.HERO_PLAYER_ID))));
        getHero().getHeroDescription().setBackupNames(getHero().getBackupNames());
        heroDescriptionsTextValues = new String[]{
                getHero().getName(),
                getHero().getHeroDescription().getHeroPlayer(),
                getHero().getHeroDescription().getHeroClassAndLevelStringFromId(databaseHolder),
                getHero().getHeroDescription().getRaceStringFromId(databaseHolder),
                getHero().getHeroDescription().getAlignmentStringFromId(databaseHolder),
                getHero().getHeroDescription().getDeityStringFromId(databaseHolder),
                getHero().getHeroDescription().getSizeStringFromId(databaseHolder),
                String.valueOf(getHero().getHeroDescription().getHeroAge()),
                getHero().getHeroDescription().getHeroGender(),
                getHero().getHeroDescription().getHeroHeight(),
                getHero().getHeroDescription().getHeroWeight(),
                getHero().getHeroDescription().getHeroEyes(),
                getHero().getHeroDescription().getHeroHair(),
                getHero().getHeroDescription().getHeroSkin()};
    }

    private void setTabs() {
        mSectionsPagerAdapter = new PlayerCharacterAllSectionsPagerAdapter(getActivity().getSupportFragmentManager(), this);
        mTabLayout = (TabLayout) getActivity().findViewById(R.id.player_character_tabs);
        mViewPager = (ViewPager) getActivity().findViewById(R.id.player_character_container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.addOnPageChangeListener(new CustomTabChangeListener(mTabLayout));
        mTabLayout.addOnTabSelectedListener(new CustomTabSelectionListener(mViewPager));
    }

    public void menuOnClick() {
        getActivity().drawerActions();
    }
}

