package com.deskconn.deviceinfo.Fragments;

import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.deskconn.deviceinfo.R;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

public class HomeFragment extends Fragment {

    TextView textViewIpAddress;
    TextView textViewHoursUsed;
    TextView textViewDeviceId;
    TextView textViewMacAddress;

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
        textViewMacAddress = view.findViewById(R.id.mac_address);


        StringBuilder IFCONFIG = new StringBuilder();
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress() && !inetAddress.isLinkLocalAddress() && inetAddress.isSiteLocalAddress()) {
                        IFCONFIG.append(inetAddress.getHostAddress() + "\n");
                    }

                }
            }
        } catch (SocketException ex) {
            ex.printStackTrace();
        }

        textViewMacAddress.setText(getWifiMacAddress());


        long timeUsed = android.os.SystemClock.elapsedRealtime() / (1000 * 60 * 60);

        textViewIpAddress.setText(IFCONFIG.toString().trim());
        textViewHoursUsed.setText(timeUsed + " hours");
        textViewDeviceId.setText(Settings.Secure.getString(getActivity().getContentResolver(), Settings.Secure.ANDROID_ID));
        return view;
    }

    public static String getWifiMacAddress() {
        try {
            String interfaceName = "wlan0";
            List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface intF : interfaces) {
                if (!intF.getName().equalsIgnoreCase(interfaceName)) {
                    continue;
                }

                byte[] mac = intF.getHardwareAddress();
                if (mac == null) {
                    return "N/A in Android11";
                }

                StringBuilder buf = new StringBuilder();
                for (byte aMac : mac) {
                    buf.append(String.format("%02X:", aMac));
                }
                if (buf.length() > 0) {
                    buf.deleteCharAt(buf.length() - 1);
                }
                return buf.toString();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "N/A in Android11";
    }
}