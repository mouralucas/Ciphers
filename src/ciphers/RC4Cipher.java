package ciphers;

import java.security.InvalidKeyException;

/**
 *
 * @author Lucas Penha de Moura - 1208977
 */
public class RC4Cipher {

    private char[] key;
    private int[] sbox;
    private static final int SBOX_LENGTH = 256;
    private static final int TAM_MIN_CHAVE = 5;

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        try {

            RC4Cipher rc4 = new RC4Cipher("testkey");
            char[] textoCriptografado = rc4.criptografa("Teste de Mensagem de Texto Puro".toCharArray());
            System.out.println("Texto Criptografado:\n" + new String(textoCriptografado));
            System.out.println("Texto Decriptografado:\n"
                    + new String(rc4.decriptografa(textoCriptografado)));

        } catch (InvalidKeyException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     *
     * @param key
     * @throws InvalidKeyException
     */
    public RC4Cipher(String key) throws InvalidKeyException {
        setKey(key);
    }

    /**
     *
     */
    public RC4Cipher() {

    }

    /**
     *
     * @param msg
     * @return
     */
    public char[] decriptografa(final char[] msg) {
        return criptografa(msg);
    }

    /**
     *
     * @param msg
     * @return
     */
    public char[] criptografa(final char[] msg) {
        sbox = initSBox(key);
        char[] code = new char[msg.length];
        int i = 0;
        int j = 0;
        for (int n = 0; n < msg.length; n++) {
            i = (i + 1) % SBOX_LENGTH;
            j = (j + sbox[i]) % SBOX_LENGTH;
            swap(i, j, sbox);
            int rand = sbox[(sbox[i] + sbox[j]) % SBOX_LENGTH];
            code[n] = (char) (rand ^ (int) msg[n]);
        }
        return code;
    }

    /**
     *
     * @param key
     * @return
     */
    private int[] initSBox(char[] key) {
        int[] sbox = new int[SBOX_LENGTH];
        int j = 0;

        for (int i = 0; i < SBOX_LENGTH; i++) {
            sbox[i] = i;
        }

        for (int i = 0; i < SBOX_LENGTH; i++) {
            j = (j + sbox[i] + key[i % key.length]) % SBOX_LENGTH;
            swap(i, j, sbox);
        }
        return sbox;
    }

    /**
     *
     * @param i
     * @param j
     * @param sbox
     */
    private void swap(int i, int j, int[] sbox) {
        int temp = sbox[i];
        sbox[i] = sbox[j];
        sbox[j] = temp;
    }

    /**
     *
     * @param key
     * @throws InvalidKeyException
     */
    public void setKey(String key) throws InvalidKeyException {
        if (!(key.length() >= TAM_MIN_CHAVE && key.length() < SBOX_LENGTH)) {
            throw new InvalidKeyException("Tamanho da chave deve ser entre "
                    + TAM_MIN_CHAVE + " e " + (SBOX_LENGTH - 1));
        }

        this.key = key.toCharArray();
    }

}
