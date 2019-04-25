package com.reading;

import android.app.Application;

import com.facebook.react.ReactApplication;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.shell.MainReactPackage;
import com.facebook.soloader.SoLoader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.tencent.bugly.crashreport.CrashReport;
import com.theweflex.react.WeChatPackage;
import org.devio.rn.splashscreen.SplashScreenReactPackage;
import com.oblador.vectoricons.VectorIconsPackage;
import com.learnium.RNDeviceInfo.RNDeviceInfo;
import com.richardcao.exceptionsmanager.react.ExceptionsManager;
import com.microsoft.codepush.react.CodePush;
public class MainApplication extends Application implements ReactApplication {

  private final ReactNativeHost mReactNativeHost = new ReactNativeHost(this) {

        @Override
        protected String getJSBundleFile() {
        return CodePush.getJSBundleFile();
        }
    
    @Override
    public boolean getUseDeveloperSupport() {
      return BuildConfig.DEBUG;
    }

    @Override
    protected List<ReactPackage> getPackages() {
      List<ReactPackage> packages = Arrays.asList(
              new MainReactPackage(),
              new SplashScreenReactPackage(),
              new WeChatPackage(),
              new CodePush(BuildConfig.CODEPUSH_KEY, MainApplication.this, BuildConfig.DEBUG),
              new RNDeviceInfo(),
              new VectorIconsPackage());
      ArrayList<ReactPackage> packageList = new ArrayList<>(packages);
      if (!BuildConfig.DEBUG) {
        packageList.add(new ExceptionsManager());
      }
      return packages;
    }

    @Override
    protected String getJSMainModuleName() {
      return "index";
    }
  };

  @Override
  public ReactNativeHost getReactNativeHost() {
    return mReactNativeHost;
  }

  @Override
  public void onCreate() {
    super.onCreate();
    SoLoader.init(this, /* native exopackage */ false);
    if (!BuildConfig.DEBUG) {
      CrashReport.initCrashReport(getApplicationContext(), "900019562", false);
    }
  }
}
