package com.example.luongnhatminh.bluetooth;

import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TouchpadActivity extends AppCompatActivity {
    private BluetoothDevice device;
    private static final String BLUETOOTH_DEVICE = "BTDevice";
    private BTService service = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touchpad);
        Bundle info = getIntent().getExtras();
        device = info.getParcelable(BLUETOOTH_DEVICE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        service = BTService.getInstance();
        Init();
    }

    private void Init(){
        if(!service.isCreated())
        {
            service.createBTSocketWithServer(device);
            service.connectServer();
        }

        Button leftClick = (Button) findViewById(R.id.leftClick);
        leftClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                service.SendMsg("LC");
            }
        });

        Button rightClick = (Button) findViewById(R.id.rightClick);
        rightClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                service.SendMsg("RC");
            }
        });
    }
}
