package com.aurora.player.viewmodels;

import static com.aurora.core.database.DatabaseHolder.getDatabaseHolder;

import lombok.Getter;
import lombok.Setter;

import androidx.viewpager.widget.ViewPager;

import android.app.Activity;

import java.util.Map;

import com.aurora.core.R;
import com.aurora.core.database.DatabaseHolder;
import com.aurora.core.helper.ActivityViewModel;
import com.aurora.core.models.userdata.HeroPlayer;
import com.aurora.player.adapters.CustomTabChangeListener;
import com.aurora.player.adapters.CustomTabSelectionListener;
import com.aurora.player.adapters.PlayerCharacterAllSectionsPagerAdapter;
import com.aurora.player.views.PlayerCharacterActivity;
import com.google.android.material.tabs.TabLayout;


public class PlayerCharacterVM extends ActivityViewModel<PlayerCharacterActivity> {

  private TabLayout pcTabLayout;
  private ViewPager pcViewPager;
  private PlayerCharacterAllSectionsPagerAdapter pcSectionsPagerAdapter;

  @Getter
  @Setter
  private HeroPlayer hero;

  @Getter
  private String[] heroAbilityScoresTextValues;
  @Getter
  private String[] heroSavingThrowsTextValues;
  @Getter
  private String[] heroDescriptionsTextValues;
  @Getter
  private Map<Integer, Map<Integer, Integer>> heroSkillsValues;

  public PlayerCharacterVM(PlayerCharacterActivity activity) {
    super(activity);
    showBackButton();
    loadHeroData(activity);
    setTabs();
  }

  private void loadHeroData(Activity activity) {
    DatabaseHolder databaseHolder = getDatabaseHolder(activity);
    this.setHero(databaseHolder.heroesPlayerMap
        .get(Integer.parseInt(activity.getIntent().getStringExtra(PlayerCharacterActivity.HERO_PLAYER_ID))));
    System.out.println("H bn-------------" + getHero().getBackupNames());//todo delete
    System.out.println("H V bn-------------" + getHero().getHeroValues().getBackupNames());//todo delete
    getHero().getHeroValues().generateRaceFromId(databaseHolder).generateClassListFromId(databaseHolder);

    heroAbilityScoresTextValues = new String[]{
        getHero().getHeroValues().getHeroAbilityScoreStr().toString(),
        getHero().getHeroValues().getHeroAbilityScoreDex().toString(),
        getHero().getHeroValues().getHeroAbilityScoreCon().toString(),
        getHero().getHeroValues().getHeroAbilityScoreInt().toString(),
        getHero().getHeroValues().getHeroAbilityScoreWis().toString(),
        getHero().getHeroValues().getHeroAbilityScoreCha().toString()
    };
    heroSavingThrowsTextValues = new String[]{
        getHero().getHeroValues().getFortitude(),
        getHero().getHeroValues().getReflex(),
        getHero().getHeroValues().getWill()
    };
    //heroSkillsValues = getHero().getHeroValues().getHeroSkillsValues();
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
    pcSectionsPagerAdapter = new PlayerCharacterAllSectionsPagerAdapter(getActivity().getSupportFragmentManager(), this);
    pcTabLayout = (TabLayout) getActivity().findViewById(R.id.player_character_tabs);
    pcViewPager = (ViewPager) getActivity().findViewById(R.id.player_character_container);
    pcViewPager.setAdapter(pcSectionsPagerAdapter);
    pcViewPager.addOnPageChangeListener(new CustomTabChangeListener(pcTabLayout));
    pcTabLayout.addOnTabSelectedListener(new CustomTabSelectionListener(pcViewPager));
  }

  public void menuOnClick() {
    getActivity().drawerActions();
  }

}

