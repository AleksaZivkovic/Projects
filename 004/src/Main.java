import com.fazecast.jSerialComm.SerialPort;

import javax.swing.*;
import java.io.IOException;

public class Main  {
    public static void main(String[] args) {
        JFrame jFrame = new JFrame("Choose port.");
        jFrame.pack();
        jFrame.setSize(400,300);
        jFrame.setResizable(false);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.setLayout(null);

        JLabel jLabel = new JLabel("Please select port:");
        jLabel.setSize(300,50);
        jLabel.setLocation(50,25);

        JButton jButton = new JButton("Confirm selection");
        jButton.setSize(200,50);
        jButton.setLocation(100,150);

        JComboBox<String> jComboBox = new JComboBox<>();
        jComboBox.setSize(300, 50);
        jComboBox.setLocation(50,75);

        SerialPort[] spPortList = SerialPort.getCommPorts();
        for(SerialPort spPort : spPortList){
            System.out.println(spPort.getDescriptivePortName());
            jComboBox.addItem(spPort.getDescriptivePortName());
        }

        jButton.addActionListener(e -> {
            SerialPort spPort = spPortList[jComboBox.getSelectedIndex()];
            try {
                new ColorPicker(spPort);
            } catch (InterruptedException | IOException ex) {
                ex.printStackTrace();
            }
            jFrame.dispose();
        });

        jFrame.add(jLabel);
        jFrame.add(jComboBox);
        jFrame.add(jButton);
        jFrame.setVisible(true);
    }
}
