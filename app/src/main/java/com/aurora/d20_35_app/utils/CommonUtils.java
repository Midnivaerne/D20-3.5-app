package com.aurora.d20_35_app.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.Settings;
import android.util.Patterns;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public final class CommonUtils {

  public static final String TIMESTAMP_FORMAT = "yyyyMMdd_HHmmss";

  private CommonUtils() {
    // This utility class is not publicly instantiable
  }

  @SuppressLint("all")
  public static String getDeviceId(Context context) {
    return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
  }

  public static String getTimestamp() {
    return new SimpleDateFormat(TIMESTAMP_FORMAT, Locale.US).format(new Date());
  }

  public static boolean isEmailValid(String email) {
    return Patterns.EMAIL_ADDRESS.matcher(email).matches();
  }

  public static String loadJSONFromAsset(Context context, String jsonFileName) throws IOException {
    AssetManager manager = context.getAssets();
    InputStream is = manager.open(jsonFileName);

    int size = is.available();
    byte[] buffer = new byte[size];
    is.read(buffer);
    is.close();

    return new String(buffer, StandardCharsets.UTF_8);
  }

  public static int randomWithRange(int min, int max) {
    int range = (max - min) + 1;
    return (int) (Math.random() * range) + min;
  }

  public static Bitmap resizeInputStreamToBitmap(InputStream inputStream, int reqWidth, int reqHeight) {
    // BEST QUALITY MATCH
    // Decode with inJustDecodeBounds=true to check dimensions
    final BitmapFactory.Options options = new BitmapFactory.Options();
    options.inJustDecodeBounds = true;
    BitmapFactory.decodeStream(inputStream, null, options); //todo investigate if null

    // Calculate inSampleSize
    // Raw height and width of image
    final int height = options.outHeight;
    final int width = options.outWidth;
    final String imageType = options.outMimeType;
    options.inPreferredConfig = Bitmap.Config.RGB_565;
    int inSampleSize = 1;

    if (height > reqHeight) {
      inSampleSize = Math.round((float) height / (float) reqHeight);
    }

    int expectedWidth = width / inSampleSize;

    if (expectedWidth > reqWidth) {
      //if(Math.round((float)width / (float)reqWidth) > inSampleSize) // If bigger SampSize..
      inSampleSize = Math.round((float) width / (float) reqWidth);
    }

    options.inSampleSize = inSampleSize;

    // Decode bitmap with inSampleSize set
    options.inJustDecodeBounds = false;

    return BitmapFactory.decodeStream(inputStream, null, options);
  }

  public static void copyFile(Path source, Path destination) {
    try (InputStream in = Files.newInputStream(source);
        OutputStream out = Files.newOutputStream(destination)) {
      byte[] buffer = new byte[1024];
      int read;
      while ((read = in.read(buffer)) != -1) {
        out.write(buffer, 0, read);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void copyFileFromAssets(InputStream in, Path destination) {
    try (OutputStream out = Files.newOutputStream(destination)) {
      byte[] buffer = new byte[1024];
      int read;
      while ((read = in.read(buffer)) != -1) {
        out.write(buffer, 0, read);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}