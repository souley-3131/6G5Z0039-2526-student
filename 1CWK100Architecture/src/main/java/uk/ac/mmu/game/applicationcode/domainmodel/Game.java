package uk.ac.mmu.game.applicationcode.domainmodel;

import uk.ac.mmu.game.applicationcode.variations.Variation;

import java.util.Random;

public class Game {

    private final Variation variation;

    private final Board board = new Board();
    private final Random random = new Random();

    private int redPos = board.getRedStart();
    private int bluePos = board.getBlueStart();
    private boolean redTurn = true;

    private int redTurns = 0;
    private int blueTurns = 0;

    public Game(Variation variation) {
        this.variation = variation;
    }

    public void play() {

        System.out.println("\n===================");
        System.out.println("Game Variation " + variation);
        System.out.println("====================");
        System.out.println("Starting Basic Game!");
        System.out.println("Red starts at " + redPos);
        System.out.println("Blue starts at " + bluePos);

        while (true) {

            if (redTurn) {
                redTurns++;
                System.out.println("Turn " + redTurns);
                System.out.println("\nRed's turn");

                int roll = variation == Variation.SINGLE_DIE
                        ? rollSingleDie("Red")
                        : rollDice("Red");

                System.out.println("Red rolled " + roll);

                int oldRed = redPos;

                redPos += roll;
                System.out.println("Red moved to " + redPos);

                if (variation == Variation.HIT && redPos == bluePos) {
                    System.out.println("Red HIT Blue! Red stays at " + (oldRed));
                    redPos = oldRed;
                }

                if(variation == Variation.EXACT_END) {
                    int end = board.getRedEnd();

                    if(redPos > end) {
                        int overshoot = redPos - end;
                        redPos = end -  overshoot;
                        System.out.println("Red overshot! Bounced back to " + redPos);
                    }

                    if (redPos == end) {
                        System.out.println("Red Wins!");
                        int totalTurns = redTurns + blueTurns;
                        System.out.println("Total turns taken by all players: " + totalTurns);
                    }

                } else {

                    if (redPos >= board.getRedEnd()) {
                        System.out.println("Red wins!");
                        int totalTurns = redTurns + blueTurns;
                        System.out.println("Total turns taken by all players: " + totalTurns);
                        break;
                    }
                }

            } else {
                blueTurns++;
                System.out.println("Turn " + blueTurns);
                System.out.println("\nBlue's turn");

                int roll = (variation == Variation.SINGLE_DIE)
                        ? rollSingleDie("Blue")
                        : rollDice("Blue");

                System.out.println("Blue rolled " + roll);

                int oldBlue = bluePos;

                bluePos -= roll;
                System.out.println("Blue moved to " + bluePos);

                if (variation == Variation.HIT && bluePos == redPos) {
                    System.out.println("Blue HIT! Blue stays at " + (oldBlue));
                    bluePos = oldBlue;
                }

                if (variation == Variation.EXACT_END) {
                    int end = board.getBlueEnd();

                    if(bluePos < end) {
                        int overshoot = end - bluePos;
                        bluePos = end +  overshoot;
                        System.out.println("Blue overshot! Bounced back to " + bluePos);
                    }

                    if (bluePos == end) {
                        System.out.println("Blue Wins!");
                        int totalTurns = blueTurns + redTurns;
                        System.out.println("Total turns taken by all players: " + totalTurns);
                    }
                }

                if (bluePos <= board.getBlueEnd()) {
                    System.out.println("Blue wins!");
                    int totalTurns = blueTurns + redTurns;
                    System.out.println("Total turns taken by all players: " + totalTurns);
                    break;
                }
            }
            redTurn = !redTurn;
        }
    }

    private int rollDice(String playerName) {
        int d1 = random.nextInt(6) + 1;
        int d2 = random.nextInt(6) + 1;
        int total = d1 + d2;

        System.out.println(playerName + " rolled: " + d1 + " and " + d2 + " (total = " + total + ")");

        return total;
    }

    private int rollSingleDie(String playerName) {
        int d = random.nextInt(6) + 1;
        System.out.println(playerName + "rolled " + d);
        return d;
    }
}

