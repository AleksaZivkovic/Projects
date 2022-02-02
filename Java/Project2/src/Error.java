import javax.swing.*;

public class Error extends JFrame {
    Error(String sMessage, int nErrorCode){
        JFrame jfFrame = new JFrame("Error " + nErrorCode);
        jfFrame.pack();
        jfFrame.setSize(300,200);
        jfFrame.setResizable(false);
        jfFrame.setLocationRelativeTo(null);
        jfFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel lblError = new JLabel(sMessage, JLabel.CENTER);
        lblError.setSize(250, 100);

        JButton btnOK = new JButton("OK");
        btnOK.setSize(100,50);
        btnOK.setLocation(100,100);
        btnOK.addActionListener(e -> {
            jfFrame.dispose();
        });

        jfFrame.add(lblError);
        jfFrame.add(btnOK);
        jfFrame.setLayout(null);
        jfFrame.setVisible(true);
    }
}
