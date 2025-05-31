package ch.supsi.backend.business.model;

import ch.supsi.backend.business.event.IGameEventBusiness;
import ch.supsi.backend.business.event.IPlayerEventBusiness;

public abstract class AbstractModel implements GameState, BoardAccess, GameStatistics, IPlayerEventBusiness, IGameEventBusiness {

    public abstract void setRevealedCellCount(int revealedCellCount);

    public abstract void checkWinCondition();

    public abstract void setFlaggedCellCount(int flaggedCellCount);

    public abstract int getFlaggedCellCount();

    public abstract void initializeBoard();


}
