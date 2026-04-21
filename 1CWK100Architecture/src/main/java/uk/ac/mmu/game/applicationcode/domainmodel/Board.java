package uk.ac.mmu.game.applicationcode.domainmodel;

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
}

