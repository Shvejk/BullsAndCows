package bullscows;

import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BullsAndCows bullsAndCows = new BullsAndCows();
        TextUI textUI = new TextUI(scanner, bullsAndCows);
        textUI.start();
    }
}


