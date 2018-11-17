package com.aurora.d20_35_app.viewModels;

import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.aurora.d20_35_app.helper.ActivityViewModel;
import com.aurora.d20_35_app.utilsDatabase.DatabaseManager;
import com.aurora.d20_35_app.startup.Startup;
import com.aurora.d20_35_app.utils.PermissionHandler;
import com.aurora.d20_35_app.views.D2035appActivity;

import java.util.Arrays;

import androidx.core.app.ActivityCompat;
import androidx.databinding.ObservableField;
import lombok.NonNull;


public class D2035appVM extends ActivityViewModel<D2035appActivity> {
    public final ObservableField<String> status = new ObservableField<>();

    public D2035appVM(D2035appActivity activity, String status) {
        super(activity);
        this.status.set(status);
        checkPermissionsOnFirstTimeOpen();
    }

    private void checkPermissionsOnFirstTimeOpen() {
        if (Startup.sharedpreferences.getBoolean("firstTimeOpened", true)) {
            Log.i("Permissions ", " Getting initial permissions");
            getPermissions();
            Startup.editor.putBoolean("firstTimeOpened", false).apply();
        } else {
            Log.i("Permissions ", " Already asked for initial permissions");
        }
    }

    public void getPermissions() {
        PermissionHandler.reloadPermissions(this.getActivity());
        if (!this.hasPermissions(this.getActivity(), DatabaseManager.getPermissionTypeStringManifest())) {
            ActivityCompat.requestPermissions(this.getActivity(), DatabaseManager.getPermissionTypeStringManifest(), DatabaseManager.REQUEST_CODE_PERMISSION_ALL);
            Log.i("Permissions ", " Asked for " + Arrays.toString(DatabaseManager.getPermissionTypeString()) + " External Storage permissions");
        } else {
            Log.i("Permissions ", " " + Arrays.toString(DatabaseManager.getPermissionTypeString()) + " External Storage permissions already granted");
        }
    }

    // This method is invoked after user click buttons in permission grant popup dialog.
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (permissions.length > 0) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this.getActivity(), "You grant " + Arrays.toString(DatabaseManager.getPermissionTypeString()).toLowerCase() + " external storage permission", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this.getActivity(), "You denied " + Arrays.toString(DatabaseManager.getPermissionTypeString()).toLowerCase() + " external storage permission.", Toast.LENGTH_LONG).show();
            }
            Log.i("Permissions ", "Finished asking for " + Arrays.toString(DatabaseManager.getPermissionTypeString()) + " permissions");
            PermissionHandler.reloadPermissions(this.getActivity());
        } else {
            Log.i("Permissions ", " No permission specified");
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

    public boolean onOptionsItemSelectedStart() {
        if (DatabaseManager.getWriteExternalStoragePermission() == PackageManager.PERMISSION_GRANTED) {
            DatabaseManager.initialDatabasesResolver(getActivity());
            return true;
        }
        return false;
    }

    public void buttonOnClick(View view) {
        Log.i("Button ", " Clicked " + view.toString());
        if (DatabaseManager.getWriteExternalStoragePermission() == PackageManager.PERMISSION_GRANTED) {
            DatabaseManager.initialDatabasesResolver(getActivity());
            this.getActivity().startNewActivityFromMain(view.getId());
        } else {
            Toast.makeText(getActivity(), "Write external storage permission needed.", Toast.LENGTH_LONG).show();
        }
    }

    public boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

}
