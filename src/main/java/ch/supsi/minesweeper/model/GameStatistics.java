package ch.supsi.minesweeper.model;

public interface GameStatistics {
    int getMineCount();
    int getRemainingMines();
    int getRevealedCellCount();

}
