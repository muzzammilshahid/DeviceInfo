package com.deskconn.deviceinfo.Fragments;

import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.deskconn.deviceinfo.R;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;


public class DeviceFragment extends Fragment {
    TextView deviceName;
    TextView manufacturer;
    TextView model;
    TextView androidVersion;
    TextView buildNumber;
    TextView cpu;
    TextView securityPatchLevel;
    TextView basebandVersion;
    TextView kernelVersion;
    TextView resolution;

    public DeviceFragment() {
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
        View view = inflater.inflate(R.layout.fragment_device, container, false);
        deviceName = view.findViewById(R.id.device_name);
        manufacturer = view.findViewById(R.id.manufacturer);
        model = view.findViewById(R.id.model);
        androidVersion = view.findViewById(R.id.android_version);
        buildNumber = view.findViewById(R.id.build_number);
        cpu = view.findViewById(R.id.cpu);
        resolution = view.findViewById(R.id.resolution);
        securityPatchLevel = view.findViewById(R.id.security_patch_level);
        basebandVersion = view.findViewById(R.id.baseband_version);
        kernelVersion = view.findViewById(R.id.kernel_version);

        deviceName.setText(Settings.Secure.getString(requireActivity().getContentResolver(), "bluetooth_name"));

        manufacturer.setText(Build.MANUFACTURER);

        model.setText(Build.MODEL);

        androidVersion.setText(Build.VERSION.RELEASE);

        cpu.setText(Build.HARDWARE);

        buildNumber.setText(Build.DISPLAY);

        resolution.setText(getResources().getDisplayMetrics().heightPixels + " x " + getResources().getDisplayMetrics().widthPixels);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            securityPatchLevel.setText(Build.VERSION.SECURITY_PATCH.trim());
        }

        basebandVersion.setText(Build.getRadioVersion().trim());

        kernelVersion.setText(getKernelVersion());
        return view;
    }

    public static String getKernelVersion() {
        try {
            Process p = Runtime.getRuntime().exec("uname -a");
            InputStream is;
            if (p.waitFor() == 0) {
                is = p.getInputStream();
            } else {
                is = p.getErrorStream();
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = br.readLine();
            br.close();
            return line;
        } catch (Exception ex) {
            return "ERROR: " + ex.getMessage();
        }
    }

}