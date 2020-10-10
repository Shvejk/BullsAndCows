package bullscows;

import java.util.Random;

public class BullsAndCows {
    public static final int MAX_SYMBOLS = 36;
    private Random random;
    private String secretCode;
    private int turn;
    private int totalSymbols;
    private int codeLength;

    public BullsAndCows() {
        this.turn = 1;
        this.random = new Random();
    }

    public boolean isValidTotalSymbols() {
        return totalSymbols >= codeLength && totalSymbols <= MAX_SYMBOLS;
    }

    public void setCodeLength(int codeLength) {
        this.codeLength = codeLength;
    }

    public void setTotalSymbols(int totalSymbols) {
        this.totalSymbols = totalSymbols;
    }

    public int getTurn() {
        return turn;
    }

    public void nextTurn() {
        this.turn++;
    }

    public boolean isValidLength() {
        return codeLength <= 36 && codeLength > 0;
    }

    public String getSecretCode() {
        return secretCode;
    }

    public String getGrade(String guess) {
        int bullCount = getBullCount(guess);
        int cowCount = getCowCount(guess);

        String bull = "";
        String cow = "";

        if (bullCount == 1) {
            bull = "bull";
        }
        if (bullCount > 1) {
            bull = "bulls";
        }

        if (cowCount == 1) {
            cow = "cow";
        }
        if (cowCount > 1) {
            cow = "cows";
        }

        if (bullCount != 0 && cowCount != 0) {
            return bullCount + " " + bull + " and " + cowCount + " " + cow;
        } else if (bullCount == 0 && cowCount != 0) {
            return cowCount + " " + cow;
        } else if (bullCount != 0) {
            return bullCount + " " + bull;
        } else {
            return "None.";
        }
    }

    public String displayCodeAsAsterisks() {
        StringBuilder codeAsAsterisks = new StringBuilder();
        codeAsAsterisks.append("*".repeat(secretCode.length()));
        return codeAsAsterisks.toString();
    }

    public String displayCodeRange() {
        String codeRangeString = "(0-";
        if (totalSymbols < 11) {
            return codeRangeString + (totalSymbols - 1) + ").";
        } else {
            return codeRangeString + "9, a-" + (char) ('a' + howManyLetters(this.totalSymbols) - 1) + ").";
        }
    }

    public int getBullCount(String guess) {
        int bullCount = 0;
        for (int i = 0; i < guess.length(); i++) {
            if (guess.charAt(i) == secretCode.charAt(i)) {
                bullCount++;
            }
        }
        return bullCount;
    }

    public int getCowCount(String guess) {
        int cowCount = 0;
        for (int i = 0; i < secretCode.length(); i++) {
            for (int j = 0; j < guess.length(); j++) {
                if (i != j && secretCode.charAt(i) == guess.charAt(j) &&
                        secretCode.charAt(i) != guess.charAt(i)) {
                    cowCount++;
                }
            }
        }
        return cowCount;
    }

    public void generateSecretCode() {
        StringBuilder secretNumber = new StringBuilder();
        while (secretNumber.length() < codeLength) {
            double charOrInt = Math.random();
            if (charOrInt < 0.5) {
                int randomNumber = random.nextInt(10);
                if (secretNumber.indexOf(String.valueOf(randomNumber)) == -1) {
                    secretNumber.append(randomNumber);
                }
            } else {
                if (totalSymbols >= 11) {
                    char ch = generateRandomChar(howManyLetters(totalSymbols));
                    if (secretNumber.indexOf(String.valueOf(ch)) == -1) {
                        secretNumber.append(ch);
                    }
                }
            }
        }
        this.secretCode = secretNumber.toString();
    }

    private int howManyNumbers() {
        return Math.min(totalSymbols, 10);
    }

    private int howManyLetters(int totalSymbols) {
        if (totalSymbols < 11) {
            return 0;
        }
        return totalSymbols - 10;
    }

    public char generateRandomChar(int bound) {
        return (char) ('a' + random.nextInt(bound));
    }
}

