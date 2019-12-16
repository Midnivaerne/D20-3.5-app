package com.aurora.main.views;

import static com.aurora.core.database.TranslationsHolder.translate;

import android.view.Menu;
import android.widget.Button;

import com.aurora.core.BR;
import com.aurora.core.R;
import com.aurora.core.databinding.ActivityMainMenuBinding;
import com.aurora.core.helper.BindingActivity;
import com.aurora.main.viewmodels.MainMenuVM;
import com.aurora.master.views.DmActivity;
import com.aurora.player.views.PlayerCharactersListActivity;
import com.aurora.worldbuilder.views.WbActivity;

public class MainMenuActivity extends BindingActivity<ActivityMainMenuBinding, MainMenuVM> {

  private static final String KEY_STATUS = "STATUS";

  @Override
  public MainMenuVM onCreate() {
    setSupportActionBar(getExViewDataBinding().toolbar);
    return new MainMenuVM(this, getIntent().getStringExtra(KEY_STATUS));
  }

  @Override
  protected void setTranslatedTexts() {
    getSupportActionBar().setTitle(translate("app_name"));
    ((Button) findViewById(R.id.WB_button)).setText(translate("WB_button"));
    ((Button) findViewById(R.id.DM_button)).setText(translate("DM_button"));
    ((Button) findViewById(R.id.PC_button)).setText(translate("PC_button"));
  }

  @Override
  public int getBindingVariable() {
    return BR.viewModel;
  }

  @Override
  public int getLayoutId() {
    return R.layout.activity_main_menu;
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    menu.findItem(R.id.action_rules).setTitle(translate("action_rules"));
    menu.findItem(R.id.action_content).setTitle(translate("action_content"));
    menu.findItem(R.id.action_database).setTitle(translate("action_database"));
    menu.findItem(R.id.action_settings).setTitle(translate("action_settings"));
    menu.findItem(R.id.action_help).setTitle(translate("action_help"));
    menu.findItem(R.id.action_exit).setTitle(translate("action_exit"));
    return true;
  }

  @Override
  protected Class<?> chooseNewActivity(int destinationID) {
    switch (destinationID) {
      case R.id.PC_button:
        return PlayerCharactersListActivity.class;
      case R.id.DM_button:
        return DmActivity.class;
      case R.id.WB_button:
        return WbActivity.class;
      case R.id.action_rules:
        return RulesActivity.class;
      case R.id.action_content:
        return null; //todo ContentActivity.class
      case R.id.action_database:
        return DatabasesActivity.class;
      case R.id.action_settings:
        return SettingsActivity.class;
      case R.id.action_help:
        return HelpActivity.class;
      default:
        return null;
    }
  }

  @Override
  public void onFragmentAttached() {
  }

  @Override
  public void onFragmentDetached(String tag) {
  }
}
