package com.example.deviceinfo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
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

    private ImageView imageView;

    private DrawerLayout dLayout;
    private TabLayout tabs;

    private ProgressDialog progressDialog;
    private ProgressDialog progressDialog1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);
        tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);


        progressDialog = new ProgressDialog(this);
        progressDialog1 = new ProgressDialog(this);

        //Setting Dialog Title
        progressDialog.setTitle("loading...");
        progressDialog1.setTitle("loading...");

        //Setting Dialog Message
        progressDialog.setMessage("Getting Information from android...");
        progressDialog1.setMessage("Getting Information from android...");

        progressDialog.show();
        progressDialog1.show();
        progressDialog.setCancelable(false);
        progressDialog1.setCancelable(false);
        imageView = findViewById(R.id.imageView);
        dLayout = findViewById(R.id.drawerLayout);
        imageView.setOnClickListener(v -> {
            dLayout.openDrawer(GravityCompat.START);
        });

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
        progressDialog1.dismiss();
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
        progressDialog.dismiss();
        return list1;
    }

    public void ClickHome(View view){
        tabs.getTabAt(0).select();
        dLayout.closeDrawers(); // close the all open Drawer Views
    }

    public void ClickDevice(View view){
        tabs.getTabAt(1).select();
        dLayout.closeDrawers(); // close the all open Drawer Views
    }

//    public void ClickStorage(View view){
//        tabs.getTabAt(2).select();
//        dLayout.closeDrawers(); // close the all open Drawer Views
//    }
//
//    public void ClickCPU(View view){
//        tabs.getTabAt(3).select();
//        dLayout.closeDrawers(); // close the all open Drawer Views
//    }
//
//    public void ClickBattery(View view){
//        tabs.getTabAt(4).select();
//        dLayout.closeDrawers(); // close the all open Drawer Views
//    }

    public void ClickApps(View view){
        tabs.getTabAt(4).select();
        dLayout.closeDrawers(); // close the all open Drawer Views
    }

//    public void ClickSystemApps(View view){
//        tabs.getTabAt(6).select();
//        dLayout.closeDrawers(); // close the all open Drawer Views
//    }
//
//    public void ClickDisplay(View view){
//        tabs.getTabAt(7).select();
//        dLayout.closeDrawers(); // close the all open Drawer Views
//    }

    public void ClickShareApp(View view){
        try {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name");
            String shareMessage= "";
            
            shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID +"\n\n";
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
            startActivity(Intent.createChooser(shareIntent, "choose one"));
            dLayout.closeDrawers(); // close the all open Drawer Views
        } catch(Exception e) {
            System.out.println("Inside catch");
            dLayout.closeDrawers(); // close the all open Drawer Views
            //e.toString();
        }
    }
}