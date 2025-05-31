package ch.supsi.frontend.model.game;

import ch.supsi.backend.business.cell.ICell;
import ch.supsi.frontend.model.event.IGameEventModel;
import ch.supsi.frontend.model.event.IPlayerEventModel;

public interface GameModelInterface extends IGameEventModel, IPlayerEventModel {
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
