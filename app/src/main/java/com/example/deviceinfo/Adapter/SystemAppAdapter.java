package com.example.deviceinfo.Adapter;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.deviceinfo.AppInfo;
import com.example.deviceinfo.R;

import java.util.ArrayList;

public class SystemAppAdapter extends ArrayAdapter<String> {
    private ArrayList<AppInfo> list;
    private Activity context;

    public SystemAppAdapter(Activity context, ArrayList<AppInfo> list) {
        super(context.getApplicationContext(), R.layout.listview_apps);

        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = context.getLayoutInflater().inflate(R.layout.listview_apps, parent,
                    false);
            viewHolder = new ViewHolder();
            viewHolder.textViewName = convertView.findViewById(R.id.tv_name);
            viewHolder.textViewPackage = convertView.findViewById(R.id.tv_package_name);
            viewHolder.textViewVersionName = convertView.findViewById(R.id.tv_version_name);
            viewHolder.textViewVersionCode = convertView.findViewById(R.id.tv_version_code);
            viewHolder.imageView = convertView.findViewById(R.id.imageView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            Log.i("TAG", " using old one");
        }
        try {
            viewHolder.textViewName.setText(list.get(position).getAppName());
            viewHolder.textViewPackage.setText(list.get(position).getPackageName());
            viewHolder.textViewVersionName.setText(list.get(position).getVersionName());
            viewHolder.textViewVersionCode.setText("" + list.get(position).getVersionCode());
            viewHolder.imageView.setImageDrawable(list.get(position).getIcon());
        } catch (Exception e) {
            e.printStackTrace();
        }
        convertView.setFocusable(false);
        return convertView;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public String getItem(int position) {
        return list.get(position).getAppName();
    }

    class ViewHolder {
        TextView textViewName;
        TextView textViewPackage;
        TextView textViewVersionName;
        TextView textViewVersionCode;
        ImageView imageView;
    }
}
