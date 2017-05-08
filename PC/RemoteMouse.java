package bluetoothpc;

import java.awt.AWTException;
import java.awt.Color;
import java.io.IOException;

public class RemoteMouse extends javax.swing.JFrame {
    private static BTServer bt = BTServer.getInstance();
    public RemoteMouse() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        text1 = new javax.swing.JLabel();
        text2 = new javax.swing.JLabel();
        status = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        deviceName = new javax.swing.JLabel();
        deviceAddress = new javax.swing.JLabel();
        disconnect = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jCheckBox4 = new javax.swing.JCheckBox();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Remote Mouse");

        jPanel2.setBackground(new java.awt.Color(255, 236, 134));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Leelawadee", 1, 18)); // NOI18N
        jLabel1.setText("Android TouchPad");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, -1, -1));

        text1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        text1.setText("Device name: ");
        jPanel2.add(text1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 62, -1, -1));

        text2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        text2.setText("Device address:");
        jPanel2.add(text2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, -1, -1));

        status.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        status.setForeground(new java.awt.Color(255, 0, 0));
        status.setText("No connection");
        jPanel2.add(status, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 140, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Status: ");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, -1, -1));

        deviceName.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        deviceName.setForeground(new java.awt.Color(51, 102, 255));
        deviceName.setText("Null");
        jPanel2.add(deviceName, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 60, -1, -1));

        deviceAddress.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        deviceAddress.setForeground(new java.awt.Color(51, 102, 255));
        deviceAddress.setText("Null");
        jPanel2.add(deviceAddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 100, -1, -1));

        disconnect.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        disconnect.setText("Disconnect");
        disconnect.setEnabled(false);
        disconnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                disconnectActionPerformed(evt);
            }
        });
        jPanel2.add(disconnect, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 180, -1, -1));

        jTabbedPane1.addTab("Status", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 236, 134));

        jCheckBox1.setBackground(new java.awt.Color(255, 236, 144));
        jCheckBox1.setText("Click chuột trái");

        jCheckBox2.setBackground(new java.awt.Color(255, 236, 144));
        jCheckBox2.setText("Click chuột phải");

        jCheckBox3.setBackground(new java.awt.Color(255, 236, 144));
        jCheckBox3.setText("Scroll");

        jCheckBox4.setBackground(new java.awt.Color(255, 236, 144));
        jCheckBox4.setText("Zoom");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckBox1)
                    .addComponent(jCheckBox2)
                    .addComponent(jCheckBox3)
                    .addComponent(jCheckBox4))
                .addContainerGap(317, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jCheckBox1)
                .addGap(18, 18, 18)
                .addComponent(jCheckBox2)
                .addGap(18, 18, 18)
                .addComponent(jCheckBox3)
                .addGap(18, 18, 18)
                .addComponent(jCheckBox4)
                .addContainerGap(68, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Available option", jPanel3);

        jPanel4.setBackground(new java.awt.Color(255, 234, 134));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("The Gears ®");

        jLabel6.setText("1. Lê Quốc Minh - 1412320");

        jLabel7.setText("2. Lương Nhật Minh - 1412321");

        jLabel8.setText("3. Nguyễn Thị Bảo Ngọc - 1412350");

        jLabel9.setText("Android Touchpad v1.0");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addContainerGap(245, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 93, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addContainerGap())
        );

        jTabbedPane1.addTab("About", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void disconnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_disconnectActionPerformed
        try {
            bt.CloseServer();
        } catch (IOException ex) {
        }
    }//GEN-LAST:event_disconnectActionPerformed

    public static void main(String args[]) throws IOException {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RemoteMouse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> {
            new RemoteMouse().setVisible(true);
        });
	bt.startServer();
        setText(bt.getConnectedDevice().getFriendlyName(true), bt.getConnectedDevice().getBluetoothAddress());
        try 
        {
            while(bt.WaitForMsg() == 1)
                {
                   
                }
        } catch (AWTException ex) {
        }
        bt.CloseServer();
    }
    
    public static void setText(String name, String address)
    {
        deviceName.setText(name);
        deviceAddress.setText(address);
        status.setText("Connected");
        status.setForeground(Color.green);
        disconnect.setEnabled(true);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private static javax.swing.JLabel deviceAddress;
    private static javax.swing.JLabel deviceName;
    private static javax.swing.JButton disconnect;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private static javax.swing.JLabel status;
    private javax.swing.JLabel text1;
    private javax.swing.JLabel text2;
    // End of variables declaration//GEN-END:variables
}
