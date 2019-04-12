package CSI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.stream.Collectors;


public class GUI4Homophonic extends JFrame{
    private JTextField textField1;
    private JTextPane textPane1;
    private JTextPane textPane2;
    private JButton criptareButton;
    private JButton decriptareButton;
    private JPanel pfPanel;

    private String[][] math2 = new String[5][25];
    private static Random random;
    private static Object syncObj = new Object();


    GUI4Homophonic(){
        add(pfPanel);
        setTitle("Criptare Homophonic");
        setSize(800,700);
        criptareButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textPane1.getText().equals("") || textField1.getText().equals(""))
                {
                    JOptionPane.showMessageDialog(pfPanel,"Introduceti text si cheie!","Warning",JOptionPane.WARNING_MESSAGE);
                }
                else
                {
                    textPane2.setText(Homophonic_Encrypt(textPane1.getText()));
                }
            }
        });
        decriptareButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textPane2.getText().equals("") || textField1.getText().equals(""))
                {
                    JOptionPane.showMessageDialog(pfPanel, "Introduceti textul si cheia!");
                }
                else
                {
                    textPane1.setText(Homophonic_Decrypt(textPane2.getText()));
                }
            }
        });
    }
    private static int Generare_numar(int max)
    {
        if (random == null)
            random = new Random();
        return random.nextInt(max);
    }


    private static String stergere_CaracterSpecial(String str)
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

    private static String stergere_CaracterSpecial2(String str)
    {   StringBuilder sb = new StringBuilder();
        for (char c : str.toCharArray())
        {
            if (c >= '0' && c <= '9')
            {

                sb.append(c);
            }
        }
        return sb.toString();
    }

    private void generaremath2(String cheie)
    {
        char[] alfabet = {'A','B','C','D','E','F','G','H','I','K','L','M',
                'N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
        String[] a1 = new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25" };
        String[] a2 = new String[] { "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50" };
        String[] a3 = new String[] { "51", "52", "53", "54", "55", "56", "57", "58", "59", "60", "61", "62", "63", "64", "65", "66", "67", "68", "69", "70", "71", "72", "73", "74", "75" };
        String[] a4 = new String[] { "76", "77", "78", "79", "80", "81", "82", "83", "84", "85", "86", "87", "88", "89", "90", "91", "92", "93", "94", "95", "96", "97", "98", "99", "00" };
        List<String[]> x = new ArrayList<>();
        x.add(a1);
        x.add(a2);
        x.add(a3);
        x.add(a4);

        cheie = cheie.toUpperCase();


        for (int i = 0; i < 25; i++)
        {
            math2[0][i] = String.valueOf(alfabet[i]);
        }

        char[] cheieTmp = cheie.toCharArray();

        for (int i = 0; i < 4; i++)
        {
            for (int j = 0; j < 25; j++)
            {
                if (String.valueOf(cheieTmp[i]).equals(String.valueOf(alfabet[j])))
                {
                    Collections.rotate(x,j);
                    System.arraycopy(x.get(i), 0, math2[i + 1], 0, 25);
                }
            }
        }
    }

    private String Homophonic_Encrypt(String textc)
    {
        StringBuilder textd = new StringBuilder();
        textc = stergere_CaracterSpecial(textc);
        textc = textc.toUpperCase();
        generaremath2(textField1.getText());
        for(char car : textc.toCharArray())
        {
            for (int j = 0; j < 25; j++)
            {
                String[] v = new String[4];
                if (String.valueOf(car).equals(math2[0][j]))
                {
                    for (int i = 1; i < 5; i++)
                    {
                        v[i - 1] = math2[i][j];
                    }
                    textd.append(v[Generare_numar(v.length)]);
                }
            }
        }
        return textd.toString();
    }
    private String Homophonic_Decrypt(String textd)
    {
        StringBuilder textc = new StringBuilder();
        textd = stergere_CaracterSpecial2(textd);
        generaremath2(textField1.getText());
        for (int i = 0; i < textd.length() - 1; i = i + 2)
        {
            String numar = String.valueOf(textd.charAt(i)).concat(String.valueOf(textd.charAt(i + 1)));
            for (int j = 1; j < 5; j++)
            {
                for (int k = 0; k < 25; k++)
                {
                    if (math2[j][k].equals(numar))
                    {
                        textc.append(math2[0][k]);
                    }
                }
            }
        }
//        return Arrays.asList(textc.toString().split(""))
//                .stream()
//                .distinct()
//                .collect(Collectors.joining());
        String chargeResult = "";

        for(int i = 0; i<textc.length();i++)
             textc.deleteCharAt(i);

        return textc.toString();
    }
}
