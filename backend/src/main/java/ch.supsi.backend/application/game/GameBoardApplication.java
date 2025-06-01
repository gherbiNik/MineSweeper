package ch.supsi.backend.application.game;

import ch.supsi.backend.business.game.GameBoardBusiness;

public class GameBoardApplication implements GameBoardInfoInterface{
    private GameBoardBusiness gameBoardBusiness;
    private static GameBoardApplication myself;

    private GameBoardApplication() {
    }

    public static GameBoardApplication getInstance(GameBoardBusiness gameBoardBusiness)
    {
        if(myself == null)
        {
            myself = new GameBoardApplication();
            myself.initialize(gameBoardBusiness);
        }
        return myself;
    }

    private void initialize(GameBoardBusiness gameBoardBusiness)
    {
        this.gameBoardBusiness = gameBoardBusiness;
    }

    @Override
    public int getSize() {
        return gameBoardBusiness.getSize();
    }
}
