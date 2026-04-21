package uk.ac.mmu.game.applicationcode.domainmodel;

import java.util.Random;

public class Game {

    private final Board board = new Board();
    private final Random random = new Random();

    private int redPos = board.getRedStart();
    private int bluePos = board.getBlueStart();
    private boolean redTurn = true;

    public void play() {

        System.out.println("Starting Basic Game!");
        System.out.println("Red starts at " + redPos);
        System.out.println("Blue starts at " + bluePos);

        while (true) {

            if (redTurn) {
                System.out.println("\nRed's turn");
                int roll = rollDice();
                System.out.println("Red rolled " + roll);
                redPos += roll;
                System.out.println("Red moved to " + redPos);

                if (redPos >= board.getRedEnd()) {
                    System.out.println("Red wins!");
                    break;
                }

            } else {
                System.out.println("\nBlue's turn");
                int roll = rollDice();
                System.out.println("Blue rolled " + roll);
                bluePos -= roll;
                System.out.println("Blue moved to " + bluePos);

                if (bluePos <= board.getBlueEnd()) {
                    System.out.println("Blue wins!");
                    break;
                }
            }
            redTurn = !redTurn;
        }
    }

    private int rollDice() {
        int d1 = random.nextInt(6) + 1;
        int d2 = random.nextInt(6) + 1;
        return d1 + d2;
    }
}


