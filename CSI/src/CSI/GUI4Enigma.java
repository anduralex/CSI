package CSI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GUI4Enigma extends JFrame{
    private JButton criptareButton;

    private JPanel panel1;
    private JTextPane textPane1, textPane2;
    private JRadioButton reflectorBRadioButton, reflectorCRadioButton;
    private static String[] rotoare = {"EKMFLGDQVZNTOWYHXUSPAIBRCJ",
            "AJDKSIRUXBLHWTMCQGZNPYFVOE",
            "BDFHJLCPRTXVZNYEIWGAKMUSQO"};
    private static char [][] reflectorB = new char[][] {{'A', 'Y'},
            {'B', 'R'},
            {'C', 'U'},
            {'D', 'H'},
            {'E', 'Q'},
            {'F', 'S'},
            {'G', 'L'},
            {'I', 'P'},
            {'J', 'X'},
            {'K', 'N'},
            {'M', 'O'},
            {'T', 'Z'},
            {'V', 'W'}};

    private static char [][] reflectorC = new char[][] {{'A', 'F'},
            {'B', 'V'},
            {'C', 'P'},
            {'D', 'J'},
            {'E', 'I'},
            {'G', 'O'},
            {'H', 'Y'},
            {'K', 'R'},
            {'L', 'Z'},
            {'M', 'X'},
            {'N', 'W'},
            {'T', 'Q'},
            {'S', 'U'}};

    GUI4Enigma(){
        add(panel1);
        setTitle("Criptare adfgvx");
        setSize(800,700);
        criptareButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textPane1.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(panel1,"Introduceti text!","Warning",JOptionPane.WARNING_MESSAGE);
                }
                else
                {
                    if (reflectorBRadioButton.isSelected() && !reflectorCRadioButton.isSelected())
                        textPane2.setText(criptare(textPane1.getText(), true));
                    else if(reflectorCRadioButton.isSelected() && !reflectorBRadioButton.isSelected())
                        textPane2.setText(criptare(textPane1.getText(), false));
                    else
                        JOptionPane.showMessageDialog(panel1,"Selecteaza doar un tip de reflector!","Warning",JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }
    private static String criptare(String textul, boolean reflector){
        //upper string, eliminate special chars
        char[] ch = textul.toUpperCase().toCharArray();
        String text ="";
        for(int i=0;i<ch.length;i++)
            if(Character.isLetter(textul.charAt(i)))
                text += ch[i];
        //Criptarea
        char tmpCh;
        String result = "";
        for (int i = 0; i < text.length(); i++)
        {
            tmpCh = rotoare[2].charAt((int)text.charAt(i) - 65);
            tmpCh = rotoare[1].charAt((int)tmpCh - 65);
            tmpCh = rotoare[0].charAt((int)tmpCh - 65);
            if (reflector){
                for ( char[] chars : reflectorB ) {
                    if ( chars[0] == tmpCh )
                        tmpCh = chars[1];
                    else if ( chars[1] == tmpCh )
                        tmpCh = chars[0];
                }
            }
            else{
                for ( char[] chars : reflectorC ) {
                    if ( chars[0] == tmpCh )
                        tmpCh = chars[1];
                    else if ( chars[1] == tmpCh )
                        tmpCh = chars[0];
                }
            }
            tmpCh = (char)(rotoare[0].indexOf(tmpCh) + 65);
            tmpCh = (char)(rotoare[1].indexOf(tmpCh) + 65);
            tmpCh = (char)(rotoare[2].indexOf(tmpCh) + 65);
            result += tmpCh;
        }
    return result;
    }
}
