package ciphers;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lucas Penha de Moura - 1208977
 */
public class Frequency {

    public List letterFrequency(String input) {
        List<LetterFrequency> frequencyList = new ArrayList<>();
        String v = "";
        int cont = 0;
        double percent;

        for (int i = 0; i < input.length(); i++) {
            for (int j = 0; j < input.length(); j++) {
                if (input.charAt(i) == input.charAt(j)) {
                    cont++;
                }
            }
            // ao imprimir as frequências, exclui a contagem dos espaços

            char c = input.charAt(i);
            percent = cont / (double) input.length() * 100;
            //only counts letter between a and z
            if (((c >= '0' && c <= '9') || (c >= 'A' && c <= 'Z')) && !v.contains("" + c)) {
                v = v + c;

                frequencyList.add(new LetterFrequency(input.charAt(i), percent));
            }
            cont = 0;
        }

        return frequencyList;
    }

    public LetterFrequency mostCommonLetter(List<LetterFrequency> list) {
        double freq = 0;
        
        LetterFrequency lf = list.get(0);
        for (LetterFrequency l : list) {
            if (l.getFrequency() > freq) {
                freq = l.getFrequency();
                lf = l;
            }
        }
        
        System.out.println("Letra mais comum: " + lf.getLetter()); 
        return lf;
   }
}
