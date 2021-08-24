package com.deskconn.deviceinfo;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class SplashScreen extends AppCompatActivity {

    public static ArrayList<AppInfo> listUserApps = new ArrayList<>();

    public static ArrayList<AppInfo> listSystemApps = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new AddStringTask().execute();

    }

    class AddStringTask extends AsyncTask<Void, Void, ArrayList<AppInfo>> {
        @Override
        protected ArrayList<AppInfo> doInBackground(Void... params) {

            return getAllApps();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }


        @Override
        protected void onPostExecute(ArrayList<AppInfo> items) {
            // stop the loading animation or something
            Intent intent = new Intent(SplashScreen.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            SplashScreen.this.finish();
        }
    }


    public ArrayList<AppInfo> getAllApps() {

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
                    listSystemApps.add(appInfo);
                } catch (Exception e) {
                    Log.e("ERROR", "we could not get the user's apps");
                }
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
                    listUserApps.add(appInfo);
                } catch (Exception e) {
                    Log.e("ERROR", "we could not get the user's apps");
                }

            }

        }

        return listSystemApps;
    }
}