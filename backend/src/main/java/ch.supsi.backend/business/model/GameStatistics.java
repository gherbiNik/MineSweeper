package ch.supsi.backend.business.model;

import ch.supsi.backend.application.game.GameBoardApplication;
import ch.supsi.backend.application.game.GameBombApplication;

public interface GameStatistics {
    int getMineCount();

    int getRemainingMines();

    int getRevealedCellCount();

    GameBombApplication getGameBombApplication();

    GameBoardApplication getGameBoardApplication();
}
