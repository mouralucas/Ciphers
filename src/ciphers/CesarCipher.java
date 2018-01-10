package ciphers;

/**
 *
 * @author Lucas Penha de Moura - 1208977
 */
public class CesarCipher {

    private final char[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
        'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    int ascii;
    int alphabetPos;
    char aux;
    String test;

    public String encrypt(int key, String text) {
        StringBuilder cipheredText = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            ascii = (int) text.charAt(i);//verifica estado, virgula e ponto final.
            if (ascii == 32 || ascii == 44 || ascii == 46) {
                aux = text.charAt(i);
            } else {
                for (int j = 0; j < alphabet.length; j++) {
                    if (text.charAt(i) == alphabet[j]) {
                        alphabetPos = j;
                    }
                }

                if ((alphabetPos + key) >= alphabet.length) {
                    aux = alphabet[((alphabetPos + key) - alphabet.length)];
                } else {
                    aux = alphabet[(alphabetPos + key)];
                }

            }

            cipheredText.append(aux);
        }

        return cipheredText.toString();
    }

    public String decrypt(int key, String cipheredText) {
        StringBuilder text = new StringBuilder();

        for (int i = 0; i < cipheredText.length(); i++) {
            ascii = cipheredText.charAt(i);//aqui

            if (ascii == 32 || ascii == 44 || ascii == 46) {
                aux = cipheredText.charAt(i);
            } else {
                for (int j = 0; j < alphabet.length; j++) {
                    if (cipheredText.charAt(i) == alphabet[j]) {//aqui
                        alphabetPos = j;
                        j = alphabet.length;
                    }
                }

                if ((alphabetPos - key) < 0) {
                    aux = alphabet[(alphabet.length - (key - alphabetPos))];
                } else {
                    aux = alphabet[(alphabetPos - key)];
                }

            }
            text.append(aux);
        }

        return text.toString();
    }

    /**
     *
     * @param lf
     * @return freqKey
     */
    public int CesarKeyFreq(LetterFrequency lf) {
        int freqKey = 0;
        for (int i = 0; i < alphabet.length; i++) {
            if (lf.getLetter() == alphabet[i]) {
                freqKey = i;
            }
        }

        return freqKey;
    }

}
