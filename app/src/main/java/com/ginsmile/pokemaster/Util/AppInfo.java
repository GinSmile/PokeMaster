package com.ginsmile.pokemaster.Util;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xujin on 16/1/15.
 */
public class AppInfo {
    private String appLabel;
    private Drawable appIcon;
    private String pkgName;

    public static List<AppInfo> list_third;
    public static List<AppInfo> list_system;

    public AppInfo(){}

    public Drawable getAppIcon() {
        return appIcon;
    }

    public void setAppIcon(Drawable appIcon) {
        this.appIcon = appIcon;
    }

    public String getAppLabel() {
        return appLabel;
    }

    public void setAppLabel(String appLabel) {
        this.appLabel = appLabel;
    }

    public String getPkgName() {
        return pkgName;
    }

    public void setPkgName(String pkgName) {
        this.pkgName = pkgName;
    }




    public static List<AppInfo> getAppInfo(int flag, PackageManager pm){
        //获取所有的应用信息
        List<ApplicationInfo> listApplications = pm.getInstalledApplications(PackageManager.GET_UNINSTALLED_PACKAGES);
        List<AppInfo> myList = new ArrayList<AppInfo>();

        switch (flag){
            case 2://系统应用
                myList.clear();
                for(ApplicationInfo app:listApplications){
                    if((app.flags & ApplicationInfo.FLAG_SYSTEM) != 0) {
                        myList.add(makeAppInfo(app, pm));
                    }
                }
                break;
            case 1://第三方应用
                myList.clear();
                for(ApplicationInfo app:listApplications){
                    if((app.flags & ApplicationInfo.FLAG_SYSTEM) <= 0) {
                        myList.add(makeAppInfo(app, pm));
                    }
                }
                break;

        }

        return myList;

    }

    public static AppInfo makeAppInfo(ApplicationInfo app, PackageManager pm){
        AppInfo appInfo = new AppInfo();
        appInfo.setAppIcon(app.loadIcon(pm));
        appInfo.setAppLabel((String)app.loadLabel(pm));
        appInfo.setPkgName(app.packageName);

        return appInfo;
    }
}
