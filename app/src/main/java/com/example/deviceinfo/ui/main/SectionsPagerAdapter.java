package com.example.deviceinfo.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.deviceinfo.Fragments.AppFragment;
import com.example.deviceinfo.Fragments.BackgroundFragment;
import com.example.deviceinfo.Fragments.BatteryFragment;
import com.example.deviceinfo.Fragments.CPUUsageFragment;
import com.example.deviceinfo.Fragments.DeviceFragment;
import com.example.deviceinfo.Fragments.DisplayFragment;
import com.example.deviceinfo.Fragments.NetworkFragment;
import com.example.deviceinfo.R;
import com.example.deviceinfo.Fragments.StorageFragment;

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.network, R.string.device,
            R.string.storage, R.string.cpu_usage, R.string.battery, R.string.apps, R.string.background_apps, R.string.Display};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new NetworkFragment();
                break;
            case 1:
                fragment = new DeviceFragment();
                break;
            case 2:
                fragment = new StorageFragment();
                break;
            case 3:
                fragment = new CPUUsageFragment();
                break;
            case 4:
                fragment = new BatteryFragment();
                break;
            case 5:
                fragment = new AppFragment();
                break;
            case 6:
                fragment = new BackgroundFragment();
                break;
            case 7:
                fragment = new DisplayFragment();
                break;
        }
        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 8;
    }
}