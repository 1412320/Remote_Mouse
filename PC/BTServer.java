package bluetoothpc;

import java.awt.AWTException;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.bluetooth.*;
import javax.microedition.io.*;

public class BTServer {
    private final UUID defaultUUID = new UUID(0x1101);
    private StreamConnectionNotifier streamCon = null;
    private StreamConnection connection = null;
    private InputStream input = null;
    private OutputStream output = null;
    private final MyRobot bot = new MyRobot();
    private static BTServer instance;
    private RemoteDevice dev = null;
    private LocalDevice ld = null;
    private DiscoveryAgent da = null;
    
    public static BTServer getInstance(){
        if(instance == null)
        {
           instance = new BTServer();
        }
        return instance;
    }
    
    public RemoteDevice getConnectedDevice(){
        return dev;
    }
    
    public void startServer() throws IOException{
        String connectionString = "btspp://localhost:" + this.defaultUUID + ";name=PC BT Server";
        streamCon = (StreamConnectionNotifier)Connector.open(connectionString);
        connection = streamCon.acceptAndOpen();
        dev = RemoteDevice.getRemoteDevice(connection);
        System.out.println("Device name: " + dev.getFriendlyName(true));
        System.out.println("Device address: " + dev.getBluetoothAddress());
        input = connection.openInputStream();
        output = connection.openOutputStream();
        ld = LocalDevice.getLocalDevice();
        ld.setDiscoverable(DiscoveryAgent.GIAC);
        da = ld.getDiscoveryAgent();
        SendRes();
    }
    
    private void SendRes(){
        try {
            String msg = "Connected@";
            byte[] buff = msg.getBytes();
            output.write(buff);
            output.close();
        } catch (IOException ex) {
            Logger.getLogger(BTServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int WaitForMsg() throws AWTException {
        try{
            String msg = null;
            if(input.available() > 0)
            {
                byte[] buff = new byte[1024];
                input.read(buff);
                msg = new String(buff, StandardCharsets.US_ASCII);
                bot.commandHandler(msg);
            }
            
        }catch(IOException e)
        {
        }
        return 1;
    }

    public void CloseServer() throws IOException{
        streamCon.close();
        connection.close();
        input.close();
        output.close();
    }
}
