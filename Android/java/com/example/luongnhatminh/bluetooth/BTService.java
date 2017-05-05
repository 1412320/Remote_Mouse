package com.example.luongnhatminh.bluetooth;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;

import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;


/**
 * Created by Luong Nhat Minh on 4/24/2017.
 */

public class BTService{
    private final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private OutputStream outputStream = null;
    private BluetoothSocket btSocket = null;
    private static BTService instance = null;

    private BTService(){

    }

    public static BTService getInstance(){
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
            return true;
        } catch (IOException e) {
            e.printStackTrace();
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
