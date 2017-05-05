package com.example.luongnhatminh.bluetooth;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Luong Nhat Minh on 4/27/2017.
 */

public class BTDevicesAdapter extends ArrayAdapter<BluetoothDevice>{

    public BTDevicesAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<BluetoothDevice> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        BluetoothDevice item = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.bt_devices, parent, false);
        }
        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.name);

        // Populate the data into the template view using the data object
        tvName.setText(item.getName());

        // Return the completed view to render on screen
        return convertView;
    }
}
