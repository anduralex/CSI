package CSI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.stream.IntStream;

public class GUI4pFare extends JFrame {
    private JTextField textField1;
    private JTextPane textPane1;
    private JTextPane textPane2;
    private JButton criptareButton;
    private JButton decriptareButton;
    private JPanel hPanel;

    private static String[][] matrice = new String[5][5];
    private static char[] alfabet = {'A','B','C','D','E','F','G','H','I','J','K','L','M',
            'N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};

    private String[][] matrice_f = new String[27][27];
    private String[][] matrice_i = new String[27][27];
    private String key = "";


    GUI4pFare() {
        add(hPanel);
        setTitle("Criptare playfare");
        setSize(800,700);
        criptareButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textPane1.getText().equals("") || textField1.getText().equals(""))
                {
                    JOptionPane.showMessageDialog(hPanel,"Introduceti text si cheie!","Warning",JOptionPane.WARNING_MESSAGE);
                }
                else
                {
                    textPane2.setText(criptare(textPane1.getText()));
                }

            }
        });
        decriptareButton.addActionListener(e -> {
            if (textPane2.getText().equals("") || textField1.getText().equals(""))
            {
                JOptionPane.showMessageDialog(hPanel, "Introduceti textul si cheia!");
            }
            else
            {
                textPane1.setText(decriptare(textPane2.getText()));
            }
        });
    }

    public void initMat()
    {

        key = textField1.getText();
        key = key.toUpperCase();
        int[] v = new int[26];
        for (int i = 0; i < 26; i++)
        {
            for (int j = 0; j < 26; j++)
                matrice_i[i][j] = String.valueOf(alfabet[j]);
            IntStream.range(0, 25).forEach(k -> alfabet[k] = alfabet[k + 1]);
            alfabet[25] = alfabet[0];
        }

        for (int i = 0; i < key.length(); i++)
            for (int j = 0; j < 26; j++)
                if (alfabet[j] == key.charAt(i))
                    v[i] = j;

        IntStream.range(0, key.length()).forEach(i -> System.arraycopy(matrice_i[v[i]], 0, matrice_f[i], 0, 26));
    }
    private static String delSpecialChars(String str)
    {
        StringBuilder sb = new StringBuilder();
        for (char c : str.toCharArray())
        {
            if ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z'))
            {

                sb.append(c);
            }
        }

        return sb.toString();
    }

    private String criptare(String textclar)
    {
        String textCriptat = delSpecialChars(textclar.toUpperCase());
        textCriptat = textCriptat.replace("J", "I");

        for (int i = 0; i < textCriptat.length() - 1; i += 2)
            if (textCriptat.charAt(i) == textCriptat.charAt(i + 1))
                textCriptat = new StringBuffer(textCriptat).insert(i+1, "X").toString();


        if (textCriptat.length() % 2 != 0)
        {
            textCriptat = new StringBuffer(textCriptat).insert(textCriptat.length(), "A").toString();
        }

        matrix(textField1.getText());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < textCriptat.length() - 1; i = i + 2)
        {

            char a = textCriptat.charAt(i);
            char b = textCriptat.charAt(i + 1);
            int a_i = 0;
            int a_j = 0;
            int b_i = 0;
            int b_j = 0;
            for (int n = 0; n < 5; n++){
                for (int m = 0; m < 5; m++){
                    if (matrice[n][m].equals(String.valueOf(a)))
                    {
                        a_i = n;
                        a_j = m;
                    }
                    if (matrice[n][m].equals(String.valueOf(b)))
                    {
                        b_i = n;
                        b_j = m;
                    }
                }
            }
            if (a_i == b_i)
            {
                if (a_j == 4)
                {
                    sb.append(matrice[a_i][0]);
                    sb.append(matrice[b_i][b_j + 1]);
                }
                else if (b_j == 4)
                {
                    sb.append(matrice[a_i][a_j + 1]);
                    sb.append(matrice[b_i][0]);
                }
                else
                {
                    sb.append(matrice[a_i][a_j + 1]);
                    sb.append(matrice[b_i][b_j + 1]);
                }
            }
            else if (a_j == b_j)
            {
                if (a_i == 4)
                {
                    sb.append(matrice[0][ a_j]);
                    sb.append(matrice[b_i + 1][ b_j]);
                }
                else if (b_i == 4)
                {
                    sb.append(matrice[a_i + 1][a_j]);
                    sb.append(matrice[0][ b_j]);
                }
                else
                {
                    sb.append(matrice[a_i + 1][ a_j]);
                    sb.append(matrice[b_i + 1][ b_j]);
                }
            }
            else
            {
                sb.append(matrice[a_i][ b_j]);
                sb.append(matrice[b_i][ a_j]);
            }
        }
        return sb.toString();
    }

    public String decriptare(String textCriptat)
    {

        if (textCriptat.length() % 2 != 0)
        {
            textCriptat = new StringBuffer(textCriptat).insert(textCriptat.length(), "B").toString();
        }

        matrix(textField1.getText());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < (textCriptat.length() - 1); i = i + 2)
        {

            char a = textCriptat.charAt(i);
            char b = textCriptat.charAt(i + 1);
            int a_i = 0;
            int a_j = 0;
            int b_i = 0;
            int b_j = 0;
            for (int n = 0; n < 5; n++)
            {
                for (int m = 0; m < 5; m++)
                {
                    if (matrice[n][m].equals(String.valueOf(a)))
                    {
                        a_i = n;
                        a_j = m;
                    }
                    if (matrice[n][m].equals(String.valueOf(b)))
                    {
                        b_i = n;
                        b_j = m;
                    }
                }
            }
            if (a_i == b_i)
            {
                if (a_j == 0)
                {
                    sb.append(matrice[a_i][ 4]);
                    sb.append(matrice[b_i][ b_j - 1]);
                }
                else if (b_j == 0)
                {
                    sb.append(matrice[a_i][a_j - 1]);
                    sb.append(matrice[b_i][ 4]);
                }
                else
                {
                    sb.append(matrice[a_i][a_j - 1]);
                    sb.append(matrice[b_i][ b_j - 1]);
                }
            }
            else if (a_j == b_j)
            {
                if (a_i == 0)
                {
                    sb.append(matrice[4][ a_j]);
                    sb.append(matrice[b_i - 1][ b_j]);
                }
                else if (b_i == 0)
                {
                    sb.append(matrice[a_i - 1][ a_j]);
                    sb.append(matrice[4][ b_j]);
                }
                else
                {
                    sb.append(matrice[a_i - 1][ a_j]);
                    sb.append(matrice[b_i - 1][ b_j]);
                }
            }
            else
            {

                sb.append(matrice[a_i][ b_j]);
                sb.append(matrice[b_i][ a_j]);

            }

        }
        return sb.toString();
    }
    private static void matrix(String cheie)
    {
        cheie = cheie.toUpperCase();
        cheie = cheie.replace("J", "I");
        cheie = delDuplicates(cheie);
        cheie = delSpecialChars(cheie);
        StringBuilder sb = new StringBuilder();
        sb.append(cheie);
        for (char c : alfabet) {
            if (!sb.toString().contains(String.valueOf(c))) {
                sb.append(c);
            }
        }
        int k = 0;
        for (int i = 0; i < 5; i++){
            for (int j = 0; j < 5; j++){
                matrice[i][j] = String.valueOf(sb.toString().charAt(k));
                k++;
            }
        }
    }

    private static String delDuplicates(String p)
    {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < p.length(); i++) {
            if(!result.toString().contains(String.valueOf(p.charAt(i)))) {
                result.append(p.charAt(i));
            }
        }
        return result.toString();
    }

}
