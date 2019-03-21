package com.aurora.player.viewmodels;

import static com.aurora.core.database.DatabaseHolder.getDatabaseHolder;

import lombok.Getter;
import lombok.Setter;

import android.app.Activity;
import androidx.viewpager.widget.ViewPager;
import com.aurora.core.R;
import com.aurora.core.database.DatabaseHolder;
import com.aurora.core.helper.ActivityViewModel;
import com.aurora.core.models.userData.HeroPlayer;
import com.aurora.player.adapters.CustomTabChangeListener;
import com.aurora.player.adapters.CustomTabSelectionListener;
import com.aurora.player.adapters.PlayerCharacterAllSectionsPagerAdapter;
import com.aurora.player.views.PlayerCharacterActivity;
import com.google.android.material.tabs.TabLayout;


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
    this.setHero(databaseHolder.HEROES_PLAYER_MAP
        .get(Integer.parseInt(activity.getIntent().getStringExtra(PlayerCharacterActivity.HERO_PLAYER_ID))));
    getHero().getHeroDescription().setBackupNames(getHero().getBackupNames());
    getHero().getHeroValues().setBackupNames(getHero().getBackupNames());
    getHero().getHeroValues().generateRaceFromId(databaseHolder).generateClassListFromId(databaseHolder);
    heroCombatTextValues = new String[]{
        getHero().getHeroValues().getHeroHitPointsStringFromList(),
        getHero().getDamageReduction(),
        getHero().getArmourClass(),
        getHero().getArmourClassTouch(),
        getHero().getArmourClassFlatfooted(),
        getHero().getSpeed(),
        getHero().getInitiative(),
        getHero().getAttack(),
        getHero().getAttackMelee(),
        getHero().getAttackRanged(),
        getHero().getGrapple(),
        getHero().getSpellResistance()
    };
    heroAbilityScoresTextValues = new String[]{
        getHero().getHeroValues().getHeroAbilityScoreStr().toString(),
        getHero().getHeroValues().getHeroAbilityScoreDex().toString(),
        getHero().getHeroValues().getHeroAbilityScoreCon().toString(),
        getHero().getHeroValues().getHeroAbilityScoreInt().toString(),
        getHero().getHeroValues().getHeroAbilityScoreWis().toString(),
        getHero().getHeroValues().getHeroAbilityScoreCha().toString()
    };
    heroSavingThrowsTextValues = new String[]{
        getHero().getFortitude(),
        getHero().getReflex(),
        getHero().getWill()
    };
    heroDescriptionsTextValues = new String[]{
        getHero().getName(),
        getHero().getHeroDescription().getHeroPlayer(),
        getHero().getHeroValues().getClassListFromMap(),
        getHero().getHeroValues().getRaceAndTemplateNamesFromObjects(),
        getHero().getHeroValues().getAlignmentStringFromId(databaseHolder),
        getHero().getHeroValues().getDeityStringFromId(databaseHolder),
        getHero().getHeroValues().getSizeStringFromId(databaseHolder),
        String.valueOf(getHero().getHeroDescription().getHeroAge()),
        getHero().getHeroValues().getHeroGender(),
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

