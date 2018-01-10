package ciphers;

import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.Random;

/**
 *
 * @author Lucas Penha de Moura - 1208977
 * 
 */
public class NewVernam {

    Formatter recordFile;
    private final char[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
        'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    public String vernamEncrypt(String text, String key) {
        char cText[] = text.toCharArray();
        char cKey[] = key.toCharArray();
        char[] encoded = new char[text.length()];
        StringBuilder vernamEncrypt = new StringBuilder();

        for (int i = 0; i < cText.length; i++) {
            encoded[i] = (char) (cText[i] ^ cKey[i]);
        }

        for (int i = 0; i < encoded.length; i++) {
            vernamEncrypt.append(encoded[i]);
        }
        
        System.out.println("Vernam: "  + vernamEncrypt.toString().replace("\n", ""));
        return vernamEncrypt.toString();
    }

    public String vernamDecrypt(String encryptedText, String key) {
        char cEncryptedText[] = encryptedText.toCharArray();
        char cKey[] = key.toCharArray();
        char[] decoded = new char[encryptedText.length()];
        StringBuilder vernamDecrypt = new StringBuilder();

        for (int i = 0; i < cEncryptedText.length; i++) {
            decoded[i] = (char) (cEncryptedText[i] ^ cKey[i]);
            //System.out.print(temp);
        }
        for (int i = 0; i < decoded.length; i++) {
            vernamDecrypt.append(decoded[i]);
        }
        
        return vernamDecrypt.toString();

    }

    public String vernamKeyGenerator(int keySize) throws FileNotFoundException {
        //recordFile = new Formatter("generated-key.txt");
        StringBuilder key = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < keySize; i++) {
            key.append(alphabet[random.nextInt(alphabet.length)]);
        }

        return key.toString();
    }
}
