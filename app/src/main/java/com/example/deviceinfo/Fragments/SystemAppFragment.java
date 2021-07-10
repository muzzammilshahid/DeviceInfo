package com.example.deviceinfo.Fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.deviceinfo.AppInfo;
import com.example.deviceinfo.R;
import com.example.deviceinfo.Adapter.SystemAppAdapter;

import java.util.ArrayList;
import java.util.List;

import static com.example.deviceinfo.MainActivity.list1;

public class SystemAppFragment extends Fragment {
    ListView appNameListView;
    SystemAppAdapter systemAppAdapter;
    ArrayList<AppInfo> list = new ArrayList<>();
    private ProgressDialog progressDialog1;

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


        progressDialog1 = new ProgressDialog(getActivity());
        progressDialog1.setMessage("Please Wait Apps are loading...");
        progressDialog1.show();
        progressDialog1.setCancelable(false);

        new AddStringTask().execute();

        appNameListView.setOnItemClickListener((parent, view1, position, id) -> {
            AppInfo appInfo = list.get(position);
            Intent intent1 = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            Uri uri = Uri.fromParts("package", appInfo.getPackageName(), null);
            intent1.setData(uri);
            startActivity(intent1);
        });
        return view;
    }

    public ArrayList<AppInfo> getSystemApps() {

        PackageManager pm = getActivity().getPackageManager();
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
                    appInfo.setAppName(info.applicationInfo.loadLabel(getActivity().getPackageManager()).toString());
                    appInfo.setIcon(info.applicationInfo.loadIcon(getActivity().getPackageManager()));
                    list.add(appInfo);
                } catch (Exception e) {
                    Log.e("ERROR", "we could not get the user's apps");
                }
            } else {
                // Users apps

            }

        }
        progressDialog1.dismiss();
        return list;
    }

    class AddStringTask extends AsyncTask<Void, Void, ArrayList<AppInfo>> {
        @Override
        protected ArrayList<AppInfo> doInBackground(Void... params) {

            list = getSystemApps();
            return list;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }


        @Override
        protected void onPostExecute(ArrayList<AppInfo> items) {
            // stop the loading animation or something

            systemAppAdapter = new SystemAppAdapter(getActivity(), list);

            appNameListView.setAdapter(systemAppAdapter);
        }
    }
}