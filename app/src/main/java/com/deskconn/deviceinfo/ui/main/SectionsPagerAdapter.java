package com.deskconn.deviceinfo.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.deskconn.deviceinfo.Fragments.AppFragment;
import com.deskconn.deviceinfo.Fragments.BatteryFragment;
import com.deskconn.deviceinfo.Fragments.CPUUsageFragment;
import com.deskconn.deviceinfo.Fragments.DeviceFragment;
import com.deskconn.deviceinfo.Fragments.DisplayFragment;
import com.deskconn.deviceinfo.Fragments.HomeFragment;
import com.deskconn.deviceinfo.Fragments.NetworkFragment;
import com.deskconn.deviceinfo.Fragments.StorageFragment;
import com.deskconn.deviceinfo.Fragments.SystemAppFragment;
import com.deskconn.deviceinfo.R;

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.home, R.string.network, R.string.Display,
            R.string.battery, R.string.apps, R.string.background_apps, R.string.cpu_usage,
            R.string.storage
            , R.string.device};
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
                fragment = new HomeFragment();
                break;
            case 1:
                fragment = new NetworkFragment();
                break;
            case 2:
                fragment = new DisplayFragment();
                break;
            case 3:
                fragment = new BatteryFragment();
                break;
            case 4:
                fragment = new AppFragment();
                break;
            case 5:
                fragment = new SystemAppFragment();
                break;
            case 6:
                fragment = new CPUUsageFragment();
                break;
            case 7:
                fragment = new StorageFragment();
                break;
            case 8:
                fragment = new DeviceFragment();
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
        return 9;
    }
}