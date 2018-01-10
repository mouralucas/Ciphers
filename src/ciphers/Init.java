package ciphers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lucas Penha de Moura - 1208977
 */
public class Init {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        List<String> information = new ArrayList<>();
        CesarCipher cc = new CesarCipher();
        VernamCipher vc = new VernamCipher();
        Frequency frequency = new Frequency();
        List<LetterFrequency> lFrequency;
        BufferedReader readFile, readVernam;
        Formatter recordFile, recordVernam;
        String textString = "";
        int option, cesarKey;

        System.out.println("1 - Cesar Encrypt \n"
                + "2 - Cesar Decrypt \n"
                + "3 - Decrypt with frequency analysis \n"
                + "4 - Vernam Encrypt \n"
                + "5 - Vernam Decrypt \n"
                + "6 - Freqency Analysis");
        Scanner input = new Scanner(System.in);
        option = input.nextInt();

        try {
            switch (option) {
                case 1://criptografa por Cesar
                    System.out.println("Encrypt key");
                    cesarKey = input.nextInt();
                    String aux2;

                    //criptografa cada linha do arquivo de origem
                    readFile = new BufferedReader(new FileReader("texto-aberto.txt"));
                    while (readFile.ready()) {
                        aux2 = readFile.readLine();
                        information.add(aux2);
                    }
                    readFile.close();

                    //verifica se no texto possui algum caracter invalido
                    if (!verifyCharacter(information)) {
                        System.out.println("Texto com caractere não permitido");
                        break;
                    }

                    //grava no arquivo de saída o texto criptografado
                    recordFile = new Formatter("cifrado-cesar.txt");
                    information.stream().forEach((info) -> {
                        //System.out.println("Encrypt: " + info);
                        recordFile.format("%s\r\n", cc.encrypt(cesarKey, info));
                    });

                    recordFile.close();
                    information.clear();
                    break;
                case 2://Descritografa por Cesar
                    System.out.println("Decrypt key");
                    cesarKey = input.nextInt();

                    readFile = new BufferedReader(new FileReader("cifrado-cesar.txt"));
                    recordFile = new Formatter("aberto-cesar.txt");

                    //descriptografa cada linho do arquivo de origem
                    while (readFile.ready()) {
                        information.add(cc.decrypt(cesarKey, readFile.readLine()));
                    }
                    readFile.close();

                    //grava o texto decifrado no arquivo correspondente
                    information.stream().forEach((info) -> {
                        System.out.println("Decrypt: " + info);
                        recordFile.format("%s\r\n", info);
                    });

                    recordFile.close();
                    information.clear();
                    break;
                case 3://Descriptografa por analise de frequencia em Cesar
                    String aux;
                    List<String> auxList = new ArrayList<>();

                    //le arquivo criptografado e une todas as linhas APENAS para analise de frequencia
                    //cria uma lista das strigs de cada linha separadas, para descriptografar linha a linha
                    readFile = new BufferedReader(new FileReader("cifrado-cesar.txt"));
                    while (readFile.ready()) {
                        aux = readFile.readLine();
                        textString = textString.concat(aux + " ");
                        auxList.add(aux);
                    }
                    readFile.close();

                    //analise de frequencia feita apenas em caracteres maiusculos
                    textString = textString.toUpperCase();
                    //determina a frequencia de cada caracter
                    lFrequency = frequency.letterFrequency(textString);
                    //determina qual caracter mais comum e atraves dele determina a chave de cesar
                    cesarKey = cc.CesarKeyFreq(frequency.mostCommonLetter(lFrequency));

                    //salva no arquivo o texto descriptografado
                    recordFile = new Formatter("aberto-cesar-freq.txt");
                    auxList.stream().forEach((record) -> {
                        System.out.println("Decrypt: " + cc.decrypt(cesarKey, record));
                        recordFile.format("%s\r\n", cc.decrypt(cesarKey, record));
                    });
                    recordFile.close();

                    break;
                case 4://criptografa em Vernam
                    List<String> key = new ArrayList<>();

                    //armazena cada linha no vetor de informação
                    readFile = new BufferedReader(new FileReader("texto-aberto.txt"));
                    while (readFile.ready()) {
                        information.add(readFile.readLine());
                    }
                    readFile.close();

                    //verifica se o texto possui caracter nao permitido
                    if (!verifyCharacter(information)) {
                        System.out.println("Texto com caractere não permitido");
                        break;
                    }

                    //cria uma chave de acordo com o tamanho de cada linha do arquivo
                    for (String info : information) {
                        key.add(vc.vernamKeyGenerator(info.length()));
                    }

                    //salva a chave de vernam para descriptografar
                    recordFile = new Formatter("vernamKey.txt");
                    key.stream().forEach((k) -> {
                        recordFile.format("%s\r\n", k);
                    });
                    recordFile.close();

                    //criptografa e grava o texto criptografado no arquivo
                    recordVernam = new Formatter("cifrado-vernam.txt");
                    for (int i = 0; i < information.size(); i++) {
                        recordVernam.format("%s\r\n", vc.encrypt(information.get(i), key.get(i)));
                    }
                    recordVernam.close();
                    information.clear();
                    break;
                case 5://descriptografa em Vernam
                    //adiciona as chaves de cada linha em um vetor
                    readFile = new BufferedReader(new FileReader("vernamKey.txt"));
                    List<String> keys = new ArrayList<>();

                    while (readFile.ready()) {
                        keys.add(readFile.readLine());
                    }
                    readFile.close();

                    //adiciona as linhas do texto criptografado em um vetor
                    System.out.println("tam info: " + information.size());
                    readVernam = new BufferedReader(new FileReader("cifrado-vernam.txt"));
                    List<String> teste = new ArrayList<>();
                    while (readVernam.ready()) {
                        teste.add(readVernam.readLine());
                    }
                    readVernam.close();

                    //Descriptografia do texto de vernam
                    recordFile = new Formatter("aberto-vernam.txt");
                    for (int i = 0; i < teste.size(); i++) {
                        recordFile.format("%s\r\n", vc.decrypt(teste.get(i), keys.get(i)));
                    }
                    recordFile.close();
                    teste.clear();
                    break;
                case 6:
                    List<LetterFrequency> freq;
                    String path;
                    System.out.println("Nome do arquivo");
                    path = input.next().replace(".txt", "");
                    readFile = new BufferedReader(new FileReader(path + ".txt"));
                    while (readFile.ready()) {
                        textString = textString.concat(readFile.readLine() + " ");
                    }
                    readFile.close();
                    textString = textString.toUpperCase();
                    freq = frequency.letterFrequency(textString);

                    for (LetterFrequency lf : freq) {
                        System.out.println("A letra: " + lf.getLetter() + " corresponde a " + lf.getFrequency()
                                + "% do total de letras");
                    }
                    System.out.println("A letra mais comum eh: " + frequency.mostCommonLetter(freq).getLetter());

                    break;
                case 7:
                    System.out.println("Aluno: Lucas Penha de Moura \n"
                            + "RA: 1208977");
                default:

                    break;
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Init.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Init.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static boolean verifyCharacter(List<String> text) {
        for (String s : text) {
            for (int i = 0; i < s.length(); i++) {
                if (!((s.charAt(i) >= 'a' && s.charAt(i) <= 'z')
                        || (s.charAt(i) >= 'A' && s.charAt(i) <= 'Z')
                        || (s.charAt(i) >= '0' && s.charAt(i) <= '9')
                        || s.charAt(i) == 32
                        || s.charAt(i) == 44
                        || s.charAt(i) == 46)) {
                    return false;
                }
            }
        }

        return true;
    }
}
