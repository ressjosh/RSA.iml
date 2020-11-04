import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;

public class RSACipher {
    private JFrame fenster;
    private JTextField textField_pubKey_N;
    private JTextField textField_pubKey_e;
    private JButton button_encrypt_validateSignature;
    private JTextField textField_privKey_N;
    private JTextField textField_privKey_d;
    private JButton button_decrypt_sign;
    private JPanel mainPanel;
    private JTextField textField_Input;
    private JTextField textField_Result;
    private JButton button_createKey;
    private JTextField textField_p;
    private JTextField textField_q;
    private JTextField textField_e;
    private BigInteger verschluesselt;

    // Hier die relevanten Teile des RSA-Verfahrens ergänzen
    public void decrypt_sign ()
    {
        if(isPrivKeyValid() && checkInput(Integer.valueOf(textField_privKey_N.getText())))
        {
            // Ergänzen
            BigInteger bI = new BigInteger(textField_Input.getText());
            BigInteger hg =bI.pow(Integer.parseInt(textField_e.getText()));
            verschluesselt = hg.mod(new BigInteger(textField_pubKey_N.getText()));
            textField_Result.setText(verschluesselt.toString());
        }
        else
        {
            System.err.println("Input or private Key not a positive integer or input is too big.");
            textField_Result.setText("Err");
        }
    }

    public void encrypt_validateSignature ()
    {
        if(isPubKeyValid() && checkInput(Integer.valueOf(textField_pubKey_N.getText())))
        {
            BigInteger bI = new BigInteger(textField_Input.getText());
            BigInteger hg =bI.pow(Integer.parseInt(textField_privKey_d.getText()));
            textField_Result.setText(hg.mod(new BigInteger(textField_privKey_N.getText())).toString());
            // Ergänzen
            textField_Result.setText("nA");
        }
        else
        {
            System.err.println("Input or public Key not a positive integer or input is too big.");
            textField_Result.setText("Err");
        }
    }

    // Zusatzaufgabe: Schlüsselaustausch ergänzen. Ihr müsst nicht prüfen, ob p und q Primzahlen sind
    private void createKeys() {
        if(isPositiveInteger(textField_p.getText()) && isPositiveInteger(textField_q.getText()) && isPositiveInteger(textField_e.getText()))
        {

        }
        else
        {
            System.err.println("p, q or e not a positive integer");
        }
    }






    public static void main(String[] args) {
        new RSACipher();
    }

    public RSACipher ()
    {
        fenster = new JFrame("RSA-Ver- und Entschlüsselung");
        fenster.setContentPane(mainPanel);
        fenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenster.pack();
        fenster.setVisible(true);
        fenster.setBounds(300,100,800,200);

        button_encrypt_validateSignature.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                encrypt_validateSignature();
            }
        });


        button_decrypt_sign.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                decrypt_sign();
            }
        });

        button_createKey.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { createKeys(); }
        });
    }

    private boolean isPositiveInteger(String s)
    {
        if(s==null || s.isEmpty()) {return false;}
        return s.chars().allMatch(Character::isDigit); //Works only for positive Integers (no Characters like ,. and - allowed).
    }

    // Diese Abfragen testen ob die Eingaben grundsätzlich sinnvoll sind, decken aber leider nicht alles ab (zum Beispiel ob ein Schlüssel wirklich gültig ist)
    private boolean checkInput(int maxNumber)
    {
        String input = textField_Input.getText();
        return isPositiveInteger(input) && Integer.valueOf(input)<maxNumber;
    }

    private boolean isPubKeyValid()
    {
        return isPositiveInteger(textField_pubKey_N.getText()) && isPositiveInteger(textField_pubKey_e.getText());
    }

    private boolean isPrivKeyValid()
    {
        return isPositiveInteger(textField_privKey_N.getText()) && isPositiveInteger(textField_privKey_d.getText());
    }
}
