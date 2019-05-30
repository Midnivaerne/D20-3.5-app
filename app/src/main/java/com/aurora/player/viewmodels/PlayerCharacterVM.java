package com.aurora.player.viewmodels;

import static com.aurora.core.database.DatabaseHolder.getDatabaseHolder;

import android.app.Activity;
import androidx.viewpager.widget.ViewPager;
import lombok.Getter;
import lombok.Setter;

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
    getHero().generateAll(databaseHolder);
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

