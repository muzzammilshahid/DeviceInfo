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


public class DeviceFragment extends Fragment {
    TextView deviceName;
    TextView manufacturer;
    TextView model;
    TextView androidVersion;
    TextView buildNumber;
    TextView cpu;

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
        deviceName.setText(Settings.Secure.getString(getActivity().getContentResolver(), "bluetooth_name"));

        manufacturer.setText(Build.MANUFACTURER);

        model.setText(Build.MODEL);

        androidVersion.setText(Build.VERSION.RELEASE);

        cpu.setText(Build.HARDWARE);

        buildNumber.setText(Build.DISPLAY);


        return view;
    }
}