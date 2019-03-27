package com.aurora.core.viewmodels;

import static com.aurora.core.utils.CommonUtils.randomWithRange;
import static com.aurora.core.utils.CommonUtils.resizeInputStreamToBitmap;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.view.Display;
import android.view.Gravity;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutionException;

import com.aurora.core.database.CustomCallback;
import com.aurora.core.database.DatabaseManager;
import com.aurora.core.helper.ActivityViewModel;
import com.aurora.core.views.D2035appActivity;

public class D2035appVM extends ActivityViewModel<D2035appActivity> {

  private static final int MIN_LEVEL_DRAWABLE = 1;
  private static final int MAX_LEVEL_DRAWABLE = 6;
  private static final String ASSETS_LAUNCH_IMAGES_DIR = "launchImages/";
  private static final String ASSETS_LAUNCH_IMAGES_PREFIX = "launch_image_";
  private static final String ASSETS_LAUNCH_IMAGES_EXTENSION = ".png";
  private int appDimensionWidth;
  private int appDimensionHeight;

  public D2035appVM(D2035appActivity activity) {
    super(activity);
    setDimensions();
  }

  private void setDimensions() {
    Display display = getActivity().getWindowManager().getDefaultDisplay();
    Point size = new Point();
    display.getSize(size);
    appDimensionWidth = size.x;
    appDimensionHeight = size.y;
  }

  public Drawable getBackground() {
    int launchImageNumber = randomWithRange(MIN_LEVEL_DRAWABLE, MAX_LEVEL_DRAWABLE);
    InputStream inputStream = null;
    try {
      inputStream = getActivity().getAssets()
          .open(ASSETS_LAUNCH_IMAGES_DIR + ASSETS_LAUNCH_IMAGES_PREFIX + launchImageNumber + ASSETS_LAUNCH_IMAGES_EXTENSION);
    } catch (IOException e) {
      e.printStackTrace();
    }
    Bitmap bitmapResized = resizeInputStreamToBitmap(inputStream, appDimensionWidth, appDimensionHeight);
    BitmapDrawable bitmapDrawable = new BitmapDrawable(getActivity().getResources(), bitmapResized);
    bitmapDrawable.setGravity(Gravity.FILL);
    bitmapDrawable.setAlpha(255);
    bitmapDrawable.setVisible(true, true);
    return bitmapDrawable;
  }

  public void initializeDB(CustomCallback callback) {
    InitializeDbAsync initializeDbAsync = new InitializeDbAsync(this, callback);
    try {
      initializeDbAsync.execute().get();
    } catch (ExecutionException | InterruptedException e) {
      e.printStackTrace();
      Thread.currentThread().interrupt();
    }
  }

  private static class InitializeDbAsync extends AsyncTask<Void, Void, Void> {

    private final D2035appVM d2035appVM;
    private CustomCallback customCallback;

    private InitializeDbAsync(D2035appVM d2035appVM, CustomCallback customCallback) {
      this.d2035appVM = d2035appVM;
      this.customCallback = customCallback;
    }

    @Override
    protected void onPreExecute() {
      DatabaseManager.startProgressBar(d2035appVM.getActivity());
    }

    @Override
    protected Void doInBackground(final Void... params) {
      DatabaseManager.initialDatabasesResolver(d2035appVM.getActivity());
      return null;
    }

    @Override
    protected void onPostExecute(Void par) {
      DatabaseManager.closeProgressBar(d2035appVM.getActivity());
      try {
        Thread.sleep(1);
      } catch (InterruptedException e) {
        e.printStackTrace();
        Thread.currentThread().interrupt();
      }
      customCallback.onDatabaseInitialised();
    }
  }
}
