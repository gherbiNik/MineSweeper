package ch.supsi.minesweeper.controller;

import ch.supsi.minesweeper.MainFx;
import ch.supsi.minesweeper.model.GameEventHandler;
import ch.supsi.minesweeper.model.GameModel;
import ch.supsi.minesweeper.model.PlayerEventHandler;
import ch.supsi.minesweeper.view.DataView;

import java.util.List;

public class GameController implements GameEventHandler, PlayerEventHandler {

    private static GameController myself;
    private GameModel gameModel;
    private List<DataView> views;
    private MainFx mainFx;

    private GameController() {
        this.gameModel = GameModel.getInstance();
    }

    public static GameController getInstance() {
        if (myself == null) {
            myself = new GameController();
        }
        return myself;
    }

    public void initialize(List<DataView> views) {
        this.views = views;
    }

    public void setMainFx(MainFx mainFx) {
        this.mainFx = mainFx;
    }

    @Override
    public void newGame() {
        gameModel.newGame();
        mainFx.switchGameBoard();
        this.views.forEach(view -> view.newGameMessage());
    }

    @Override
    public void save() {
        gameModel.save();
        this.views.forEach(DataView::update);
    }

    @Override
    public void move() {
        // Metodo generico, non utilizzato direttamente
    }

    @Override
    public void quit(){
        gameModel.quit();

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