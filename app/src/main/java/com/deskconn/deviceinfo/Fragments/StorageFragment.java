package com.deskconn.deviceinfo.Fragments;

import static android.content.Context.ACTIVITY_SERVICE;

import android.app.ActivityManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.deskconn.deviceinfo.R;

import java.io.File;


public class StorageFragment extends Fragment {

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
        TextView totalRam = view.findViewById(R.id.ram_total);
        TextView availRam = view.findViewById(R.id.ram_avail);
        TextView totalStorage = view.findViewById(R.id.storage_total);
        TextView availStorage = view.findViewById(R.id.storage_avail);
        TextView percentageAvailableRam = view.findViewById(R.id.ram_avail_percentage);
        TextView percentageAvailableStorage = view.findViewById(R.id.storage_avail_percentage);
        ProgressBar progressBar = view.findViewById(R.id.progress);
        ProgressBar progressBar1 = view.findViewById(R.id.progress1);

        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        ActivityManager activityManager = (ActivityManager) requireActivity().getSystemService(ACTIVITY_SERVICE);
        activityManager.getMemoryInfo(mi);

        int availableGb = (int) (mi.availMem / 0x100000L);
        int totalGb = (int) (mi.totalMem / 0x100000L);

        int percentAvail = 100 - (int) (mi.availMem / (double) mi.totalMem * 100.0);
        totalRam.setText(totalGb + " MiB");
        availRam.setText(availableGb + " MiB");
        percentageAvailableRam.setText(percentAvail + "%");
        progressBar.setProgress(percentAvail);


        StatFs stat = new StatFs(Environment.getExternalStorageDirectory().getPath());
        double bytesAvailable, totalBytes;
        if (android.os.Build.VERSION.SDK_INT >=
                android.os.Build.VERSION_CODES.JELLY_BEAN_MR2) {
            bytesAvailable = stat.getBlockSizeLong() * stat.getAvailableBlocksLong();
            totalBytes = stat.getBlockSizeLong() * stat.getBlockCountLong();
        } else {
            bytesAvailable = stat.getBlockSize() * stat.getAvailableBlocks();
            totalBytes = stat.getBlockSize() * stat.getBlockCount();
        }
        double gbAvailable = bytesAvailable / (1024 * 1024 * 1024);
        double gbTotal = totalBytes / (1024 * 1024 * 1024);

        int percentageAvail = 100 - (int) (gbAvailable / gbTotal * 100.0);

        totalStorage.setText(formatSize(getTotalInternalMemorySize()));
        availStorage.setText(formatSize(getAvailableInternalMemorySize()));
        percentageAvailableStorage.setText(percentageAvail + "%");
        progressBar1.setProgress(percentageAvail);


        return view;

    }

    static public double getAvailableInternalMemorySize() {
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        double blockSize = stat.getBlockSize();
        double availableBlocks = stat.getAvailableBlocks();
        return availableBlocks * blockSize;
    }

    static public long getTotalInternalMemorySize() {
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long totalBlocks = stat.getBlockCount();
        return totalBlocks * blockSize;
    }


    public String formatSize(double size) {

        if (size >= 1024) {
            size /= 1024;
            if (size >= 1024) {
                size /= 1024;
                if ((size >= 1024)) {
                    size /= 1024;
                }
            }
        }

        String sizeString = "" + size;

        return sizeString.substring(0, 5) + " GiB";
    }
}
