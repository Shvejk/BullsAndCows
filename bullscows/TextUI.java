package bullscows;

import java.util.Scanner;

public class TextUI {
    private final Scanner scanner;
    private final BullsAndCows bullsAndCows;

    public TextUI(Scanner scanner, BullsAndCows bullsAndCows) {
        this.scanner = scanner;
        this.bullsAndCows = bullsAndCows;
    }

    public void start() {
        System.out.println("Input the length of the secret code:");
        String userInputForCodeLength = scanner.nextLine();
        int codeLength = 0;

        if (isInteger(userInputForCodeLength)) {
            codeLength = Integer.parseInt(userInputForCodeLength);
        } else {
            System.out.println("Error: \"" + userInputForCodeLength + "\" is not a valid number");
            return;
        }

        System.out.println("Input the number of possible symbols in the code:");
        String userInputForTotalSymbols = scanner.nextLine();
        int totalSymbols = 0;

        if (isInteger(userInputForTotalSymbols)) {
            totalSymbols = Integer.parseInt(userInputForTotalSymbols);
        } else {
            System.out.println("Error: \"" + userInputForTotalSymbols + "\" is not a valid number");
            return;
        }

        bullsAndCows.setCodeLength(codeLength);
        bullsAndCows.setTotalSymbols(totalSymbols);

        if (!bullsAndCows.isValidTotalSymbols()) {
            System.out.println("Error: it's not possible to generate a code with a length " +
                    "of " + codeLength + " with " + totalSymbols + " unique symbols.");
            return;
        }

        if (bullsAndCows.isValidTotalSymbols() && bullsAndCows.isValidLength()) {
            bullsAndCows.generateSecretCode();
            System.out.println("The secret is prepared: " + bullsAndCows.displayCodeAsAsterisks() +
                    " " + bullsAndCows.displayCodeRange());
        } else {
            System.out.println("Error: can't generate a secret number with a length of " + codeLength +
                    " because there aren't enough unique digits or number is less than one.");
            return;
        }

        beginGame();

        while (true) {
            System.out.println("Turn " + bullsAndCows.getTurn() + ":");
            String guess = scanner.nextLine();
            String grade = bullsAndCows.getGrade(guess);
            System.out.println("Grade: " + grade);
            if (bullsAndCows.getBullCount(guess) == codeLength) {
                System.out.println("Congratulations! You guessed the secret code.");
                break;
            }
            bullsAndCows.nextTurn();
        }

    }

    private static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static void beginGame() {
        System.out.println("Okay, let's start a game!");
    }
}