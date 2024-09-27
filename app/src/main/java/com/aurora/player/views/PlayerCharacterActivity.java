package com.aurora.player.views;

import static com.aurora.core.database.TranslationsHolder.translate;

import lombok.NonNull;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.view.MenuItem;

import com.aurora.core.BR;
import com.aurora.core.R;
import com.aurora.core.databinding.ActivityPlayerCharacterBinding;
import com.aurora.core.helper.BindingActivity;
import com.aurora.player.adapters.CustomDrawerListener;
import com.aurora.player.viewmodels.PlayerCharacterVM;
import com.google.android.material.navigation.NavigationView;


public class PlayerCharacterActivity extends BindingActivity<ActivityPlayerCharacterBinding, PlayerCharacterVM>
    implements NavigationView.OnNavigationItemSelectedListener {

  public static final String HERO_PLAYER_ID = "hero_id";

  @Override
  public PlayerCharacterVM onCreate() {
    setSupportActionBar(getExViewDataBinding().toolbar);
    drawerSetup();
    return new PlayerCharacterVM(this);
  }

  private void drawerSetup() {
    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.player_character_drawer_layout);  //TODO investigate if needs to be moved
    drawer.addDrawerListener(new CustomDrawerListener());

    NavigationView navigationView = (NavigationView) findViewById(R.id.player_character_actions_drawer);
    navigationView.setNavigationItemSelectedListener(this);
  }

  @Override
  public int getBindingVariable() {
    return BR.viewModel;
  }

  @Override
  public int getLayoutId() {
    return R.layout.activity_player_character;
  }

  @SuppressLint("NewApi")
  @Override
  protected void setTranslatedTexts() {
    getSupportActionBar().setTitle(translate(getExActivityViewModel().getHero().getName()));
  }

  public void drawerActions() {
    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.player_character_drawer_layout);
    if (drawer.isDrawerOpen(GravityCompat.START)) {
      drawer.closeDrawer(GravityCompat.START);
    } else {
      drawer.openDrawer(GravityCompat.START);
    }
  }

  @Override
  public void onFragmentAttached() {
  }

  @Override
  public void onFragmentDetached(String tag) {
  }

  @SuppressWarnings("StatementWithEmptyBody")
  @Override
  public boolean onNavigationItemSelected(@NonNull MenuItem item) {
    // Handle navigation view item clicks here.
    int id = item.getItemId();

    if (id == R.id.nav_camera) {
      //todo Handle the "nav_camera" action
    } else if (id == R.id.nav_gallery) {
      //todo Handle the "nav_gallery" action
    } else if (id == R.id.nav_slideshow) {
      //todo Handle the "nav_slideshow" action
    } else if (id == R.id.nav_manage) {
      //todo Handle the "nav_manage" action
    } else if (id == R.id.nav_share) {
      //todo Handle the "nav_share" action
    } else if (id == R.id.nav_send) {
      //todo Handle the "nav_send" action
    }

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.player_character_drawer_layout);
    drawer.closeDrawer(GravityCompat.START);
    return true;
  }
}

