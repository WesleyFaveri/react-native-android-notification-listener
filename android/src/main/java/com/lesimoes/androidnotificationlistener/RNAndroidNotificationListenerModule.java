package com.lesimoes.androidnotificationlistener;

import android.support.v4.app.NotificationManagerCompat;
import android.provider.Settings;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.Arguments;

import java.util.List;
import java.util.Set;

import com.lesimoes.androidnotificationlistener.RNAndroidNotificationListenerService;

public class RNAndroidNotificationListenerModule extends ReactContextBaseJavaModule {
    private static ReactApplicationContext reactContext;
    private Context mContext;

    public RNAndroidNotificationListenerModule(ReactApplicationContext context) {
        super(context);
        reactContext = context;
        mContext = reactContext;
    }

    public String getName() {
        return "RNAndroidNotificationListener";
    }

    @ReactMethod
    public void getPermissionStatus(Promise promise) {
        String packageName = reactContext.getPackageName();
        Set<String> enabledPackages = NotificationManagerCompat.getEnabledListenerPackages(reactContext);
        if (enabledPackages.contains(packageName)) {
            promise.resolve("authorized");
        } else {
            promise.resolve("denied");
        }
    }

    @ReactMethod
    public void requestPermission() {
        final Intent i = new Intent();
        i.setAction(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        i.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
        reactContext.startActivity(i);
    }

    public void onActivityResult(Activity activity, int requestCode, int resultCode, Intent data) {}

    public void onNewIntent(Intent intent){}

    public static void sendEvent(String event, String params) {
      Intent intent = new Intent(mContext, RNAndroidNotificationListenerService.class);
      Log.d("RN NOTIFICATION", params);
      intent.putExtra("params", params);

      mContext.startService(intent);
    }
}
