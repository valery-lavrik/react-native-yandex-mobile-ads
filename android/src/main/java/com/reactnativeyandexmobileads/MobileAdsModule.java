package com.reactnativeyandexmobileads;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.yandex.mobile.ads.common.InitializationListener;
import com.facebook.react.bridge.Promise;
import com.yandex.mobile.ads.common.MobileAds;

public class MobileAdsModule extends ReactContextBaseJavaModule {

  public MobileAdsModule(ReactApplicationContext reactContext) {
    super(reactContext);
  }

  @Override
  public String getName() {
    return "MobileAds";
  }

  @ReactMethod
  public void initialize(ReadableMap configuration) {
    Handler mainHandler = new Handler(getReactApplicationContext().getMainLooper());
    Runnable myRunnable = () -> MobileAds.initialize(getReactApplicationContext(), () -> {
      MobileAds.initialize(getReactApplicationContext(), () -> {
        MobileAds.setUserConsent(configuration.getBoolean("userConsent"));
        MobileAds.setLocationConsent(configuration.getBoolean("locationConsent"));
        MobileAds.enableLogging(configuration.getBoolean("enableLogging"));
        MobileAds.enableDebugErrorIndicator(configuration.getBoolean("enableDebugErrorIndicator"));
      });
    });
    mainHandler.post(myRunnable);
  }
}
