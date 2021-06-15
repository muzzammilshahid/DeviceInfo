package com.example.deviceinfo.Fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.deviceinfo.Adapter.AppAdapter;
import com.example.deviceinfo.AppInfo;
import com.example.deviceinfo.MainActivity;
import com.example.deviceinfo.R;

import java.util.ArrayList;

public class AppFragment extends Fragment {

    ListView appNameListView;
    AppAdapter appAdapter;
    ArrayList<AppInfo> list = new ArrayList<>();


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
        ProgressDialog progressDialog;
        progressDialog = ProgressDialog.show(getActivity(), "", "Please Wait Apps are loading...", true);
        progressDialog.show();
        list = MainActivity.list;
        progressDialog.dismiss();
        appAdapter = new AppAdapter(getActivity(), list);
        appNameListView.setAdapter(appAdapter);
        appAdapter.notifyDataSetChanged();

        return view;
    }


}