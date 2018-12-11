package com.aurora.d20_35_app.viewModels;

import android.Manifest;
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

import static com.aurora.d20_35_app.utilsDatabase.DatabaseHolder.getDatabaseHolder;


public class D2035appVM extends ActivityViewModel<D2035appActivity> {

    public final ObservableField<String> status = new ObservableField<>();

    public D2035appVM(D2035appActivity activity, String status) {
        super(activity);
        this.status.set(status);
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
            DatabaseManager.ConnectDbAsync task = new DatabaseManager.ConnectDbAsync(getDatabaseHolder(activity)); //todo delete after tests
            task.execute();
            this.getActivity().startNewActivityFromMain(view.getId());
        } else {
            Toast.makeText(getActivity(), "Write external storage permission needed.", Toast.LENGTH_LONG).show();
        }
    }

}
