package ch.supsi.backend.business.model;

public abstract class AbstractModel implements GameState, BoardAccess, GameStatistics {

    public abstract void setRevealedCellCount(int revealedCellCount);

    public abstract void checkWinCondition();

    public abstract void setGameOver(boolean gameOver);

    public abstract void setFlaggedCellCount(int flaggedCellCount);

    public abstract int getFlaggedCellCount();
}
