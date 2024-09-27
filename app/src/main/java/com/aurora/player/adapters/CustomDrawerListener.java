package com.aurora.player.adapters;

import static com.aurora.core.database.TranslationsHolder.translate;

import androidx.drawerlayout.widget.DrawerLayout;

import android.view.View;
import android.widget.TextView;

import com.aurora.core.R;

public class CustomDrawerListener implements DrawerLayout.DrawerListener {

  @Override
  public void onDrawerSlide(@androidx.annotation.NonNull View drawerView, float slideOffset) {
  }

  @Override
  public void onDrawerOpened(@androidx.annotation.NonNull View drawerView) {
    ((TextView) drawerView.findViewById(R.id.nav_header_title)).setText(translate("race")); //todo decide what here and change
    ((TextView) drawerView.findViewById(R.id.nav_header_subtitle)).setText(translate("class")); //todo decide what here and change
  }

  @Override
  public void onDrawerClosed(@androidx.annotation.NonNull View drawerView) {
  }

  @Override
  public void onDrawerStateChanged(int newState) {
  }
}
