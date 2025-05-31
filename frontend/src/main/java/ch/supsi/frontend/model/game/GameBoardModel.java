package ch.supsi.frontend.model.game;

import ch.supsi.backend.application.game.GameBoardApplication;

public class GameBoardModel implements GameBoardModelInterface{
    private GameBoardApplication gameBoardApplication;
    private static GameBoardModel myself;

    private GameBoardModel() {
    }

    public static GameBoardModel getInstance(GameBoardApplication gameBoardApplication)
    {
        if(myself == null)
        {
            myself = new GameBoardModel();
            myself.initialize(gameBoardApplication);
        }
        return myself;
    }

    private void initialize(GameBoardApplication gameBoardApplication)
    {
        this.gameBoardApplication = gameBoardApplication;
    }


    @Override
    public void setDimensions(int size) {
        gameBoardApplication.setDimensions(size);
    }

    @Override
    public int getSize() {
        return gameBoardApplication.getSize();
    }
}
