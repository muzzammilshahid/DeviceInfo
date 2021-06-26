package com.example.deviceinfo.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.deviceinfo.AppInfo;
import com.example.deviceinfo.R;
import com.example.deviceinfo.Adapter.SystemAppAdapter;

import java.util.ArrayList;

import static com.example.deviceinfo.MainActivity.list1;

public class SystemAppFragment extends Fragment {
    ListView appNameListView;
    SystemAppAdapter systemAppAdapter;
    ArrayList<AppInfo> list = new ArrayList<>();

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
        list = list1;
        systemAppAdapter = new SystemAppAdapter(getActivity(), list);
        appNameListView.setAdapter(systemAppAdapter);

        systemAppAdapter.notifyDataSetChanged();


        appNameListView.setOnItemClickListener((parent, view1, position, id) -> {
            AppInfo appInfo = list.get(position);
            Intent intent1 = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            Uri uri = Uri.fromParts("package",appInfo.getPackageName() , null);
            intent1.setData(uri);
            startActivity(intent1);
        });
        return view;
    }
}