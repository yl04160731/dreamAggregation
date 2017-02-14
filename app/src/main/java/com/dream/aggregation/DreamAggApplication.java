package com.dream.aggregation;

import android.app.Application;
import android.content.Context;
import android.support.v7.app.AppCompatDelegate;

import com.dream.aggregation.activity.BaseActivity;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by inno-y on 2017/2/6.
 */

public class DreamAggApplication extends Application {

    public final static String TAG = "BaseApplication";
    public final static boolean DEBUG = true;
    private static DreamAggApplication dreamAggApplication;
    private static int mainTid;

    /**
     * Activity集合，来管理所有的Activity
     */
    private static List<BaseActivity> activities;

    static {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
    }

    public static Application getContext() {
        return dreamAggApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        dreamAggApplication = this;
        activities = new LinkedList<>();
        mainTid = android.os.Process.myTid();
    }

    /**
     * 获取application
     *
     * @return
     */
    public static Context getApplication() {
        return dreamAggApplication;
    }

    /**
     * 获取主线程ID
     *
     * @return
     */
    public static int getMainTid() {
        return mainTid;
    }

    /**
     * 添加一个Activity
     *
     * @param activity
     */
    public void addActivity(BaseActivity activity) {
        activities.add(activity);
    }

    /**
     * 结束一个Activity
     *
     * @param activity
     */
    public void removeActivity(BaseActivity activity) {
        activities.remove(activity);
    }

    /**
     * 结束当前所有Activity
     */
    public static void clearActivities() {
        ListIterator<BaseActivity> iterator = activities.listIterator();
        BaseActivity activity;
        while (iterator.hasNext()) {
            activity = iterator.next();
            if (activity != null) {
                activity.finish();
            }
        }
    }

    /**
     * 退出应运程序
     */
    public static void quiteApplication() {
        clearActivities();
        System.exit(0);
    }
}
