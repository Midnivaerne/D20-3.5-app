package com.aurora.player.adapters;

import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;

public class CustomTabSelectionListener implements TabLayout.OnTabSelectedListener {

  public CustomTabSelectionListener(ViewPager viewPager) {
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
