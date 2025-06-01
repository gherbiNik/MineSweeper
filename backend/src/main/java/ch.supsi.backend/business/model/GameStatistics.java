package ch.supsi.backend.business.model;

import ch.supsi.backend.business.game.GameBoardBusiness;
import ch.supsi.backend.business.game.GameBoardInfo;
import ch.supsi.backend.business.game.GameBombBusiness;
import ch.supsi.backend.business.game.GameBombInfo;

public interface GameStatistics {
    int getMineCount();

    int getRemainingMines();

    int getRevealedCellCount();

    GameBombInfo getGameBombBusiness();

    GameBoardInfo getGameBoardBusiness();
}
