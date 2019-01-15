package com.aurora.d20_35_app.views;

import android.annotation.SuppressLint;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.aurora.d20_35_app.BR;
import com.aurora.d20_35_app.R;
import com.aurora.d20_35_app.databinding.ActivityPlayerCharacterBinding;
import com.aurora.d20_35_app.helper.BindingActivity;
import com.aurora.d20_35_app.viewModels.PlayerCharacterVM;
import com.google.android.material.navigation.NavigationView;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import lombok.NonNull;

import static com.aurora.d20_35_app.utilsDatabase.TranslationsHolder.translate;


public class PlayerCharacterActivity extends BindingActivity<ActivityPlayerCharacterBinding, PlayerCharacterVM>
        implements NavigationView.OnNavigationItemSelectedListener {

    public static final String HERO_ID = "hero_id";

    @Override
    public PlayerCharacterVM onCreate() {
        setSupportActionBar(getMViewDataBinding().toolbar);
        drawerSetup();
        return new PlayerCharacterVM(this);
    }

    private void drawerSetup() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.player_character_drawer_layout);  //TODO investigate if working here and if needs to be moved
        drawer.addDrawerListener(new customDrawerListener());

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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_player_characters_list, menu);
        menu.findItem(R.id.characters_list_actions_action).setTitle(translate("action_actions"));
        menu.findItem(R.id.characters_list_settings_action).setTitle(translate("action_settings"));
        return true;
    }

    @SuppressLint("NewApi")
    @Override
    protected void setTranslatedTexts() {
        getSupportActionBar().setTitle(translate(getMActivityViewModel().getHero().getName()));
    }

    @Override
    protected Class<?> chooseNewActivity(int destinationID) {
        switch (destinationID) {
            case R.id.characters_list_actions_action:
                drawerActions();
                return null;
            case R.id.characters_list_settings_action:
                return null; //todo change
        }
        return null;
    }

    private void drawerActions() {
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
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.player_character_drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

class customDrawerListener implements DrawerLayout.DrawerListener {

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

