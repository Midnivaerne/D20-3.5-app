package com.aurora.player.viewModels;

import static com.aurora.d20_35_app.database.DatabaseHolder.getDatabaseHolder;

import android.app.Activity;
import androidx.viewpager.widget.ViewPager;
import com.aurora.d20_35_app.R;
import com.aurora.d20_35_app.database.DatabaseHolder;
import com.aurora.d20_35_app.helper.ActivityViewModel;
import com.aurora.d20_35_app.models.userData.HeroPlayer;
import com.aurora.player.adapters.CustomTabChangeListener;
import com.aurora.player.adapters.CustomTabSelectionListener;
import com.aurora.player.adapters.PlayerCharacterAllSectionsPagerAdapter;
import com.aurora.player.views.PlayerCharacterActivity;
import com.google.android.material.tabs.TabLayout;
import lombok.Getter;
import lombok.Setter;

public class PlayerCharacterVM extends ActivityViewModel<PlayerCharacterActivity> {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private PlayerCharacterAllSectionsPagerAdapter mSectionsPagerAdapter;

    @Getter
    @Setter
    private HeroPlayer hero;

    @Getter
    private String heroCombatTextValues[];
    @Getter
    private String heroAbilityScoresTextValues[];
    @Getter
    private String heroSavingThrowsTextValues[];
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
        heroCombatTextValues = new String[]{
                getHero().getHeroStatistics().getHeroHitPointsStringFromList(),
                getHero().getDamageReduction(getHero()),
                getHero().getArmourClass(getHero()),
                getHero().getArmourClassTouch(getHero()),
                getHero().getArmourClassFlatfooted(getHero()),
                getHero().getSpeed(getHero()),
                getHero().getInitiative(getHero()),
                getHero().getAttack(getHero()),
                getHero().getAttackMelee(getHero()),
                getHero().getAttackRanged(getHero()),
                getHero().getGrapple(getHero()),
                getHero().getSpellResistance(getHero())
        };
        heroAbilityScoresTextValues = new String[]{
                getHero().getHeroStatistics().getHeroAbilityScoreStr().toString(),
                getHero().getHeroStatistics().getHeroAbilityScoreDex().toString(),
                getHero().getHeroStatistics().getHeroAbilityScoreCon().toString(),
                getHero().getHeroStatistics().getHeroAbilityScoreInt().toString(),
                getHero().getHeroStatistics().getHeroAbilityScoreWis().toString(),
                getHero().getHeroStatistics().getHeroAbilityScoreCha().toString()
        };
        heroSavingThrowsTextValues = new String[]{
                getHero().getFortitude(getHero()),
                getHero().getReflex(getHero()),
                getHero().getWill(getHero())
        };
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

