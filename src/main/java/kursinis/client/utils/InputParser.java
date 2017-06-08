package kursinis.client.utils;

import kursinis.client.exceptions.InputFormatException;
import org.thymeleaf.util.StringUtils;
import service.Atom;
import service.Input;
import service.ObjectFactory;
import service.Rule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InputParser {

    private static ObjectFactory OBJECT_FACTORY = new ObjectFactory();

    public static Input parseToInput (String rules, String facts, String goal) throws InputFormatException {
        if (StringUtils.isEmpty(rules)) {
            throw new InputFormatException("Nenurodytas nei viena taisyklė.");
        }
        if (StringUtils.isEmpty(facts)) {
            throw new InputFormatException("Nenurodytas nei vienas faktas.");
        }
        if (StringUtils.isEmpty(goal)) {
            throw new InputFormatException("Nenurodytas sprendimo tikslas.");
        }
        Input input = OBJECT_FACTORY.createInput();
        input.setRules(OBJECT_FACTORY.createInputRules());
        String[] lines = rules.split(System.getProperty("line.separator"));
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            input.getRules().getRule().add(parseRule(line, i));
        }
        input.getFact().addAll(parseAtoms(facts));
        input.setGoal(parseGoal(goal));
        return input;
    }

    private static Atom parseGoal(String goalText) throws InputFormatException {
        List<Atom> atoms = parseAtoms(goalText);
        if (atoms.size() > 1) {
            throw new InputFormatException("Sprendinyje negalimas daugiau nei vienas tikslas.");
        } else {
            return atoms.get(0);
        }
    }

    private static List<Atom> parseAtoms(String atomsText)
            throws InputFormatException {
        String[] words = atomsText.split("\\s+");
        if (words.length != 0) {
            List<Atom> atoms = new ArrayList<>();
            for (String word : words) {
                Atom atom = OBJECT_FACTORY.createAtom();
                atom.setPredicateName(word);
                atoms.add(atom);
            }
            return atoms;
        } else {
            throw new InputFormatException("Nerasta nei vieno objekto.");
        }
    }

    private static Rule parseRule(String ruleText, int number)
            throws InputFormatException {
        String[] words = ruleText.split("\\s+");
        if (words.length >= 2) {
            try {
                Atom rightAtom = OBJECT_FACTORY.createAtom();
                rightAtom.setPredicateName(words[0]);
                Rule rule = OBJECT_FACTORY.createRule();
                rule.setAllLeftHandSide(OBJECT_FACTORY.createRuleAllLeftHandSide());
                String[] newArray = Arrays.copyOfRange(words, 1, words.length);
                for (String word : newArray) {
                    Atom leftAtom = OBJECT_FACTORY.createAtom();
                    leftAtom.setPredicateName(word);
                    rule.getAllLeftHandSide().getLeftHandSide().add(leftAtom);
                }
                rule.setName("R" + Integer.toString(number));
                rule.setRightHandSide(rightAtom);
                return rule;
            } catch (IllegalArgumentException e) {
                throw new InputFormatException("Nepavyko nuskaityti taisyklės.");
            }
        } else {
            throw new InputFormatException("Nepavyko nuskaityti taisyklės.");
        }
    }
}
