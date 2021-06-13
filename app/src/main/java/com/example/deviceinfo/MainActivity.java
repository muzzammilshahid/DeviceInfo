package com.example.deviceinfo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    DrawerLayout dLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dLayout = findViewById(R.id.drawer_layout);

        Fragment frag = null;
        frag = new NetworkFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame, frag); // replace a Fragment with Frame Layout
        transaction.commit(); // commit the changes
        dLayout.closeDrawers(); // close the all open Drawer Views
    }

    public void ClickMenu(View view){
        dLayout.openDrawer(GravityCompat.START);
    }

    public void ClickNetwork(View view){
        Fragment frag = null;
        frag = new NetworkFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame, frag); // replace a Fragment with Frame Layout
        transaction.commit(); // commit the changes
        dLayout.closeDrawers(); // close the all open Drawer Views
    }

    public void ClickDevice(View view){
        Fragment frag = null;
        frag = new DeviceFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame, frag); // replace a Fragment with Frame Layout
        transaction.commit(); // commit the changes
        dLayout.closeDrawers(); // close the all open Drawer Views
    }

    public void ClickStorage(View view){
        Fragment frag = null;
        frag = new StorageFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame, frag); // replace a Fragment with Frame Layout
        transaction.commit(); // commit the changes
        dLayout.closeDrawers(); // close the all open Drawer Views
    }

    public void ClickCPUUsage(View view){
        Fragment frag = null;
        frag = new CPUUsageFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame, frag); // replace a Fragment with Frame Layout
        transaction.commit(); // commit the changes
        dLayout.closeDrawers(); // close the all open Drawer Views
    }

    public void ClickBattery(View view){
        Fragment frag = null;
        frag = new BatteryFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame, frag); // replace a Fragment with Frame Layout
        transaction.commit(); // commit the changes
        dLayout.closeDrawers(); // close the all open Drawer Views
    }

    public void ClickApps(View view){
        Fragment frag = null;
        frag = new AppFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame, frag); // replace a Fragment with Frame Layout
        transaction.commit(); // commit the changes
        dLayout.closeDrawers(); // close the all open Drawer Views
    }

    public void ClickBackgroundApps(View view){
        Fragment frag = null;
        frag = new BackgroundFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame, frag); // replace a Fragment with Frame Layout
        transaction.commit(); // commit the changes
        dLayout.closeDrawers(); // close the all open Drawer Views
    }

    public void ClickDisplay(View view){
        Fragment frag = null;
        frag = new DisplayFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame, frag); // replace a Fragment with Frame Layout
        transaction.commit(); // commit the changes
        dLayout.closeDrawers(); // close the all open Drawer Views
    }

    public void ClickShareApp(View view){
//        try {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name");
            String shareMessage= "";
            shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID +"\n\n";
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
            startActivity(Intent.createChooser(shareIntent, "choose one"));
//        } catch(Exception e) {
//            System.out.println("Inside catch");
//            //e.toString();
//        }
    }

}