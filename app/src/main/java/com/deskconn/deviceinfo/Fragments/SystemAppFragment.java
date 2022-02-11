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

import com.deskconn.deviceinfo.Adapter.SystemAppAdapter;
import com.deskconn.deviceinfo.AppInfo;
import com.deskconn.deviceinfo.MainActivity;
import com.deskconn.deviceinfo.R;

public class SystemAppFragment extends Fragment {
    ListView appNameListView;
    SystemAppAdapter systemAppAdapter;

    public SystemAppFragment() {
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
        View view = inflater.inflate(R.layout.fragment_background, container, false);
        appNameListView = view.findViewById(R.id.listview_apps);

        systemAppAdapter = new SystemAppAdapter(requireActivity(), MainActivity.listSystemApps);

        appNameListView.setAdapter(systemAppAdapter);

        appNameListView.setOnItemClickListener((parent, view1, position, id) -> {
            AppInfo appInfo = MainActivity.listSystemApps.get(position);
            Intent intent1 = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            Uri uri = Uri.fromParts("package", appInfo.getPackageName(), null);
            intent1.setData(uri);
            startActivity(intent1);
        });
        return view;
    }
}