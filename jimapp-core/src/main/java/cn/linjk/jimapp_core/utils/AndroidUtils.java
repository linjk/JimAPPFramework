package cn.linjk.jimapp_core.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import java.io.ByteArrayInputStream;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.security.auth.x500.X500Principal;

import cn.linjk.jimapp_core.app.JimAppBase;

/**
 * Created by LinJK on 6/23/16.
 */
public class AndroidUtils {

    public static Boolean isEmulator() {
        return "google_sdk".equals(Build.PRODUCT);
    }

    //region 获取应用信息
    public static String getAndroidId() {
        return Settings.Secure.getString(JimAppBase.get().getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    public static String getVersionName() {
        return getPackageInfo().versionName;
    }

    public static Integer getVersionCode() {
        return getPackageInfo().versionCode;
    }

    public static String getPackageName() {
        return getPackageInfo().packageName;
    }

    public static String getApplicationName() {
        Context context = JimAppBase.get();

        ApplicationInfo applicationInfo = AndroidUtils.getApplicationInfo();

        return context.getPackageManager().getApplicationLabel(applicationInfo).toString();
    }
    //endregion


    public static void hideSoftInput(View view) {
        ((InputMethodManager)JimAppBase.get().getSystemService(Context.INPUT_METHOD_SERVICE))
                .hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static WindowManager getWindowManager() {
        return (WindowManager)JimAppBase.get().getSystemService(Context.WINDOW_SERVICE);
    }

    public static Integer getHeapSize() {
        return ((ActivityManager)JimAppBase.get().getSystemService(Context.ACTIVITY_SERVICE))
                .getMemoryClass();
    }

    public static Boolean isMediaMounted() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    public static String getDeviceModel() {
        return Build.MODEL;
    }

    public static Integer getApiLevel() {
        return Build.VERSION.SDK_INT;
    }

    /**
     * HoneyComb is the nameof Android3.0, after that, it supported fragment.
     * @return honeyComb as true, otherwise as fase.
     */
    public static Boolean isPreHoneyComb() {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB;
    }

    public static String getPlatformVersion() {
        return Build.VERSION.RELEASE;
    }

    public static Boolean isGoogleTV() {
        return JimAppBase.get().getPackageManager().hasSystemFeature("com.google.android.tv");
    }

    public static Boolean hasGallery() {
        return !isGoogleTV();
    }

    //region 设备屏幕相关
    public static Boolean isSmallScreen() {
        int screenSize = JimAppBase.get().getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK;

        return screenSize == Configuration.SCREENLAYOUT_SIZE_SMALL;
    }

    public static Boolean isNormailScreen() {
        int screenSize = JimAppBase.get().getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK;

        return screenSize == Configuration.SCREENLAYOUT_SIZE_NORMAL;
    }

    public static Boolean isLargeScreen() {
        int screenSize = JimAppBase.get().getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK;

        return screenSize == Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    public static Boolean isXLargeScreen() {
        int screenSize = JimAppBase.get().getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK;

        return screenSize == Configuration.SCREENLAYOUT_SIZE_XLARGE;
    }

    public static Boolean isLargeScreenOrBigger() {
        int screenSize = JimAppBase.get().getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK;

        return (screenSize != Configuration.SCREENLAYOUT_SIZE_SMALL)
                && (screenSize != Configuration.SCREENLAYOUT_SIZE_NORMAL);
    }

    public static Boolean isXLargeScreenOrBigger() {
        int screenSize = JimAppBase.get().getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK;

        return (screenSize != Configuration.SCREENLAYOUT_SIZE_SMALL)
                && (screenSize != Configuration.SCREENLAYOUT_SIZE_NORMAL)
                && (screenSize != Configuration.SCREENLAYOUT_SIZE_LARGE);
    }

    public static  String getScreenSize() {
        String screenSize = null;

        if (AndroidUtils.isSmallScreen()) {
            screenSize = "small";
        }
        else if (AndroidUtils.isNormailScreen()) {
            screenSize = "normal";
        }
        else if (AndroidUtils.isLargeScreen()) {
            screenSize = "large";
        }
        else if (AndroidUtils.isXLargeScreen()) {
            screenSize = "xlarge";
        }

        return screenSize;
    }
    //endregion

    //region 屏幕密度(screen density)类型相关
    public static Boolean isLdpiDensity() {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        return metrics.densityDpi == DisplayMetrics.DENSITY_LOW;
    }

    public static Boolean isMdpiDensity() {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        return metrics.densityDpi == DisplayMetrics.DENSITY_MEDIUM;
    }

    public static Boolean isHdpiDensity() {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        return metrics.densityDpi == DisplayMetrics.DENSITY_HIGH;
    }

    public static Boolean isXHdpiDensity() {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        return metrics.densityDpi == DisplayMetrics.DENSITY_XHIGH;
    }

    public static Boolean isTVdpiDensity() {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        return metrics.densityDpi == DisplayMetrics.DENSITY_TV;
    }

    public static String getScreenDensity() {
        String density = null;

        if (AndroidUtils.isLdpiDensity()) {
            density = "ldpi";
        }
        else if (AndroidUtils.isMdpiDensity()) {
            density = "mdpi";
        }
        else if (AndroidUtils.isHdpiDensity()) {
            density = "hdpi";
        }
        else if (AndroidUtils.isXHdpiDensity()) {
            density = "xhdpi";
        }
        else if (AndroidUtils.isTVdpiDensity()) {
            density = "tvdpi";
        }

        return density;
    }

    public static DisplayMetrics getDisplayMetrics() {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        return metrics;
    }
    //endregion

    public static Boolean supportsContextualActionBar() {
        return !AndroidUtils.isPreHoneyComb() && !AndroidUtils.isGoogleTV();
    }

    public static int getStatusBarHeight(Context context) {
        int result     = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen"
                                                            , "android");

        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }

        return result;
    }

    //region 应用调试状态
    private static final X500Principal DEBUG_DN = new X500Principal("CN=Android Debug,O=Android,C=US");

    public static boolean isDebuggable(Context context) {
        boolean debuggable = false;

        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName()
                                    , PackageManager.GET_SIGNATURES);
            Signature signature[] = packageInfo.signatures;

            try {
                for (int i = 0; i < signature.length; i++) {
                    CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");

                    ByteArrayInputStream inputStream = new ByteArrayInputStream(signature[i].toByteArray());
                    X509Certificate certificate = (X509Certificate)certificateFactory.generateCertificate(inputStream);

                    debuggable = certificate.getSubjectX500Principal().equals(DEBUG_DN);

                    if (debuggable) {
                        break;
                    }
                }
            }
            catch (CertificateException e) {
                e.printStackTrace();
            }
        }
        catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return debuggable;
    }
    //endregion
    //===========================================================================
    /**
     * 获取APK包信息
     *
     * @return packageInfo
     */
    private static PackageInfo getPackageInfo() {
        PackageInfo packageInfo = null;

        try {
            Context context = JimAppBase.get();

            packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        }
        catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return packageInfo;
    }

    /**
     * 获取应用程序META_DATA信息
     * @return applicationInfo
     */
    private static ApplicationInfo getApplicationInfo() {
        ApplicationInfo applicationInfo = null;

        try {
            Context context = JimAppBase.get();

            applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
        }
        catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return applicationInfo;
    }
}
