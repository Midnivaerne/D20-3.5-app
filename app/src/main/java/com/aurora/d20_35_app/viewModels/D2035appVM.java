package com.aurora.d20_35_app.viewModels;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.aurora.d20_35_app.Startup;
import com.aurora.d20_35_app.helper.ActivityViewModel;
import com.aurora.d20_35_app.models.DatabaseManager;
import com.aurora.d20_35_app.utils.PermissionHandler;
import com.aurora.d20_35_app.views.D2035appActivity;
import com.aurora.d20_35_app.views.DMActivity;
import com.aurora.d20_35_app.views.PCActivity;

import java.util.Arrays;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.databinding.ObservableField;
import lombok.NonNull;


public class D2035appVM extends ActivityViewModel<D2035appActivity> {
    public D2035appVM(D2035appActivity activity, String status) {
        super(activity);

        this.status.set(status);
        PermissionHandler.reloadPermissions(activity);

        if (Startup.sharedpreferences.getBoolean("firstTimeOpened", true)) {
            Log.i("Permissions ", " Getting initial permissions");
            getPermissions();
            Startup.editor.putBoolean("firstTimeOpened", false).apply();
        } else {
            Log.i("Permissions ", " Already asked for initial permissions");
        }
    }

    public final ObservableField<String> status = new ObservableField<>();

    public void onResume() {

    }

    @RequiresApi(api = Build.VERSION_CODES.O_MR1)
    public void dmButtonOnClick(View view, Activity activity) {
        Log.i("Button ", " Clicked toDM");
        if (DatabaseManager.getWriteExternalStoragePermission() == PackageManager.PERMISSION_GRANTED) {
            DatabaseManager.initialDatabasesResolver(activity);
            Intent intent_DM = new Intent(activity, DMActivity.class);
            activity.startActivity(intent_DM);
            Log.i("Content ", " Main layout to DM ");
        } else {
            Toast.makeText(activity, "Write external storage permission needed.", Toast.LENGTH_LONG).show();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O_MR1)
    public void pcButtonOnClick(View view, Activity activity) {
        Log.i("Button ", " Clicked toPC");
        if (DatabaseManager.getWriteExternalStoragePermission() == PackageManager.PERMISSION_GRANTED) {
            DatabaseManager.initialDatabasesResolver(activity);
            Intent intent_PC = new Intent(activity, PCActivity.class);
            activity.startActivity(intent_PC);
            Log.i("Content ", " Main layout to PC ");
        } else {
            Toast.makeText(activity, "Write external storage permission needed.", Toast.LENGTH_LONG).show();
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



    public void getPermissions() {
        PermissionHandler.reloadPermissions(D2035appVM.super.getActivity());
        if (!this.hasPermissions(D2035appVM.super.getActivity(), DatabaseManager.getPermissionTypeStringManifest())) {
            ActivityCompat.requestPermissions(D2035appVM.super.getActivity(), DatabaseManager.getPermissionTypeStringManifest(), DatabaseManager.REQUEST_CODE_PERMISSION_ALL);
            Log.i("Permissions ", " Asked for " + Arrays.toString(DatabaseManager.getPermissionTypeString()) + " External Storage permissions");
        } else {
            Log.i("Permissions ", " " + Arrays.toString(DatabaseManager.getPermissionTypeString()) + " External Storage permissions already granted");
        }
    }


    // This method is invoked after user click buttons in permission grant popup dialog.
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (permissions.length > 0) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(D2035appVM.super.getActivity(), "You grant " + Arrays.toString(DatabaseManager.getPermissionTypeString()).toLowerCase() + " external storage permission", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(D2035appVM.super.getActivity(), "You denied " + Arrays.toString(DatabaseManager.getPermissionTypeString()).toLowerCase() + " external storage permission.", Toast.LENGTH_LONG).show();
            }
            Log.i("Permissions ", "Finished asking for " + Arrays.toString(DatabaseManager.getPermissionTypeString()) + " permissions");
            PermissionHandler.reloadPermissions(D2035appVM.super.getActivity());
        } else {
            Log.i("Permissions ", " No permission specified");
        }
    }

}
