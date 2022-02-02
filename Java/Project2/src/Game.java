import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;

public class Game extends JFrame {
    public static ArrayList<String> sLetters = new ArrayList<>();
    public static ArrayList<String> sLast = new ArrayList<>();
    public static ArrayList<String> sWords = new ArrayList<>();
    public static String sWord;

    private void fnGetDictionary() throws FileNotFoundException {
        Scanner scScanner = new Scanner(new File("./src/words.txt"));
        while (scScanner.hasNext()){
            sWords.add(scScanner.next().toUpperCase(Locale.ROOT));
        }
        scScanner.close();

        System.out.println(sWords.size());
    }

    private void fnGetLast() throws FileNotFoundException {
        Scanner scScanner = new Scanner(new File("./src/last.txt"));
        while (scScanner.hasNext()){
            sLast.add(scScanner.next());
        }
        scScanner.close();

        System.out.println(sLast.size());
    }

    private void fnClearLast() throws IOException {
        File fInputFile = new File("./src/last.txt");
        File fTempFile = new File("./src/temp.txt");
        boolean bSuccess = fTempFile.createNewFile();
        bSuccess = fTempFile.renameTo(fInputFile);
    }

    private void fnCreateLast(String sStringToWrite) throws IOException {
        FileWriter fwFileWriter = new FileWriter("./src/last.txt", true);
        BufferedWriter bwBufferedWriter = new BufferedWriter(fwFileWriter);
        PrintWriter pwPrintWriter = new PrintWriter(bwBufferedWriter);
        pwPrintWriter.println(sStringToWrite);
        pwPrintWriter.close();
    }

    private char fnRandom() {
        Random rRandom = new Random();
        int nRandom = rRandom.nextInt(26);
        return (char) ('A' + nRandom);
    }

