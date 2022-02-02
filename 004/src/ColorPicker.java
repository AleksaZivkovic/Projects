import com.fazecast.jSerialComm.SerialPort;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.io.OutputStream;

public class ColorPicker {
    SerialPort spPort;
    OutputStream osStream;
    int nR = 255, nG = 255, nB = 255;
    ColorPicker(SerialPort spPort) throws InterruptedException, IOException {
        this.spPort = spPort;
        this.spPort.setComPortParameters(9600, 8,1,0);
        this.spPort.setComPortTimeouts(SerialPort.TIMEOUT_WRITE_BLOCKING,0, 0);
        osStream = spPort.getOutputStream();
        if (this.spPort.openPort()) {
            System.out.println("open");
        } else {
            return;
        }
        fnOpen();

        JFrame jFrame = new JFrame("Choose color");
        jFrame.pack();
        jFrame.setSize(500,375);
        jFrame.setResizable(false);
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.setLayout(null);

        JSlider jSliderR = new JSlider(JSlider.HORIZONTAL, 0, 255, 255);
        JSlider jSliderG = new JSlider(JSlider.HORIZONTAL, 0, 255, 255);
        JSlider jSliderB = new JSlider(JSlider.HORIZONTAL, 0, 255, 255);

        JLabel jLabelR = new JLabel("Red (" + nR + "):");
        JLabel jLabelG = new JLabel("Green (" + nG + "):");
        JLabel jLabelB = new JLabel("Blue ( " + nB + "):");

        JButton jButton = new JButton("CONFIRM");
        Graphics gGraphics = jFrame.getGraphics();

        jSliderR.setSize(250, 50);
        jSliderR.setLocation(25,25);
        jSliderG.setSize(250, 50);
        jSliderG.setLocation(25,100);
        jSliderB.setSize(250, 50);
        jSliderB.setLocation(25,175);

        jLabelR.setSize(250,15);
        jLabelR.setLocation(25,10);
        jLabelG.setSize(250,15);
        jLabelG.setLocation(25,85);
        jLabelB.setSize(250,15);
        jLabelB.setLocation(25,160);

        gGraphics.setColor(Color.WHITE);
        gGraphics.fillRect(300,75,175,175);
        gGraphics.setColor(Color.BLACK);
        gGraphics.drawRect(300,75,175,175);

        jButton.setSize(150,50);
        jButton.setLocation(175,250);

        jSliderR.addChangeListener(l -> {
            nR = jSliderR.getValue();
            jLabelR.setText("Red (" + nR + "):");

            gGraphics.setColor(new Color(nR, nG, nB));
            gGraphics.fillRect(300,75,175,175);
            gGraphics.setColor(Color.BLACK);
            gGraphics.drawRect(300,75,175,175);
        });
        jSliderG.addChangeListener(l -> {
            nG = jSliderG.getValue();
            jLabelG.setText("Green (" + nG + "):");

            gGraphics.setColor(new Color(nR, nG, nB));
            gGraphics.fillRect(300,75,175,175);
            gGraphics.setColor(Color.BLACK);
            gGraphics.drawRect(300,75,175,175);
        });
        jSliderB.addChangeListener(l -> {
            nB = jSliderB.getValue();
            jLabelB.setText("Blue (" + nB + "):");

            gGraphics.setColor(new Color(nR, nG, nB));
            gGraphics.fillRect(300,75,175,175);
            gGraphics.setColor(Color.BLACK);
            gGraphics.drawRect(300,75,175,175);
        });

        jButton.addActionListener(e -> {
            String sR = "000" + nR;
            sR = sR.substring(sR.length() - 3, sR.length());
            String sG = "000" + nG;
            sG = sG.substring(sG.length() - 3, sG.length());
            String sB = "000" + nB;
            sB = sB.substring(sB.length() - 3, sB.length());

            String sData = sR + sG + sB;
            try {
                fnWriteData(sData);
            } catch (InterruptedException | IOException ex) {
                ex.printStackTrace();
            }
        });

        jFrame.addWindowListener(new WindowListener() {

            private SerialPort spPort;

            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    fnClose();
                } catch (InterruptedException | IOException ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void windowClosed(WindowEvent e) {
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }
        });

        jFrame.add(jSliderR);
        jFrame.add(jSliderG);
        jFrame.add(jSliderB);
        jFrame.add(jLabelR);
        jFrame.add(jLabelG);
        jFrame.add(jLabelB);
        jFrame.add(jButton);
        jFrame.setVisible(true);
    }

    void fnOpen() throws InterruptedException, IOException {
        String sMessage = "o+";
        osStream.write(sMessage.getBytes());
        osStream.flush();
        Thread.sleep(2000);
    }

    void fnClose() throws InterruptedException, IOException {
        String sMessage = "c+";
        osStream.write(sMessage.getBytes());
        osStream.flush();
        Thread.sleep(2000);
        osStream.close();

        if (spPort.closePort()) {
            System.out.println("close");
        }
    }

    void fnWriteData(String sData) throws InterruptedException, IOException {
        System.out.println("\nwriting : " + sData);

        String sMessage = sData + "+";
        osStream.write(sMessage.getBytes());
        osStream.flush();
        Thread.sleep(2000);
    }
}
