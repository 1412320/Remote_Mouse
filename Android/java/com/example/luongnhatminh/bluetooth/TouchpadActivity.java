package com.example.luongnhatminh.bluetooth;

import android.bluetooth.BluetoothDevice;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
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
        final Button leftClick = (Button) findViewById(R.id.leftClick);
//        leftClick.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                service.SendMsg("LC");
//            }
//        });
        leftClick.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN)
                {
                    service.SendMsg("HLC");
                }
                else if (event.getAction() == MotionEvent.ACTION_UP)
                {
                    service.SendMsg("RLC");
                }
                return false;
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
