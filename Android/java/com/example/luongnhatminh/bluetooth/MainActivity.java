package com.example.luongnhatminh.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.BluetoothLeScanner;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.jar.Manifest;

import static android.bluetooth.BluetoothClass.Device.COMPUTER_DESKTOP;
import static android.bluetooth.BluetoothClass.Device.COMPUTER_LAPTOP;

public class MainActivity extends AppCompatActivity {
    private final static int REQUEST_ENABLE_BT = 1;
    private final static String LIST_DEVICES_KEY = "Devices";


    private BluetoothAdapter mBluetoothAdapter;
    private ListView listView;
    private ListView pairedListView;
    private Button scanButton;
    private ArrayList<BluetoothDevice> devices = new ArrayList<BluetoothDevice>();
    private ArrayList<BluetoothDevice> paired = new ArrayList<BluetoothDevice>();
    private BTDevicesAdapter adapter;
    private BTDevicesAdapter pairedAdapter;
    private Toast toast;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(LIST_DEVICES_KEY, devices);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null)
        {
            devices = (ArrayList<BluetoothDevice>) savedInstanceState.getSerializable(LIST_DEVICES_KEY);
        }
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        CheckBluetooth();
        Init();
        toast = new Toast(MainActivity.this);
        toast.setGravity(Gravity.CENTER | Gravity.BOTTOM, 0, 0);

        adapter = new BTDevicesAdapter(this, android.R.layout.simple_list_item_1, devices);
        listView.setAdapter(adapter);

        IntentFilter mScanning = new IntentFilter();
        mScanning.addAction(BluetoothDevice.ACTION_FOUND);
        mScanning.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        registerReceiver(mReceiver, mScanning);
        GetPaired();
    }

    private void Init()
    {
        pairedListView = (ListView) findViewById(R.id.pair);

        listView = (ListView) findViewById(R.id.listview);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (!devices.isEmpty()) {
                    mBluetoothAdapter.cancelDiscovery();
                    Connect(devices.get(position));
                    Touchpad();
                }
            }
        });

        scanButton = (Button) findViewById(R.id.scan);
        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.Scan();
            }
        });

        Button closeButton = (Button) findViewById(R.id.closeBT);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                // Discovery has found a device. Get the BluetoothDevice
                // object and its info from the Intent.
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                if(device.getBluetoothClass().getDeviceClass() == COMPUTER_LAPTOP || device.getBluetoothClass().getDeviceClass() == COMPUTER_DESKTOP)
                {
                    devices.add(device);
                    adapter.notifyDataSetChanged();
                }
            }
            else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action))
            {
                showToast("Finish");
            }
        }
    };

    private void GetPaired(){
        for(BluetoothDevice tmp : mBluetoothAdapter.getBondedDevices())
        {
            if(tmp.getBluetoothClass().getDeviceClass() == COMPUTER_LAPTOP || tmp.getBluetoothClass().getDeviceClass() == COMPUTER_DESKTOP)
            {
                paired.add(tmp);
            }
        }

        pairedAdapter = new BTDevicesAdapter(this, android.R.layout.simple_list_item_1, paired);
        pairedListView.setAdapter(pairedAdapter);
    }

    private void CheckBluetooth(){
        if(Build.VERSION.SDK_INT >= 23)
        {
            ActivityCompat.requestPermissions(this,new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.ACCESS_FINE_LOCATION},1000);
        }
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            // Device does not support Bluetooth
        }

        if(mBluetoothAdapter.isEnabled() == false)
        {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }
    }

    private void Scan(){
        devices.clear();
        adapter.notifyDataSetChanged();
        mBluetoothAdapter.cancelDiscovery();

        if(mBluetoothAdapter.startDiscovery()) {
            showToast("Scanning");
        }
    }

    private void showToast(String nof)
    {
        toast = Toast.makeText(this, nof, Toast.LENGTH_SHORT);
        toast.show();
    }

    private void Touchpad(){
        Intent touchpad = new Intent(getApplicationContext(), TouchpadActivity.class);
        startActivity(touchpad);
    }

    private void Connect(BluetoothDevice device){
        BTService service = BTService.getInstance();
        if(!service.isCreated())
        {
            service.createBTSocketWithServer(device);
            service.connectServer();
            showToast("Connecting...");
            service.waitForConnection();
        }
    }
}
