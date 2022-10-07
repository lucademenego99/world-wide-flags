package it.unitn.disi.webarch.lucademenego.assignment2.model;

import it.unitn.disi.webarch.lucademenego.assignment2.utils.Pair;

import java.io.Serializable;
import java.util.*;

public class FlagsBean implements Serializable {
    private List<Pair<String, String>> flagsToGuess;

    private Random random;

    private final int numberOfFlagsToGuess = 3;

    private final List<Pair<String, String>> flags = Arrays.asList(
            new Pair<String, String>("Algeria", "Algiers"),
            new Pair<String, String>("Armenia", "Yerevan"),
            new Pair<String, String>("Chad", "N'Djamena"),
            new Pair<String, String>("CzechRepublic", "Prague"),
            new Pair<String, String>("Djibouti", "Djibouti"),
            new Pair<String, String>("Gabon", "Libreville"),
            new Pair<String, String>("Indonesia", "Jakarta"),
            new Pair<String, String>("Lithuania", "Vilnius"),
            new Pair<String, String>("Malta", "La Valletta"),
            new Pair<String, String>("Ukraine", "Kiev")
    );

    public FlagsBean() {
        flagsToGuess = new ArrayList<>();
        random = new Random();
    }

    private void randomizeFlagsToGuess() {
        List<Pair<String, String>> clonedFlags = new ArrayList<>(flags);
        Collections.shuffle(clonedFlags);
        flagsToGuess.clear();
        for (int i = 0; i < numberOfFlagsToGuess; i++) {
            flagsToGuess.add(clonedFlags.get(i));
        }
    }

    public List<Pair<String, String>> getFlagsToGuess() {
        return flagsToGuess;
    }

    public List<Pair<String, String>> getFlags() {
        return flags;
    }

    public String flagsListToHTML(String stylesEven, String stylesOdd) {
        String result = "";
        for (int i = 0; i < this.flags.size(); i++) {
            result += "<div class='" + (i % 2 == 0 ? stylesEven : stylesOdd) + "'>" +
                    "<p class='text-xl font-semibold'>" + (i + 1) + "</p>" +
                    "<p class='text-sm font-semibold'>" + this.flags.get(i).snd + "</p>" +
                    "</div>";
        }
        return result;
    }

    public String guessListToHTML(String stylesEven, String stylesOdd, String stylesInput) {
        randomizeFlagsToGuess();
        String result = "";
        for (int i = 0; i < this.flagsToGuess.size(); i++) {
            String img = "<img class='w-3/12' src='../assets/" + this.flagsToGuess.get(i).fst + ".jpg' alt='' />";
            String input = "<input required type='number' name='flag" + (i + 1) + "' placeholder='Flag " + (i + 1) + "' id='flag-" + (i + 1) + "' class='" + stylesInput + "' />";
            result += "<div class='" + (i % 2 == 0 ? stylesEven : stylesOdd) + "'>" +
                    ((i % 2 == 0) ? (img + input) : (input + img)) +
                    "</div>";
        }
        return result;
    }
}
