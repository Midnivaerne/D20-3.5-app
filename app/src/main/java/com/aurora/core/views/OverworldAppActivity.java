package com.aurora.core.views;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.aurora.core.BR;
import com.aurora.core.R;
import com.aurora.core.database.CustomCallback;
import com.aurora.core.databinding.ActivityOverworldappBinding;
import com.aurora.core.helper.BindingActivity;
import com.aurora.core.viewmodels.OverworldAppVM;
import com.aurora.main.views.MainMenuActivity;


public class OverworldAppActivity extends BindingActivity<ActivityOverworldappBinding, OverworldAppVM> implements CustomCallback {

  private static final int REQUEST_CODE_PERMISSION_ALL = 1;
  @Getter
  private static String[] permissionTypeStringManifest = {Manifest.permission.WRITE_EXTERNAL_STORAGE};

  @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
  @Override
  public OverworldAppVM onCreate() {
    return new OverworldAppVM(this);
  }

  @Override
  public void onStart() {
    super.onStart();
    checkPermissions();
  }


  @Override
  protected void setApplicationTheme() {
  }

  @Override
  protected void setTranslatedTexts() {
  }

  @Override
  public int getBindingVariable() {
    return BR.viewModel;
  }

  @Override
  public int getLayoutId() {
    return R.layout.activity_overworldapp;
  }

  @Override
  public void onFragmentAttached() {
  }

  @Override
  public void onFragmentDetached(String tag) {
  }

  public void checkPermissions() {
    final List<String> missingPermissions = new ArrayList<>();
    getMissingPermissions(missingPermissions);

    if (!missingPermissions.isEmpty()) {
      // request all missing permissions
      final String[] permissions = missingPermissions.toArray(new String[missingPermissions.size()]);
      Log.i("Permissions ", " Asked for " + Arrays.toString(permissions) + " permissions");
      requestPermissions(permissions, REQUEST_CODE_PERMISSION_ALL);
    } else {
      final int[] grantResults = new int[permissionTypeStringManifest.length];
      Arrays.fill(grantResults, PackageManager.PERMISSION_GRANTED);
      onRequestPermissionsResult(REQUEST_CODE_PERMISSION_ALL, permissionTypeStringManifest, grantResults);
    }
  }

  private void getMissingPermissions(List<String> missingPermissions) {
    // check all required dynamic permissions
    Log.i("Permissions ", " Getting permissions");
    for (final String permission : permissionTypeStringManifest) {
      final int result = ContextCompat.checkSelfPermission(this.getApplicationContext(), permission);
      if (result != PackageManager.PERMISSION_GRANTED) {
        missingPermissions.add(permission);
      }
    }
  }

  @Override
  public void onRequestPermissionsResult(int requestCode, @androidx.annotation.NonNull String[] permissions,
      @androidx.annotation.NonNull int[] grantResults) {
    switch (requestCode) {
      case REQUEST_CODE_PERMISSION_ALL:
        for (int index = permissions.length - 1; index >= 0; --index) {
          if (grantResults[index] != PackageManager.PERMISSION_GRANTED) {
            Log.i("Permissions ", " " + permissions[index] + " not granted, exiting");
            Toast.makeText(this, "Required permission '" + permissions[index] + "' not granted, exiting", Toast.LENGTH_LONG).show();
            finish();
            return;
          }
        }
        break;
      default:
        break;
    }
    final List<String> stillMissingPermissions = new ArrayList<>();
    getMissingPermissions(stillMissingPermissions);
    if (stillMissingPermissions.isEmpty()) {
      Log.i("Permissions ", " All permissions granted");
      this.getExActivityViewModel().initializeDB(this);
    } else {
      Log.i("Permissions ", " Permissions not granted yet, still deciding");
    }
  }

  @Override
  public void onDatabaseInitialised() {
    startActivity(new Intent(this, MainMenuActivity.class));
    finish();
  }
}
