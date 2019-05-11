package CSI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyGUIForm extends JFrame{
    private JButton HOMOPHONICButton;
    private JButton PLAYFAREButton;
    private JButton ADFGVXButton;
    public JPanel rootPanel;
    private JButton EnigmaButton;
    private JButton DESButton;

    public MyGUIForm(){

        DESButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUI4DES des = new GUI4DES();
                des.setVisible(true);

            }
        });

        PLAYFAREButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUI4Homophonic playfare = new GUI4Homophonic();
                playfare.setVisible(true);

            }
        });

        HOMOPHONICButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUI4pFare homof = new GUI4pFare();
                homof.setVisible(true);
            }
        });
        ADFGVXButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUI4adfgvx adfg = new GUI4adfgvx();
                adfg.setVisible(true);
            }
        });

        EnigmaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUI4Enigma enigma = new GUI4Enigma();
                enigma.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("MyGUIForm");
        frame.setContentPane(new MyGUIForm().rootPanel);
        frame.setVisible(true);
        frame.setTitle("Criptare");
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800,700);
    }
}
