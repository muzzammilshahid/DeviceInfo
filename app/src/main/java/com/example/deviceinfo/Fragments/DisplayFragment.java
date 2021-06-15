package com.example.deviceinfo.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.deviceinfo.R;

public class DisplayFragment extends Fragment {

    TextView width;
    TextView height;
    TextView scaledDensity;
    TextView densityDpi;
    TextView xdpi;
    TextView ydpi;

    public DisplayFragment() {
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
        View view = inflater.inflate(R.layout.fragment_display, container, false);

        width = view.findViewById(R.id.width);
        height = view.findViewById(R.id.height);
        scaledDensity = view.findViewById(R.id.scaled_density);
        densityDpi = view.findViewById(R.id.density_dpi);
        xdpi = view.findViewById(R.id.xdpi);
        ydpi = view.findViewById(R.id.ydpi);
        String displayResolution = getResources().getDisplayMetrics().widthPixels + "x" + getResources().getDisplayMetrics().heightPixels;
        System.out.println("here is screen " + displayResolution + "    Density Dpi   " + getResources().getDisplayMetrics().densityDpi +
                "  Scaled Density  " + getResources().getDisplayMetrics().scaledDensity +
                " xdpi  " + getResources().getDisplayMetrics().xdpi + " ydpi   "
                + getResources().getDisplayMetrics().ydpi);
        width.setText("" + getResources().getDisplayMetrics().widthPixels);
        height.setText("" + getResources().getDisplayMetrics().heightPixels);
        scaledDensity.setText(getResources().getDisplayMetrics().scaledDensity + "");
        densityDpi.setText("" + getResources().getDisplayMetrics().densityDpi);
        xdpi.setText(getResources().getDisplayMetrics().xdpi + "");
        ydpi.setText(getResources().getDisplayMetrics().scaledDensity + "");

        return view;
    }
}