package com.deskconn.deviceinfo;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.deskconn.deviceinfo.databinding.ActivityMainBinding;
import com.deskconn.deviceinfo.ui.main.SectionsPagerAdapter;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private DrawerLayout dLayout;
    private TabLayout tabs;

    public static ArrayList<AppInfo> listUserApps = new ArrayList<>();

    public static ArrayList<AppInfo> listSystemApps = new ArrayList<>();

    private boolean keep = true;

    private AdView adView;

    private AdView adView1;

    public AdRequest adRequest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        androidx.core.splashscreen.SplashScreen splashScreen = SplashScreen.installSplashScreen(this);


        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        adRequest = new AdRequest.Builder().build();

        adView = binding.adView;
        adView1 = binding.adView1;

        adView.loadAd(adRequest);
        adView1.loadAd(adRequest);

        splashScreen.setKeepOnScreenCondition(() -> keep);

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executor.execute(() -> {
            getAllApps();
            handler.post(() -> keep = false);
        });

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);
        tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);

        ImageView imageView = findViewById(R.id.imageView);
        dLayout = findViewById(R.id.drawerLayout);

        imageView.setOnClickListener(v -> {
            dLayout.openDrawer(GravityCompat.START);
        });

    }

    public void ClickHome(View view) {
        tabs.getTabAt(0).select();
        dLayout.closeDrawers();
    }

    public void ClickNetwork(View view) {
        tabs.getTabAt(1).select();
        dLayout.closeDrawers();
    }

    public void ClickDisplay(View view) {
        tabs.getTabAt(2).select();
        dLayout.closeDrawers();
    }

    public void ClickBattery(View view) {
        tabs.getTabAt(3).select();
        dLayout.closeDrawers();
    }

    public void ClickApps(View view) {
        tabs.getTabAt(4).select();
        dLayout.closeDrawers();
    }

    public void ClickSystemApps(View view) {
        tabs.getTabAt(5).select();
        dLayout.closeDrawers();
    }

    public void ClickCPU(View view) {
        tabs.getTabAt(6).select();
        dLayout.closeDrawers();
    }

    public void ClickStorage(View view) {
        tabs.getTabAt(7).select();
        dLayout.closeDrawers();
    }

    public void ClickDevice(View view) {
        tabs.getTabAt(8).select();
        dLayout.closeDrawers();
    }

    public void ClickShareApp(View view) {
        try {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name");
            String shareMessage = "";

            shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID + "\n\n";
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
            startActivity(Intent.createChooser(shareIntent, "choose one"));
            dLayout.closeDrawers(); // close the all open Drawer Views
        } catch (Exception e) {
            System.out.println("Inside catch");
            dLayout.closeDrawers(); // close the all open Drawer Views
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