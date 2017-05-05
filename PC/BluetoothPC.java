/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bluetoothpc;

/**
 *
 * @author Luong Nhat Minh
 */
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

