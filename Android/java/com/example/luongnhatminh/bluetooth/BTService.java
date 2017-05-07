package com.example.luongnhatminh.bluetooth;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.UUID;


/**
 * Created by Luong Nhat Minh on 4/24/2017.
 */

public class BTService{
    private final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private OutputStream outputStream = null;
    private InputStream inputStream = null;
    private BluetoothSocket btSocket = null;
    private static BTService instance;

    private BTService(){

    }

    public synchronized static BTService getInstance(){
        if(instance == null) {
            instance = new BTService();
        }
        return instance;
    }

    public void SendMsg(String msg)
    {
        try {
            if(!btSocket.isConnected())
                connectServer();

            msg = msg + "@";
            byte[] buff = msg.getBytes();
            outputStream.write(buff);
        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public boolean isCreated(){
        return (btSocket != null);
    }

    public boolean isConnected(){
        return btSocket.isConnected();
    }

    public boolean createBTSocketWithServer(BluetoothDevice server){
        try {
            btSocket = server.createRfcommSocketToServiceRecord(MY_UUID);
            return true;
        }catch (IOException e) {
            return false;
        }
    }

    public boolean connectServer()
    {
        try {
            btSocket.connect();
            outputStream = btSocket.getOutputStream();
            inputStream = btSocket.getInputStream();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean waitForConnection(){
        try{
            String msg = null;
            while(true)
            {
                if(inputStream.available() > 0)
                {
                    byte[] buff = new byte[1024];
                    inputStream.read(buff);
                    msg = new String(buff, StandardCharsets.US_ASCII);
                    msg = msg.split("@")[0];
                    if(msg.equals("Connected"))
                    {
                        return true;
                    }
                }
            }

        }catch(IOException e)
        {
            return false;
        }
    }

    public void close(){
        try {
            outputStream.close();
            btSocket.close();
            btSocket = null;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
