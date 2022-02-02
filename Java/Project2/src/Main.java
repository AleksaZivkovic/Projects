import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;

public class Main {
    public static int nWidth, nHeight;
    public static ArrayList<String> sLast = new ArrayList<>();

    public static void getLast() throws FileNotFoundException {
        Scanner s = new Scanner(new File("./src/last.txt"));
        while (s.hasNext()){
            sLast.add(s.next());
        }
        s.close();

        System.out.println(sLast.size());
    }

    public static void main(String[] args) {
        JFrame jfFrame = new JFrame("Word Search - Main Menu");
        jfFrame.pack();
        jfFrame.setSize(700, 450);
        jfFrame.setLocationRelativeTo(null);
        jfFrame.setResizable(false);
        jfFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel lblWidth = new JLabel("Width (number of letters horizontally)");
        JLabel lblHeight = new JLabel("Height (number of letters vertically)");
        JTextField tfWidth = new JTextField();
        JTextField tfHeight = new JTextField();
        JButton btnReplay = new JButton("Continue Last Game");
        JButton btnStart = new JButton("Start");

        lblWidth.setLocation(125,50);
        lblWidth.setSize(300, 50);

        lblHeight.setLocation(125,150);
        lblHeight.setSize(300, 50);

        tfWidth.setLocation(425,50);
        tfWidth.setSize(150,50);

        tfHeight.setLocation(425,150);
        tfHeight.setSize(150,50);

        btnReplay.setLocation(50,300);
        btnReplay.setSize(250,50);
        btnStart.setLocation(400,300);
        btnStart.setSize(250,50);

        btnReplay.addActionListener(e -> {
            try {
                getLast();

                if(sLast.size() > 3){
                    long lPoints = Long.parseLong(sLast.get(0));
                    nWidth = Integer.parseInt(sLast.get(1));
                    nHeight = Integer.parseInt(sLast.get(2));
                    new Game(lPoints, nWidth, nHeight, true);
                    jfFrame.dispose();
                } else {
                    new Error("Can't load last game file.", 823);
                    btnReplay.setEnabled(false);
                }
            } catch (FileNotFoundException ex){
                new Error("Can't load last game file.", 823);
                btnReplay.setEnabled(false);
            }
        });

        btnStart.addActionListener(e -> {
            try {
                boolean bCorrectInput = true;

                nWidth = Integer.parseInt(tfWidth.getText());
                nHeight = Integer.parseInt(tfHeight.getText());

                if(nWidth < 5){
                    new Error("Width needs to be at least 5.", 261);
                    bCorrectInput = false;
                } else if(nWidth > 19){
                    new Error("Width needs to be less then 20.", 262);
                    bCorrectInput = false;
                } else if(nHeight < 5){
                    new Error("Height needs to be at least 5.", 263);
                    bCorrectInput = false;
                } else if(nHeight > 19){
                    new Error("Height needs to be less then 20.", 264);
                    bCorrectInput = false;
                }

                if(bCorrectInput){
                    new Game(0, nWidth, nHeight, false);
                    jfFrame.dispose();
                }
            } catch (NumberFormatException ex){
                new Error("Please input only numbers.", 238);
            }
        });

        jfFrame.add(lblWidth);
        jfFrame.add(lblHeight);
        jfFrame.add(tfWidth);
        jfFrame.add(tfHeight);
        jfFrame.add(btnReplay);
        jfFrame.add(btnStart);

        jfFrame.setLayout(null);
        jfFrame.setVisible(true);
    }
}
