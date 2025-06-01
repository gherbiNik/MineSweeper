package ch.supsi.frontend.model.game;

import ch.supsi.backend.application.game.GameApplicationInterface;
import ch.supsi.backend.business.cell.ICell;
import javafx.stage.Stage;

public class GameModel implements GameModelInterface {

    private static GameModel instance;
    private GameApplicationInterface app;

    private GameModel() {

    }
    public static GameModel getInstance(GameApplicationInterface app) {
        if (instance == null) {
            instance = new GameModel();
            instance.initialize(app);
        }
        return instance;
    }

    private void initialize(GameApplicationInterface app){
        this.app = app;
    }



    @Override
    public void newGame() {
        app.newGame();
    }


    @Override
    public void move(int row, int col, boolean isRightClick) {
        app.move(row, col, isRightClick);
    }





    @Override
    public ICell getCell(int x, int y) {
        return app.getCell(x, y);
    }



    @Override
    public int getMineCount() {
        return app.getMineCount();
    }

    @Override
    public int getRemainingMines() {
        return app.getRemainingMines();
    }



    @Override
    public boolean isGameOver() {
        return app.isGameOver();
    }

    @Override
    public boolean isGameWon() {
        return app.isGameWon();
    }

    @Override
    public boolean isGameStarted() {
        return app.isGameStarted();
    }


}