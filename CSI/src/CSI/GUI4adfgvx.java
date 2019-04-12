package CSI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.stream.Collectors;

public class GUI4adfgvx extends JFrame{
    private JTextField textField1;
    private JTextField textField2;
    private JTextPane textPane1;
    private JTextPane textPane2;
    private JButton criptareButton;
    private JPanel adfgPanel;

    GUI4adfgvx(){
        add(adfgPanel);
        setTitle("Criptare adfgvx");
        setSize(800,700);
        criptareButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textPane1.getText().equals("") || textField1.getText().equals("") || textField2.getText().equals(""))
                {
                    JOptionPane.showMessageDialog(adfgPanel,"Introduceti text si cheile!","Warning",JOptionPane.WARNING_MESSAGE);
                }
                else
                {
                    textPane2.setText(Criptare(textPane1.getText()));
                }
            }
        });
    }
    private String[][] matrice1 = new String[7][7];
    private String[][] matrice2 = new String[7][7];

    private static String InlaturaDuplicate(String p)
    {
        return Arrays.asList(p.split(""))
                .stream()
                .distinct()
                .collect(Collectors.joining());
    }

    public String Criptare(String textDeCriptat)
    {
        String cheie1 = InlaturaDuplicate(textField1.getText().toUpperCase());
        String cheie2 = InlaturaDuplicate(textField2.getText().toUpperCase());
        String[] alfabet1 = new String[] { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
                "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        StringBuilder incarcareCharsMatrice = new StringBuilder();
        incarcareCharsMatrice.append(cheie1);

        for (String s : alfabet1) {
            if (!(incarcareCharsMatrice.toString().toUpperCase().contains(s)))
                incarcareCharsMatrice.append(s);
        }
        String textcriptat = incarcareCharsMatrice.toString().toUpperCase();

        String criptaFirstRowOrCol = "ADFGVX";

        int token = 0,firstR=0,firstCol=0;
        for (int i = 0; i < 7; i++){
            for (int j = 0; j < 7; j++){
                if(i == j && i == 0)
                    matrice1[i][j] = "";
                else if(i == 0){
                    matrice1[i][j] = String.valueOf(criptaFirstRowOrCol.charAt(firstR));
                    firstR ++;
                }
                else if(j == 0){
                    matrice1[i][j] = String.valueOf(criptaFirstRowOrCol.charAt(firstCol));
                    firstCol ++;
                }
                else {
                    matrice1[i][j] = String.valueOf(textcriptat.charAt(token));
                token++;}
            }
        }

        StringBuilder criptarePreliminara = new StringBuilder();
        for (char c : textDeCriptat.toCharArray()){
            for (int i = 1; i < 7; i++){
                for (int j = 1; j < 7; j++){
                    if (String.valueOf(c).toUpperCase().equals(matrice1[i][j]))
                    {
                        criptarePreliminara.append(matrice1[0][i]);
                        criptarePreliminara.append(matrice1[j][0]);
                    }
                }
            }
        }
        int sizeCol = (int)roundUp(criptarePreliminara.length(),cheie1.length())+1;
        matrice2 = new String[cheie2.length()][sizeCol];
        StringBuilder cheieDoiPreliminarAdfgvx = new StringBuilder();
        //add chars from cheie 2
        for(char c: cheie2.toCharArray()){
            cheieDoiPreliminarAdfgvx.append(c);
        }
        //add chars from criptarePreliminara
        for(int i=0;i<criptarePreliminara.length();i++){
            cheieDoiPreliminarAdfgvx.append(criptarePreliminara.charAt(i));
        }
        //add chars ADFGVX
        for(char c: criptaFirstRowOrCol.toCharArray()) {
            cheieDoiPreliminarAdfgvx.append(c);
        }

        List<String> finalBeforeAddNumbers = new ArrayList<>();
        int token2 = 0;
        for (int j = 0 ; j < sizeCol ; j++) {
            for (int i = 0 ; i < cheie2.length() ; i++)
             {
                matrice2[i][j] = String.valueOf(cheieDoiPreliminarAdfgvx.charAt(token2));
                finalBeforeAddNumbers.add(String.valueOf(cheieDoiPreliminarAdfgvx.charAt(token2)));
                token2++;
            }
        }

        sortString(cheie2);
        String[] key2Number = new String[cheie2.length()];
        for (int i = 0; i < cheie2.length(); i++)
        {
            key2Number[i] = String.valueOf(cheie2.charAt(i));
        }
        Arrays.sort(key2Number);

        StringBuilder resultCripted = new StringBuilder();
        try {
            int r = 0;
            while ( r < cheie2.length() ) {//randuri
                for ( int i = 0 ; i < cheie2.length() ; i++ ) { //randuri
                    if ( matrice2[i][0].equals(key2Number[r]) ) {
                        for ( int j = 1 ; j <= (criptarePreliminara.length() / cheie2.length()) + 1 ; j++ ) {
                            resultCripted.append(matrice2[i][j]);
                        }
                        r++;
                    }
                }
            }
        } catch ( Exception e ) {
            System.out.println("");
        }
        return resultCripted.toString();

    }

    private static long roundUp(long num, long divisor) {
        return (num + divisor - 1) / divisor;
    }
    private static String sortString(String inputString)
    {
        // convert input string to char array
        char[] tempArray = inputString.toCharArray();

        // sort tempArray
        Arrays.sort(tempArray);

        // return new sorted string
        return new String(tempArray);
    }

}
