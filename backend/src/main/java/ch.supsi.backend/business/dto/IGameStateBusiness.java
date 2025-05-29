package ch.supsi.backend.business.dto;

public interface IGameStateBusiness {
    ICellStateBusiness[][] getCells();
    int getMineCount();
    int getRevealedCellCount();
    int getFlaggedCellCount();
    boolean isGameStarted();
    boolean isGameOver();
    boolean isGameWon();
    int getBoardSize();
    int getMaxBomb();
    int getMinBomb();
    long getTimestamp();
}
