package com.deskconn.deviceinfo.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.deskconn.deviceinfo.Adapter.AppAdapter;
import com.deskconn.deviceinfo.AppInfo;
import com.deskconn.deviceinfo.R;
import com.deskconn.deviceinfo.SplashScreen;

public class AppFragment extends Fragment {

    ListView appNameListView;
    AppAdapter appAdapter;


    public AppFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_app, container, false);

        appNameListView = view.findViewById(R.id.listview_apps);

        appAdapter = new AppAdapter(requireActivity(), SplashScreen.listUserApps);

        appNameListView.setAdapter(appAdapter);

        appNameListView.setOnItemClickListener((parent, view1, position, id) -> {
            AppInfo appInfo = SplashScreen.listUserApps.get(position);
            Intent intent1 = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            Uri uri = Uri.fromParts("package", appInfo.getPackageName(), null);
            intent1.setData(uri);
            startActivity(intent1);
        });


        return view;
    }

}