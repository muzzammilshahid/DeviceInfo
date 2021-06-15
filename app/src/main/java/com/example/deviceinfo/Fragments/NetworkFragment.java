package com.example.deviceinfo.Fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.TrafficStats;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.deviceinfo.R;

public class NetworkFragment extends Fragment {

    TextView isOnlineTextView;
    TextView wifiTextView;
    TextView mobileDataTextView;
    TextView transmitByteTextView;
    TextView receivedByteTextView;
    ConnectivityManager connMgr;

    private Handler mHandler = new Handler();
    private long mStartRX = 0;
    private long mStartTX = 0;

    public NetworkFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_network, container, false);
        isOnlineTextView = view.findViewById(R.id.is_online);
        wifiTextView = view.findViewById(R.id.wifi);
        mobileDataTextView = view.findViewById(R.id.mobile_data);
        transmitByteTextView = view.findViewById(R.id.transmit);
        receivedByteTextView = view.findViewById(R.id.received);
        connMgr = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);


        mStartRX = TrafficStats.getTotalRxBytes();
        mStartTX = TrafficStats.getTotalTxBytes();
        if (mStartRX == TrafficStats.UNSUPPORTED || mStartTX == TrafficStats.UNSUPPORTED) {
            AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
            alert.setTitle("Uh Oh!");
            alert.setMessage("Your device does not support traffic stat monitoring.");
            alert.show();
        } else {
            mHandler.postDelayed(mRunnable, 1000);
        }

        // Inflate the layout for this fragment
        return view;
    }

    private boolean isOnline() {
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

    public String getNetworkType() {
        NetworkInfo info = connMgr.getActiveNetworkInfo();
        return info.getTypeName();
    }

    private final Runnable mRunnable = new Runnable() {
        public void run() {
            long rxBytes = TrafficStats.getTotalRxBytes() - mStartRX;

            long txBytes = TrafficStats.getTotalTxBytes() - mStartTX;


            if (isOnline()) {
                transmitByteTextView.setText("" + rxBytes);
                receivedByteTextView.setText("" + txBytes);
                isOnlineTextView.setText("Yes");
                if (getNetworkType().equals("WIFI")) {
                    wifiTextView.setText("ON");
                    mobileDataTextView.setText("OFF");
                } else {
                    wifiTextView.setText("OFF");
                    mobileDataTextView.setText("ON");
                }
            } else {
                isOnlineTextView.setText("NO");
                wifiTextView.setText("Offline");
                mobileDataTextView.setText("Offline");
                transmitByteTextView.setText("Offline");
                receivedByteTextView.setText("Offline");
            }
            mHandler.postDelayed(mRunnable, 1000);
        }
    };
}