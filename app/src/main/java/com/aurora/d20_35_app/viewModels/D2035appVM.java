package com.aurora.d20_35_app.viewModels;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.aurora.d20_35_app.helper.ActivityViewModel;
import com.aurora.d20_35_app.utilsDatabase.DatabaseManager;
import com.aurora.d20_35_app.views.D2035appActivity;

import androidx.core.content.ContextCompat;
import androidx.databinding.ObservableField;


public class D2035appVM extends ActivityViewModel<D2035appActivity> {

    public final ObservableField<String> status = new ObservableField<>();

    public D2035appVM(D2035appActivity activity, String status) {
        super(activity);
        checkPermissions();
        onFirstTimeOpened();
        this.status.set(status);
    }

    private void onFirstTimeOpened() {
        SharedPreferences sharedpreferences = getActivity().getApplicationContext().getSharedPreferences("MyPref", 0);
        if (sharedpreferences.getBoolean("firstTimeOpened", true)) {
            System.out.println("First opening");
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putBoolean("firstTimeOpened", true);
            editor.apply();
            DatabaseManager.initialDatabasesResolver(getActivity());
        } else {
            System.out.println("Another opening");
        }
    }

    public void onResume() {
    }

    @Override
    public void onOptionsItemSelected(MenuItem item) {
        Log.i("Menu ", " Clicked menu item: " + item);
        if (this.onOptionsItemSelectedStart()) {
            this.getActivity().startNewActivityFromMain(item.getItemId());
            super.onOptionsItemSelected(item); //todo check this
        } else {
            Toast.makeText(getActivity(), "Write external storage permission needed.", Toast.LENGTH_LONG).show();
        }
    }

    private boolean onOptionsItemSelectedStart() {
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        return false;
    }

    public void buttonOnClick(View view) {
        Log.i("Button ", " Clicked " + view.toString());
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            this.getActivity().startNewActivityFromMain(view.getId());
        } else {
            Toast.makeText(getActivity(), "Write external storage permission needed.", Toast.LENGTH_LONG).show();
        }
    }

}
