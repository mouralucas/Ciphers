package ciphers;

/**
 *
 * @author Lucas Penha de Moura - 1208977
 */
public class LetterFrequency {

    private char letter;
    private double frequency;

    public LetterFrequency() {
    }

    public LetterFrequency(char letter, double frequency) {
        this.letter = letter;
        this.frequency = frequency;
    }

    public char getLetter() {
        return letter;
    }

    public void setLetter(char letter) {
        this.letter = letter;
    }

    public double getFrequency() {
        return frequency;
    }

    public void setFrequency(double frequency) {
        this.frequency = frequency;
    }

}
