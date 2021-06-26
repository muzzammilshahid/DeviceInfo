package com.example.deviceinfo.Fragments;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.Settings;
import android.text.format.Formatter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.deviceinfo.R;

public class HomeFragment extends Fragment {

    TextView textViewIpAddress;
    TextView textViewHoursUsed;
    TextView textViewDeviceId;

    public HomeFragment() {
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
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        textViewIpAddress = view.findViewById(R.id.ip_address);
        textViewHoursUsed = view.findViewById(R.id.hours_used);
        textViewDeviceId = view.findViewById(R.id.device_id);

        Context context = getActivity().getApplicationContext();
        WifiManager wm = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        String ip = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());

        long timeUsed =  android.os.SystemClock.elapsedRealtime()/(1000*60*60);


        textViewIpAddress.setText(ip);
        textViewHoursUsed.setText(timeUsed+ " hours");
        textViewDeviceId.setText(Settings.Secure.getString(getActivity().getContentResolver(), Settings.Secure.ANDROID_ID));
        return view;
    }
}