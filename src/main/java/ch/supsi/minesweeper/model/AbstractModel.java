package ch.supsi.minesweeper.model;

public abstract class AbstractModel {

    public abstract int getMineCount();

    public abstract Cell[][] getBoard();

    public abstract void setGameStarted(boolean b);

    public abstract boolean isGameOver();

    public abstract boolean isGameWon();
    public abstract int getRevealedCellCount();
    public abstract int getRemainingMines();
    public abstract void setRevealedCellCount(int revealedCellCount);
    public abstract boolean isGameStarted();
    public abstract void setGameWon(boolean gameWon);
    abstract void checkWinCondition();
    public abstract void setGameOver(boolean gameOver);
    public abstract void setFlaggedCellCount(int flaggedCellCount);
    public abstract int getFlaggedCellCount();
}
