package com.lesimoes.androidnotificationlistener;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.facebook.react.HeadlessJsTaskService;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.jstasks.HeadlessJsTaskConfig;
import android.content.Context;
import android.util.Log;

public class RNAndroidNotificationListenerService extends HeadlessJsTaskService {
  public static void startService(Context context) {
    Log.d("RN NOTIFICATION start service ", " aaaaaaa");

    if (context != null) {
      Intent intentService = new Intent(context, RNAndroidNotificationListenerService.class);
      context.startService(intentService);

      HeadlessJsTaskService.acquireWakeLockNow(context);
    }
  }

  @Override
  protected @Nullable HeadlessJsTaskConfig getTaskConfig(Intent intent) {
      Bundle extras = intent.getExtras();

      WritableMap data = extras != null ? Arguments.fromBundle(extras) : null;

      return new HeadlessJsTaskConfig(
        "RNAndroidNotificationListener",
        Arguments.fromBundle(extras),
        5000,
        true);
  }
}
