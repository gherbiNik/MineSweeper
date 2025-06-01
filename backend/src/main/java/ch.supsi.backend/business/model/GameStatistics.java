package ch.supsi.backend.business.model;

import ch.supsi.backend.business.game.GameBoardInfo;
import ch.supsi.backend.business.game.GameBombInfo;

public interface GameStatistics {
    int getMineCount();
    void setMineCount(int mineCount);

    int getRemainingMines();

    int getRevealedCellCount();

    GameBombInfo getGameBombBusiness();

    GameBoardInfo getGameBoardBusiness();
}
