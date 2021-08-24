package com.deskconn.deviceinfo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.deskconn.deviceinfo.databinding.ActivityMainBinding;
import com.deskconn.deviceinfo.ui.main.SectionsPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private DrawerLayout dLayout;
    private TabLayout tabs;

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
}