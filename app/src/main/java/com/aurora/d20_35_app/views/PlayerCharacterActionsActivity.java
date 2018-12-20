package com.aurora.d20_35_app.views;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.aurora.d20_35_app.BR;
import com.aurora.d20_35_app.R;
import com.aurora.d20_35_app.databinding.ActivityPlayerCharacterActionsBinding;
import com.aurora.d20_35_app.helper.BindingActivity;
import com.aurora.d20_35_app.viewModels.PlayerCharacterActionsVM;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import static com.aurora.d20_35_app.utilsDatabase.TranslationsHolder.translate;

;

public class PlayerCharacterActionsActivity extends BindingActivity<ActivityPlayerCharacterActionsBinding, PlayerCharacterActionsVM>
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    public PlayerCharacterActionsVM onCreate() {
        setSupportActionBar(getMViewDataBinding().toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);  //TODO investigate if working here and if needs to be moved
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, getMViewDataBinding().toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        return new PlayerCharacterActionsVM(this);
    }

    @Override
    protected void setTranslatedTexts() {
        (findViewById(R.id.nav_header_desc)).setContentDescription(translate("nav_header_desc"));
        ((TextView) findViewById(R.id.nav_header_title)).setText(translate("nav_header_title"));
        ((TextView) findViewById(R.id.nav_header_subtitle)).setText(translate("nav_header_subtitle"));
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_player_character_actions;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.player_character_actions_, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentAttached() {

    }

    @Override
    public void onFragmentDetached(String tag) {

    }
}
