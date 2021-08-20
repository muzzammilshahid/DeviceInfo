package com.deskconn.deviceinfo.Fragments;

import android.app.ActivityManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.os.StatFs;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.deskconn.deviceinfo.R;

import static android.content.Context.ACTIVITY_SERVICE;


public class StorageFragment extends Fragment {

    private TextView totalRam;
    private TextView availRam;
    private TextView totalStorage;
    private TextView availStorage;
    private TextView percentageAvailableStorage;
    private TextView percentageAvailableRam;
    private ProgressBar progressBar;
    private ProgressBar progressBar1;

    public StorageFragment() {
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
        View view = inflater.inflate(R.layout.fragment_storage, container, false);
        totalRam = view.findViewById(R.id.ram_total);
        availRam = view.findViewById(R.id.ram_avail);
        totalStorage = view.findViewById(R.id.storage_total);
        availStorage = view.findViewById(R.id.storage_avail);
        percentageAvailableRam = view.findViewById(R.id.ram_avail_percentage);
        percentageAvailableStorage = view.findViewById(R.id.storage_avail_percentage);
        progressBar = view.findViewById(R.id.progress);
        progressBar1 = view.findViewById(R.id.progress1);

        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        ActivityManager activityManager = (ActivityManager) getActivity().getSystemService(ACTIVITY_SERVICE);
        activityManager.getMemoryInfo(mi);

        double availableGb = (mi.availMem / 0x100000L) / 1024;
        double totalGb = (mi.totalMem / 0x100000L) / 1024;

        //Percentage can be calculated for API 16+
        int percentAvail = 100 - (int) (mi.availMem / (double) mi.totalMem * 100.0);
        totalRam.setText(totalGb + "");
        availRam.setText(availableGb + "");
        percentageAvailableRam.setText(percentAvail + "%");
        progressBar.setProgress(percentAvail);


        StatFs stat = new StatFs(Environment.getExternalStorageDirectory().getPath());
        long bytesAvailable, totalBytes;
        if (android.os.Build.VERSION.SDK_INT >=
                android.os.Build.VERSION_CODES.JELLY_BEAN_MR2) {
            bytesAvailable = stat.getBlockSizeLong() * stat.getAvailableBlocksLong();
            totalBytes = stat.getBlockSizeLong() * stat.getBlockCountLong();
        } else {
            bytesAvailable = (long) stat.getBlockSize() * (long) stat.getAvailableBlocks();
            totalBytes = (long) stat.getBlockSize() * (long) stat.getBlockCount();
        }
        double gbAvailable = bytesAvailable / (1024 * 1024 * 1024);
        double gbTotal = totalBytes / (1024 * 1024 * 1024);
        int percentageAvail = 100 - (int) (gbAvailable / gbTotal * 100.0);

        totalStorage.setText(gbTotal + "");
        availStorage.setText(gbAvailable + "");
        percentageAvailableStorage.setText(percentageAvail + "%");
        progressBar1.setProgress(percentageAvail);


        return view;

    }
}