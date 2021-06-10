package com.example.deviceinfo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

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

    public void ClickCPU(View view){
        Fragment frag = null;
        frag = new CPUFragment();
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

}