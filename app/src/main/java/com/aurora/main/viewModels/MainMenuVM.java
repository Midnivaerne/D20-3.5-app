package com.aurora.main.viewModels;

import android.Manifest;
import android.content.pm.PackageManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import androidx.core.content.ContextCompat;
import androidx.databinding.ObservableField;
import com.aurora.core.helper.ActivityViewModel;
import com.aurora.main.views.MainMenuActivity;


public class MainMenuVM extends ActivityViewModel<MainMenuActivity> {

  public final ObservableField<String> status = new ObservableField<>();

  public MainMenuVM(MainMenuActivity activity, String status) {
    super(activity);
    this.status.set(status);
  }

  public void onResume() {
  }

  public void buttonOnClick(View view) {
    Log.i("Button ", " Clicked " + view.toString());
    if (ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
      this.getActivity().startNewActivityWithId(view.getId());
    } else {
      Toast.makeText(getActivity(), "Write external storage permission needed.", Toast.LENGTH_LONG).show();
    }
  }

}
