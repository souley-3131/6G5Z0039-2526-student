package uk.ac.mmu.game.applicationcode.domainmodel;

import java.util.Map;

class Board {

    int getRedStart() {
        return 1;
    }

    int getRedEnd() {
        return 25;
    }

    int getBlueStart() {
        return 25;
    }

    int getBlueEnd() {
        return 1;
    }

    boolean isOnBoard(int position) {
        return position >= 1 && position <= 25;
    }

    private final Map<Integer, Integer> wormholes = Map.of(
            8, 20,
            20, 8,
            3, 23,
            23, 3
    );

    public boolean isWormhole(int position) {
        return wormholes.containsKey(position);
    }

    public int getWormhole(int position) {
        return wormholes.get(position);
    }
}