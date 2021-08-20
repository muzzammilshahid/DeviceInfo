package com.deskconn.deviceinfo.Fragments;

import android.annotation.SuppressLint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.deskconn.deviceinfo.MainActivity;
import com.deskconn.deviceinfo.R;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.content.Context.SENSOR_SERVICE;

public class CPUUsageFragment extends Fragment {


    boolean keepReading = true;
    String[] cpu0 = {"/system/bin/cat", "/sys/devices/system/cpu/cpu0/cpufreq/scaling_cur_freq"};
    String[] cpu1 = {"/system/bin/cat", "/sys/devices/system/cpu/cpu1/cpufreq/scaling_cur_freq"};
    String[] cpu2 = {"/system/bin/cat", "/sys/devices/system/cpu/cpu2/cpufreq/scaling_cur_freq"};
    String[] cpu3 = {"/system/bin/cat", "/sys/devices/system/cpu/cpu3/cpufreq/scaling_cur_freq"};
    String[] maxFreq = {"/system/bin/cat", "/sys/devices/system/cpu/cpu3/cpufreq/cpuinfo_max_freq"};


    String[] sensorFiles = new String[]{
            "/sys/devices/system/cpu/cpu0/cpufreq/cpu_temp",
            "/sys/devices/system/cpu/cpu0/cpufreq/FakeShmoo_cpu_temp",
            "/sys/class/thermal/thermal_zone1/temp",
            "/sys/class/i2c-adapter/i2c-4/4-004c/temperature",
            "/sys/devices/platform/tegra-i2c.3/i2c-4/4-004c/temperature",
            "/sys/devices/platform/omap/omap_temp_sensor.0/temperature",
            "/sys/devices/platform/tegra_tmon/temp1_input",
            "/sys/kernel/debug/tegra_thermal/temp_tj",
            "/sys/devices/platform/s5p-tmu/temperature",
            "/sys/class/thermal/thermal_zone0/temp",
            "/sys/devices/virtual/thermal/thermal_zone0/temp",
            "/sys/class/hwmon/hwmon0/device/temp1_input",
            "/sys/devices/virtual/thermal/thermal_zone1/temp",
            "/sys/devices/platform/s5p-tmu/curr_temp",
            "/sys/htc/cpu_temp",
            "/sys/devices/platform/tegra-i2c.3/i2c-4/4-004c/ext_temperature",
            "/sys/devices/platform/tegra-tsensor/tsensor_temperature",
            "/sys/devices/system/cpu/cpufreq/cput_attributes/cur_temp"
    };


    String pattern = "([0-9]{6,7})";
    Pattern pat = Pattern.compile(pattern);


    int foo = 0;


    MainActivity mainActivity = new MainActivity();

    public CPUUsageFragment() {
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
        View view = inflater.inflate(R.layout.fragment_cpu_usage, container, false);
        SensorManager mSensorManager = (SensorManager) getActivity().getSystemService(SENSOR_SERVICE);
        Sensor TempSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        mSensorManager.registerListener(temperatureSensor, TempSensor, SensorManager.SENSOR_DELAY_NORMAL);
        keepReading = true;

        final TextView tv_core0 = view.findViewById(R.id.core0freq);
        final TextView tv_core1 = view.findViewById(R.id.core1freq);
        final TextView tv_core2 = view.findViewById(R.id.core2freq);
        final TextView tv_core3 = view.findViewById(R.id.core3freq);
        final TextView tv_maxFreq = view.findViewById(R.id.tv_maxFreq);
        final TextView tv_cputemp = view.findViewById(R.id.tv_cpu_temp);


        final File cpu0file = new File("/sys/devices/system/cpu/cpu0/cpufreq/scaling_cur_freq");
        final File cpu1file = new File("/sys/devices/system/cpu/cpu1/cpufreq/scaling_cur_freq");
        final File cpu2file = new File("/sys/devices/system/cpu/cpu2/cpufreq/scaling_cur_freq");
        final File cpu3file = new File("/sys/devices/system/cpu/cpu3/cpufreq/scaling_cur_freq");
        final File maxcpufreqfile = new File("/sys/devices/system/cpu/cpu3/cpufreq/cpuinfo_max_freq");

        if (maxcpufreqfile.exists()) {
            tv_maxFreq.setText(formatCPUFreq(ReadCPU0(maxFreq)) + " Mhz");
        } else tv_maxFreq.setText("N/A");

        Thread thread = new Thread() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void run() {
                try {
                    while (keepReading) {
                        Thread.sleep(1000);
                        mainActivity.runOnUiThread(() -> {

                            if (cpu0file.exists()) {
                                tv_core0.setText(formatCPUFreq(ReadCPU0(cpu0)) + " MHz");
                            } else {
                                tv_core0.setText("Offline");
                            }

                            if (cpu1file.exists()) {
                                //tv_cpu2file.setText("Yes");
                                tv_core1.setText(formatCPUFreq(ReadCPU0(cpu1)) + " MHz");
                            } else {
                                //tv_cpu2file.setText("No");
                                tv_core1.setText("Offline");
                            }

                            if (cpu2file.exists()) {
                                tv_core2.setText(formatCPUFreq(ReadCPU0(cpu2)) + " MHz");
                            } else {
                                tv_core2.setText("Offline");
                            }

                            if (cpu3file.exists()) {
                                tv_core3.setText(formatCPUFreq(ReadCPU0(cpu3)) + " MHz");
                            } else {
                                tv_core3.setText("Offline");
                            }


                            File correctSensorFile = null;
                            for (String file : sensorFiles) {
                                File f = new File(file);
                                if (f.exists()) {
                                    correctSensorFile = f;
                                    System.out.println("millo  " + correctSensorFile);
                                    break;
                                }
                            }

                            RandomAccessFile reader = null;
                            try {
                                reader = new RandomAccessFile(correctSensorFile, "r");
                                String value = reader.readLine();
                                float temp = Float.parseFloat(value);
                                temp = temp / 1000.0f;
                                tv_cputemp.setText("" + temp);
                                foo = Integer.parseInt(String.valueOf(temp));
                                reader.close();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        });
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();


        return view;
    }


    private String ReadCPU0(String[] input) {
        ProcessBuilder pB;
        String result = "";

        try {
            String[] args = {"/system/bin/cat", "/sys/devices/system/cpu/cpu0/cpufreq/scaling_cur_freq"};
            pB = new ProcessBuilder(input);
            pB.redirectErrorStream(false);
            Process process = pB.start();
            InputStream in = process.getInputStream();
            byte[] re = new byte[1024];
            while (in.read(re) != -1) //default -1
            {
                //System.out.println(new String(re));
                result = new String(re);
            }
            in.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        System.out.println("millis " + result);
        return result;
    }

    private String formatCPUFreq(String cpuFreq) {

        String uwot = "";
        Matcher m = pat.matcher(cpuFreq);
        if (m.find()) {
            uwot = m.group(0);
            return uwot.substring(0, uwot.length() - 3);
        } else return "Error";
    }

    private SensorEventListener temperatureSensor = new SensorEventListener() {

        @Override
        public void onSensorChanged(SensorEvent event) {
            System.out.println("on sensor changed called");
            float temp = event.values[0];
            System.out.println("Temperature sensor: " + temp);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        keepReading = false;
    }


    @Override
    public void onStart() {
        super.onStart();
        keepReading = true;
    }

    public void onStop() {
        super.onStop();
        keepReading = false;      //don't disable otherwise stops reading after going to sleep
    }

    public void onResume() {
        super.onResume();
        keepReading = true;
    }
}