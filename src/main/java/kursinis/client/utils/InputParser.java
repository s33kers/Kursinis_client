package kursinis.client.utils;

import kursinis.client.exceptions.InputFormatException;
import service.Atom;
import service.Input;
import service.Rule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InputParser {

    public static Input parseToInput (String rules, String facts, String goal) throws InputFormatException {
        Input input = new Input();
        String[] lines = rules.split(System.getProperty("line.separator"));
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            input.getRules().add(parseRule(line, i));
        }
        input.getFacts().addAll(parseAtoms(facts));
        input.setGoal(parseGoal(goal));
        return input;
    }

    private static Atom parseGoal(String goalText) throws InputFormatException {
        List<Atom> atoms = parseAtoms(goalText);
        if (atoms.size() > 1) {
            throw new InputFormatException("Failed goal parsing. One goal allowed.");
        } else {
            return atoms.get(0);
        }
    }

    private static List<Atom> parseAtoms(String atomsText)
            throws InputFormatException {
        String[] words = atomsText.split("\\s+");
        if (words.length != 0) {
            List<Atom> atoms = new ArrayList<>();
            for (int i = 0; i < words.length; i++) {
                Atom atom = new Atom();
                atom.setPredicateName(words[i]);
                atoms.add(atom);
            }
            return atoms;
        } else {
            throw new InputFormatException("Failed atoms parsing. Zero atoms found.");
        }
    }

    private static Rule parseRule(String ruleText, int number)
            throws InputFormatException {
        String[] words = ruleText.split("\\s+");
        if (words.length >= 2) {
            try {
                Atom rightAtom = new Atom();
                rightAtom.setPredicateName(words[0]);
                Rule rule = new Rule();
                String[] newArray = Arrays.copyOfRange(words, 1, words.length);
                for (String word : newArray) {
                    Atom leftAtom = new Atom();
                    leftAtom.setPredicateName(word);
                    rule.getLeftHandSide().add(leftAtom);
                }
                rule.setName("R" + Integer.toString(number));
                rule.setRightHandSide(rightAtom);
                return rule;
            } catch (IllegalArgumentException e) {
                throw new InputFormatException("Failed rule parsing.");
            }
        } else {
            throw new InputFormatException("Failed rule parsing.");
        }
    }
}
