/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bluetoothpc;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.awt.Robot;
import javax.bluetooth.*;
import javax.microedition.io.*;
import java.awt.Point;
import java.awt.event.InputEvent;

public class BTServer {
	private final UUID defaultUUID = new UUID(0x1101);
	private StreamConnectionNotifier streamCon = null;
	private StreamConnection connection = null;
	InputStream input = null;
	InputStreamReader inputStream = null;
	
	public void startServer() throws IOException{
		String connectionString = "btspp://localhost:" + this.defaultUUID + ";name=PC BT Server";
		streamCon = (StreamConnectionNotifier)Connector.open(connectionString);
		
		connection = streamCon.acceptAndOpen();
		
		RemoteDevice dev = RemoteDevice.getRemoteDevice(connection);
		System.out.println("Device name: " + dev.getFriendlyName(true));
		System.out.println("Device address: " + dev.getBluetoothAddress());
		input = connection.openInputStream();
	}
	
	public int WaitForMsg() throws AWTException {
		
		try{
			String msg = null;
			if(input.available() > 0)
			{
				byte[] buff = new byte[1024];
				input.read(buff);
				msg = new String(buff, StandardCharsets.US_ASCII);
			}
			
		if(msg == null)
		{
			return 1;	
		}
		else
			{
			msg = msg.substring(0, msg.indexOf("@"));
			if(Objects.equals(msg, "Disconnect"))
			{
                            System.out.println(msg);
                            return 0;
			}
                        else if (msg.equals("LC"))
                        {
                            System.out.println(msg);
                            Robot aRobot = new Robot();
                            aRobot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                            aRobot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                        }
			else if(!msg.equals(""))
			{
				System.out.println(msg);
                                String temp = msg.substring(1, msg.length() - 2);
                                String [] part = temp.split(",");
                                Float xvalue = Float.parseFloat(part[0]);
                                Float yvalue = Float.parseFloat(part[1]);
                                int xValue = Math.round(xvalue);
                                int yValue = Math.round(yvalue);
                                Point point = MouseInfo.getPointerInfo().getLocation(); 
                                float nowx = point.x;
                                float nowy = point.y;
                                Robot aRobot = new Robot();
                                aRobot.mouseMove((int)(nowx + xValue),(int)(nowy + yValue));
			}
			}
		}catch(IOException e)
		{
			
		}
		
		return 1;
	}
	
	public void CloseServer() throws IOException{
		streamCon.close();
	}
	
}
