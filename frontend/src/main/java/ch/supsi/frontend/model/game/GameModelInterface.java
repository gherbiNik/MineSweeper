package ch.supsi.frontend.model.game;

import ch.supsi.backend.business.cell.ICell;
import ch.supsi.frontend.model.event.IGameEventModel;
import ch.supsi.frontend.model.event.IPlayerEventModel;

public interface GameModelInterface extends IGameEventModel, IPlayerEventModel {


    ICell getCell(int x, int y);


    int getMineCount();

    int getRemainingMines();


    boolean isGameOver();

    boolean isGameWon();

    boolean isGameStarted();


}
