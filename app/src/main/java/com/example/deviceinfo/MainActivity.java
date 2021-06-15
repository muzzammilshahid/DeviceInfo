package com.example.deviceinfo;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.deviceinfo.databinding.ActivityMainBinding;
import com.example.deviceinfo.ui.main.SectionsPagerAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    public static ArrayList<AppInfo> list = new ArrayList<>();
    public static ArrayList<AppInfo> list1 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);

        Thread thread = new Thread() {
            @Override
            public void run() {
                super.run();
                list = getUserApps();

                list1 = getSystemApps();
            }
        };
        thread.start();

    }

    public ArrayList<AppInfo> getUserApps() {

        PackageManager pm = getPackageManager();
        List<ApplicationInfo> installedApps = pm.getInstalledApplications(0);
        for (ApplicationInfo aInfo : installedApps) {

            if ((aInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0) {
                // System apps
            } else {
                // Users apps
                AppInfo appInfo = new AppInfo();
                appInfo.setAppName(aInfo.loadLabel(pm).toString());
                appInfo.setPackageName(aInfo.packageName);
                try {
                    PackageInfo info = pm.getPackageInfo(aInfo.packageName, 0);
                    appInfo.setVersionName(info.versionName);
                    appInfo.setVersionCode(info.versionCode);
                    appInfo.setAppName(info.applicationInfo.loadLabel(getPackageManager()).toString());
                    appInfo.setIcon(info.applicationInfo.loadIcon(getPackageManager()));
                    list.add(appInfo);
                } catch (Exception e) {
                    Log.e("ERROR", "we could not get the user's apps");
                }

            }
        }
        return list;
    }

    public ArrayList<AppInfo> getSystemApps() {

        PackageManager pm = getPackageManager();
        List<ApplicationInfo> installedApps = pm.getInstalledApplications(0);
        for (ApplicationInfo aInfo : installedApps) {

            if ((aInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0) {
                // System apps
                AppInfo appInfo = new AppInfo();
                appInfo.setAppName(aInfo.loadLabel(pm).toString());
                appInfo.setPackageName(aInfo.packageName);
                try {
                    PackageInfo info = pm.getPackageInfo(aInfo.packageName, 0);
                    appInfo.setVersionName(info.versionName);
                    appInfo.setVersionCode(info.versionCode);
                    appInfo.setAppName(info.applicationInfo.loadLabel(getPackageManager()).toString());
                    appInfo.setIcon(info.applicationInfo.loadIcon(getPackageManager()));
                    list1.add(appInfo);
                } catch (Exception e) {
                    Log.e("ERROR", "we could not get the user's apps");
                }
            } else {
                // Users apps


            }
        }
        return list1;
    }
}