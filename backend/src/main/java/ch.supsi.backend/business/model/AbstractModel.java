package ch.supsi.backend.business.model;

import ch.supsi.backend.application.game.GameBoardApplication;
import ch.supsi.backend.application.game.GameBombApplication;

public abstract class AbstractModel implements GameState, BoardAccess, GameStatistics {

    public abstract void setRevealedCellCount(int revealedCellCount);

    public abstract void checkWinCondition();

    public abstract void setFlaggedCellCount(int flaggedCellCount);

    public abstract int getFlaggedCellCount();


}
