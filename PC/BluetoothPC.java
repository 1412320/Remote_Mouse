package bluetoothpc;

import java.awt.AWTException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BluetoothPC{
	public static void main(String[] args) throws IOException {
		BTServer bt = new BTServer();
		bt.startServer();
            try { 
                while(bt.WaitForMsg() == 1)
                {
                    
                }
            } catch (AWTException ex) {
                Logger.getLogger(BluetoothPC.class.getName()).log(Level.SEVERE, null, ex);
            }
		bt.CloseServer();
	}
}

