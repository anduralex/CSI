package CSI;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.*;

public class GUI4DES extends JFrame{
    private JTextPane Mesaj;
    private JTextPane Criptat;
    private JTextPane Decriptat;
    private JTextField textField1;
    private JButton decriptareButton;
    private JButton criptareButton;
    private JPanel desPanel;

    GUI4DES(){
        add(desPanel);
        setTitle("Criptare DES");
        setSize(800,700);

        criptareButton.addActionListener(e -> {
            try{
                //generateSymmetricKey();
                String inputMessage = Mesaj.getText();
                byte[] ibyte = inputMessage.getBytes();

                byte[] ebyte= encrypt(textField1.getText(), ibyte);
                String encryptedData = new String(ebyte);
                System.out.println("Encrypted message " + encryptedData + " Key: " + textField1.getText());
                Criptat.setText(encryptedData);
            } catch ( Exception a) {
                System.out.println(a);
            }

        });
        decriptareButton.addActionListener(e -> {
            try {

                String inputMessage = Mesaj.getText();
                byte[] ibyte = inputMessage.getBytes();
                byte[] ebyte = encrypt(textField1.getText(), ibyte);
                byte[] dbyte = decrypt(textField1.getText(), ebyte);
                String decryptedMessage = new String(dbyte);
                Decriptat.setText(decryptedMessage);
                System.out.println("Decrypted message " + decryptedMessage);
            }catch(Exception a) {
                System.out.println(a);
            }
        });
    }

    private static byte[] decrypt(String raw, byte[] encrypted) throws Exception {
        SecretKeySpec skeySpec = new SecretKeySpec(raw.getBytes(), "DES");
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);
        return cipher.doFinal(encrypted);
    }

    private static byte[] encrypt(String raw, byte[] clear) throws Exception {
        SecretKeySpec skeySpec = new SecretKeySpec(raw.getBytes(), "DES");
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        return cipher.doFinal(clear);
    }
}
