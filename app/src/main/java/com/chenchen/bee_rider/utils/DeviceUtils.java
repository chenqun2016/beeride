package com.chenchen.bee_rider.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import com.chenchen.bee_rider.MyApplication;

import java.io.File;

/**
 * 创建时间：2020/8/19
 * 编写人： 进京赶考
 * 功能描述：获取设备信息工具
 */

public class DeviceUtils {
    /**
     * 获取application中指定的meta-data。本例中，调用方法时key就是UMENG_CHANNEL
     * @return 如果没有获取成功(没有对应值，或者异常)，则返回值为空
     */
    public static String getAppMetaData() {

        String resultData = null;
        try {
            PackageManager packageManager = MyApplication.Companion.getMInstance().getPackageManager();
            if (packageManager != null) {
                ApplicationInfo applicationInfo = packageManager.getApplicationInfo(MyApplication.Companion.getMInstance().getPackageName(), PackageManager.GET_META_DATA);
                if (applicationInfo != null) {
                    if (applicationInfo.metaData != null) {
                        resultData = applicationInfo.metaData.getString("UMENG_CHANNEL");
                    }
                }

            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return resultData;
    }

    /**
     * 获取 IMEI 码
     * <p>需添加权限
     * {@code <uses-permission android:name="android.permission.READ_PHONE_STATE" />}</p>
     *
     * @return IMEI 码
     */
    @SuppressLint({"HardwareIds", "MissingPermission"})
    public static String getIMEI() {
//        TelephonyManager tm =
//                (TelephonyManager) BeeApplication.getInstance().getSystemService(Context.TELEPHONY_SERVICE);
//        return tm != null ? tm.getDeviceId() : "";

        return "";
    }


    /**
     * 获取 App 版本号
     *
     * @return App 版本号
     */
    public static String getAppVersionName() {
        return getAppVersionName(MyApplication.Companion.getMInstance().getPackageName());
    }

    /**
     * 获取 App 版本号
     *
     * @param packageName 包名
     * @return App 版本号
     */
    public static String getAppVersionName(final String packageName) {
        if (isSpace(packageName)) return null;
        try {
            PackageManager pm = MyApplication.Companion.getMInstance().getPackageManager();
            PackageInfo pi = pm.getPackageInfo(packageName, 0);
            return pi == null ? null : pi.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static boolean isSpace(final String s) {
        if (s == null) return true;
        for (int i = 0, len = s.length(); i < len; ++i) {
            if (!Character.isWhitespace(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }
    /**
     * 获取设备 AndroidID
     *
     * @return AndroidID
     */
    @SuppressLint("HardwareIds")
    public static String getAndroidID() {
        return Settings.Secure.getString(
                MyApplication.Companion.getMInstance().getContentResolver(),
                Settings.Secure.ANDROID_ID
        );
    }


    /**
     * 获取设备型号
     * <p>如 MI2SC</p>
     *
     * @return 设备型号
     */
    public static String getModel() {
        String model = Build.MODEL;
        if (model != null) {
            model = model.trim().replaceAll("\\s*", "");
        } else {
            model = "";
        }
        return model;
    }

    /**
     * 删除之前的apk
     *
     * @param apkName apk名字
     * @return
     */
    public static File clearApk(Context context, String apkName) {
        File apkFile = new File(context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), apkName);
        if (apkFile.exists()) {
            apkFile.delete();
        }
        return apkFile;
    }
}
