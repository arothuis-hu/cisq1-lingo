package nl.hu.cisq1.lingo.trainer.domain;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Round {
    private String wordToGuess;
    private int tried;

    public Round(String word) {
        this.wordToGuess = word;
        this.tried = 0;
    }

    public Feedback guess(String guess) {
        this.tried++;

        List<Mark> marks = new ArrayList<>();
        String presentLetters = wordToGuess;
        String lowerCaseGuess = guess == null ? "" : guess.toLowerCase();

        if (guess == null || guess.length() == 0 || guess.length() != wordToGuess.length() || lowerCaseGuess.charAt(0) != wordToGuess.charAt(0)) {
            for (int i = 0; i < wordToGuess.length(); i++) {
                marks.add(Mark.ILLEGAL);
            }
            return new Feedback(guess, marks);
        }

        for (int i = 0; i < lowerCaseGuess.length(); i++) {
            String charAsString = lowerCaseGuess.charAt(i)+"";
            if (lowerCaseGuess.charAt(i) == wordToGuess.charAt(i)) {
                marks.add(Mark.CORRECT);
                presentLetters.replaceFirst(charAsString, "");
                continue;
            }
            if (presentLetters.contains(charAsString)) {
                marks.add(Mark.PRESENT);
                presentLetters.replaceFirst(charAsString, "");
                continue;
            }
            marks.add(Mark.WRONG);
        }

        return new Feedback(guess, marks);
    }
}