    Game(long lPoints, int nWidth, int nHeight, boolean bReplayLast){
        try {
            fnGetDictionary();

            if(bReplayLast){
                fnGetLast();
                fnClearLast();
                fnCreateLast(String.valueOf(lPoints));
                fnCreateLast(String.valueOf(nHeight));
                fnCreateLast(String.valueOf(nWidth));
                for(int i = 3; i < sLast.size(); i++){
                    fnCreateLast(sLast.get(i));
                }
            } else {
                fnClearLast();
                fnCreateLast(String.valueOf(lPoints));
                fnCreateLast(String.valueOf(nHeight));
                fnCreateLast(String.valueOf(nWidth));
            }

        } catch (FileNotFoundException ex){
            new Error("Couldn't load Dictionary.", 404);
            return;
        } catch (IOException ex) {
            new Error("Couldn't load Last File.", 403);
            return;
        }

        JFrame jfFrame = new JFrame("Word Search");
        jfFrame.pack();
        jfFrame.setSize(nWidth * 50 + 200,nHeight * 50 + 37);
        jfFrame.setResizable(false);
        jfFrame.setLayout(null);
        jfFrame.setLocationRelativeTo(null);
        jfFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel lblPoints = new JLabel("Points : " + lPoints);
        JLabel lblWord = new JLabel("<html><p>Your word:</p></html>");
        JButton btnReset = new JButton("Restart");
        JButton btnDone = new JButton("Done");

        lblPoints.setVerticalAlignment(JLabel.CENTER);
        lblPoints.setSize(200, nHeight * 50 / 4);
        lblPoints.setLocation(nWidth * 50, 0);

        lblWord.setVerticalAlignment(JLabel.CENTER);
        lblWord.setSize(200, nHeight * 50 / 4);
        lblWord.setLocation(nWidth * 50, nHeight * 50 / 4);

        btnReset.setBackground(Color.WHITE);
        btnReset.setSize(200, nHeight * 50 / 4);
        btnReset.setLocation(nWidth * 50, nHeight * 50 / 2);

        btnDone.setBackground(Color.WHITE);
        btnDone.setSize(200, nHeight * 50 / 4);
        btnDone.setLocation(nWidth * 50, nHeight * 50 / 4 * 3);

        int counter = 3;
        JButton[][] btnLetters = new JButton[nHeight][nWidth];
        for(int y = 0; y < nHeight; y++){
            for(int x = 0; x < nWidth; x++){
                btnLetters[y][x] = new JButton();

                if(bReplayLast){
                    btnLetters[y][x].setText(sLast.get(counter));
                    counter++;
                } else{
                    btnLetters[y][x].setText(String.valueOf(fnRandom()));
                    try{
                        fnCreateLast(btnLetters[y][x].getText());
                    } catch (IOException ex){
                        new Error("Couldn't load Last File.", 403);
                        jfFrame.dispose();
                    }
                }

                btnLetters[y][x].setSize(new Dimension(50, 50));
                btnLetters[y][x].setLocation(50 * x, 50 * y);
                btnLetters[y][x].setFont(new Font("Arial", Font.PLAIN, 10));
                btnLetters[y][x].setBackground(Color.WHITE);

                btnLetters[y][x].addActionListener(e -> {
                    int nX = 0, nY = 0;

                    for(int i = 0; i < nHeight; i++){
                        for(int j = 0; j < nWidth; j++){
                            if(e.getSource() == btnLetters[i][j]){
                                nY = i;
                                nX = j;

                                sLetters.add(btnLetters[i][j].getText());
                                sWord = String.join("", sLetters);
                                if(sWords.contains(sWord)){
                                    lblWord.setText("<html><p>Your word:</p><p>" + sWord + "</p></html>");
                                } else {
                                    lblWord.setText("<html><p>Your word:</p><u>" + sWord + "</u></html>");
                                }

                                System.out.println(sWord);
                            }
                        }
                    }

                    for(int i = 0; i < nHeight; i++){
                        for(int j = 0; j < nWidth; j++){
                            if(i == nY - 1 && j == nX - 1){
                                btnLetters[i][j].setBackground(Color.WHITE);
                                btnLetters[i][j].setEnabled(true);
                            } else if(i == nY - 1 && j == nX){
                                btnLetters[i][j].setBackground(Color.WHITE);
                                btnLetters[i][j].setEnabled(true);
                            } else if(i == nY - 1 && j == nX + 1){
                                btnLetters[i][j].setBackground(Color.WHITE);
                                btnLetters[i][j].setEnabled(true);
                            } else if(i == nY && j == nX - 1){
                                btnLetters[i][j].setBackground(Color.WHITE);
                                btnLetters[i][j].setEnabled(true);
                            } else if(i == nY && j == nX + 1){
                                btnLetters[i][j].setBackground(Color.WHITE);
                                btnLetters[i][j].setEnabled(true);
                            } else if(i == nY + 1 && j == nX - 1){
                                btnLetters[i][j].setBackground(Color.WHITE);
                                btnLetters[i][j].setEnabled(true);
                            } else if(i == nY + 1 && j == nX){
                                btnLetters[i][j].setBackground(Color.WHITE);
                                btnLetters[i][j].setEnabled(true);
                            } else if(i == nY + 1 && j == nX + 1){
                                btnLetters[i][j].setBackground(Color.WHITE);
                                btnLetters[i][j].setEnabled(true);
                            } else {
                                btnLetters[i][j].setBackground(Color.DARK_GRAY);
                                btnLetters[i][j].setEnabled(false);
                            }
                        }
                    }
                });

                jfFrame.add(btnLetters[y][x]);
            }
        }

        btnReset.addActionListener(e -> {
            sLetters.clear();
            sWords.clear();
            lblWord.setText("<html><p>Your word:</p></html>");

            for(int y = 0; y < nHeight; y++) {
                for (int x = 0; x < nWidth; x++) {
                    btnLetters[y][x].setBackground(Color.WHITE);
                    btnLetters[y][x].setEnabled(true);
                }
            }
        });

        btnDone.addActionListener(e -> {
            if(sWords.contains(sWord)){
                new FinalMenu(sWord, lPoints, nWidth, nHeight);
                sLetters.clear();
                sWord = "";
                jfFrame.dispose();
            } else {
                new Error("Your word doesn't exist.", 823);
            }
        });

        jfFrame.add(lblPoints);
        jfFrame.add(lblWord);
        jfFrame.add(btnReset);
        jfFrame.add(btnDone);
        jfFrame.setVisible(true);
    }
}
