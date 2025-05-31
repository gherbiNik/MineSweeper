package ch.supsi.frontend.controller;

import ch.supsi.backend.application.l10n.TranslationsApplication;
import ch.supsi.backend.business.l10n.TranslationsBusinessInterface;
import ch.supsi.backend.business.preferences.PreferencesBusinessInterface;
import ch.supsi.frontend.model.game.GameModel;
import ch.supsi.frontend.model.game.GameModelInterface;
import ch.supsi.frontend.view.DataView;

import java.util.List;

public class GameController implements GameEventHandler, PlayerEventHandler {

    private static GameController myself;
    private final GameModelInterface gameModel;
    private List<DataView> views;
    private TranslationsApplication translationsApplication;

    private GameController(GameModel gameModel) {
        this.gameModel = gameModel;

    }

    public static GameController getInstance(GameModel gameModel) {
        if (myself == null) {
            myself = new GameController(gameModel);
        }
        return myself;
    }

    public void initialize(List<DataView> views, PreferencesBusinessInterface preferencesBusinessInterface, TranslationsBusinessInterface translationsBusinessInterface) {
        this.views = views;
        this.translationsApplication = TranslationsApplication.getInstance(preferencesBusinessInterface, translationsBusinessInterface);

    }

    @Override
    public void newGame() {
        gameModel.newGame();
        //mainFx.switchGameBoard();
        this.views.forEach(DataView::newGameMessage);
    }




    @Override
    public void quit() {
        gameModel.quit();

    }



    @Override
    public void move(int row, int col, boolean isRightClick) {
        gameModel.move(row, col, isRightClick);

        // Aggiorna le viste con lo stato attuale
        if (gameModel.isGameOver()) {
            if (gameModel.isGameWon()) {
                this.views.forEach(view -> view.gameOverMessage(translationsApplication.translate("label.winGame")));
            } else {
                this.views.forEach(view -> view.gameOverMessage(translationsApplication.translate("label.gameOver")));
            }
        } else if (isRightClick) {
            this.views.forEach(view -> view.flagUpdateMessage(gameModel.getRemainingMines()));
        } else {
            this.views.forEach(DataView::update);
        }
    }
}