package ciphers;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Lucas Penha de Moura - 1208977
 *
 */
public class VernamCipher {

    private final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ abcdefghijklmnopqrstuvwxyz 1234567890 ., ";

    public String encrypt(String text, String key) {
        String encryptedText = "";
        int a, b;
        char c;
        int i = 0;

        List charText = string2char(text);
        List charKey = string2char(key);

        Enumeration e = Collections.enumeration(charText);
        while (e.hasMoreElements()) {

            a = alphabet.indexOf((char) e.nextElement());
            b = alphabet.indexOf((char) charKey.get(i));
            c = alphabet.charAt((a + b) % alphabet.length());
            encryptedText = encryptedText + c;
            i++;
            if (i == charKey.size()) {
                i = 0;
            }
        }

        return encryptedText;
    }

    public String decrypt(String encryptedText, String key) {
        String text = "";
        int a, b;
        char c;
        int i = 0;
        List charText = string2char(encryptedText);
        List charKey = string2char(key);

        Enumeration e = Collections.enumeration(charText);
        while (e.hasMoreElements()) {
            a = alphabet.indexOf((char) e.nextElement());
            b = alphabet.indexOf((char) charKey.get(i));
            c = alphabet.charAt((alphabet.length() + a - b) % alphabet.length());
            text = text + c;
            i++;
            if (i == charKey.size()) {
                i = 0;
            }
        }

        return text;
    }

    /**
     * 
     * @param texto
     * @return 
     */
    private List string2char(String texto) {
        List string2char = new ArrayList<>();
        char[] array = texto.toCharArray();
        for (int i = 0; i < array.length; i++) {
            string2char.add(array[i]);
        }
        return string2char;
    }

    /**
     * 
     * @param keySize
     * @return
     * @throws FileNotFoundException 
     */
    public String vernamKeyGenerator(int keySize) throws FileNotFoundException {
        //recordFile = new Formatter("generated-key.txt");
        StringBuilder key = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < keySize; i++) {
            key.append(alphabet.charAt(random.nextInt(alphabet.length())));
        }

        return key.toString();
    }
}
