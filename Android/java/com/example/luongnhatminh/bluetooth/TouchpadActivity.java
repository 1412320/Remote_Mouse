package com.example.luongnhatminh.bluetooth;

import android.bluetooth.BluetoothDevice;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TouchpadActivity extends AppCompatActivity {
    private BTService service = null;

   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touchpad);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        service = BTService.getInstance();
        Init();
    }

    private void Init(){
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
