package ch.supsi.backend.application.game;

import ch.supsi.backend.application.event.IGameEventApplication;
import ch.supsi.backend.application.event.IPlayerEventApplication;
import ch.supsi.backend.business.cell.ICell;

public interface GameApplicationInterface extends IGameEventApplication, IPlayerEventApplication{


    ICell getCell(int x, int y);

    int getMineCount();

    int getRemainingMines();

    boolean isGameOver();

    boolean isGameWon();

    boolean isGameStarted();


}
