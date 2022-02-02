import javax.swing.*;
import java.util.ArrayList;

public class FinalMenu extends JFrame {
    FinalMenu(String sWord, long lStartPoints, int nWidth, int nHeight){
        JFrame jfFrame = new JFrame("Word Search");
        jfFrame.pack();
        jfFrame.setSize(600,300);
        jfFrame.setResizable(false);
        jfFrame.setLocationRelativeTo(null);
        jfFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        System.out.println("Word length : " + sWord.length());
        long lPointsToAdd = 0;
        for(int i = 0; i < sWord.length(); i++){
            lPointsToAdd += Math.pow(2, i);
        }

        long lPoints = lStartPoints + lPointsToAdd;

        JLabel lblPoints = new JLabel("<html><p>You gained " + lPointsToAdd + " points." +
                                            "<p>You have " + lPoints + " points now.</p>" +
                                            "<p>Do you want to continue playing?</p></html>");
        lblPoints.setHorizontalAlignment(JLabel.CENTER);
        lblPoints.setVerticalAlignment(JLabel.CENTER);
        lblPoints.setSize(600,150);
        lblPoints.setLocation(0,0);

        JButton btnNew = new JButton("New Letters");
        JButton btnSame = new JButton("Same Letters");
        JButton btnExit = new JButton("Exit");

        btnNew.setSize(150,75);
        btnNew.setLocation(25,150);
        btnSame.setSize(150,75);
        btnSame.setLocation(225,150);
        btnExit.setSize(150,75);
        btnExit.setLocation(425,150);

        btnNew.addActionListener(e -> {
            new Game(lPoints, nWidth, nHeight, false);
            jfFrame.dispose();
        });

        btnSame.addActionListener(e -> {
            new Game(lPoints, nWidth, nHeight, true);
            jfFrame.dispose();
        });

        btnExit.addActionListener(e -> {
            jfFrame.dispose();
        });

        jfFrame.add(lblPoints);
        jfFrame.add(btnNew);
        jfFrame.add(btnSame);
        jfFrame.add(btnExit);
        jfFrame.setLayout(null);
        jfFrame.setVisible(true);
    }
}
