package ch.supsi.frontend.controller;

import ch.supsi.frontend.MainFx;
import ch.supsi.frontend.model.GameEventHandler;
import ch.supsi.frontend.model.GameModel;
import ch.supsi.frontend.model.PlayerEventHandler;
import ch.supsi.frontend.view.DataView;

import java.util.List;

public class GameController implements GameEventHandler, PlayerEventHandler {

    private static GameController myself;
    private final GameModel gameModel;
    private List<DataView> views;

    private GameController(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    public static GameController getInstance(GameModel gameModel) {
        if (myself == null) {
            myself = new GameController(gameModel);
        }
        return myself;
    }

    public void initialize(List<DataView> views) {
        this.views = views;
    }

    @Override
    public void newGame() {
        gameModel.newGame();
        //mainFx.switchGameBoard();
        this.views.forEach(DataView::newGameMessage);
    }

    @Override
    public void save() {
        gameModel.save();
        this.views.forEach(DataView::update);
    }


    @Override
    public void quit() {
        gameModel.quit();

    }

    @Override
    public void open() {
        gameModel.open();
    }

    @Override
    public void move(int row, int col, boolean isRightClick) {
        gameModel.move(row, col, isRightClick);

        // Aggiorna le viste con lo stato attuale
        if (gameModel.isGameOver()) {
            if (gameModel.isGameWon()) {
                this.views.forEach(view -> view.gameOverMessage("Complimenti! Hai vinto!"));
            } else {
                this.views.forEach(view -> view.gameOverMessage("Game Over! Hai perso."));
            }
        } else if (isRightClick) {
            this.views.forEach(view -> view.flagUpdateMessage(gameModel.getRemainingMines()));
        } else {
            this.views.forEach(DataView::update);
        }
    }
}