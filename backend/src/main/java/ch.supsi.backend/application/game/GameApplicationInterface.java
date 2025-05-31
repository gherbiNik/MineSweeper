package ch.supsi.backend.application.game;

import ch.supsi.backend.application.event.IGameEventApplication;
import ch.supsi.backend.application.event.IPlayerEventApplication;
import ch.supsi.backend.business.cell.ICell;

public interface GameApplicationInterface extends IGameEventApplication, IPlayerEventApplication{
    void setRevealedCellCount(int revealedCellCount);

    void checkWinCondition();

    void setFlaggedCellCount(int flaggedCellCount);

    int getFlaggedCellCount();

    void initializeBoard();

    ICell[][] getBoard();

    ICell getCell(int x, int y);

    void setBoard(ICell[][] board);

    int getMineCount();

    int getRemainingMines();

    int getRevealedCellCount();


    boolean isGameOver();

    boolean isGameWon();

    boolean isGameStarted();

    void setGameStarted(boolean b);

    void setGameWon(boolean gameWon);

    void setGameOver(boolean gameOver);
}
