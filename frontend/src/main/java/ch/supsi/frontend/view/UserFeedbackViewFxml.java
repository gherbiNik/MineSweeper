package ch.supsi.frontend.view;

import ch.supsi.backend.application.l10n.TranslationsController;
import ch.supsi.backend.business.model.AbstractModel;
import ch.supsi.frontend.model.GameModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;

public class UserFeedbackViewFxml implements UncontrolledFxView {

    private static UserFeedbackViewFxml myself;
    private GameModel gameModel;
    // TODO vedere se cambiare o tenerlo cosi sia qui che per GameboardView
    private TranslationsController translationsController = new TranslationsController();

    @FXML
    private ScrollPane containerPane;

    @FXML
    private Text userFeedbackBar;

    private UserFeedbackViewFxml() {
    }

    public static UserFeedbackViewFxml getInstance() {
        if (myself == null) {
            myself = new UserFeedbackViewFxml();

            try {
                URL fxmlUrl = UserFeedbackViewFxml.class.getResource("/userfeedbackbar.fxml");
                if (fxmlUrl != null) {
                    FXMLLoader fxmlLoader = new FXMLLoader(fxmlUrl);
                    fxmlLoader.setController(myself);
                    fxmlLoader.load();
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return myself;
    }

    @Override
    public void initialize(AbstractModel model) {
        this.gameModel = (GameModel) model;
        this.userFeedbackBar.setText(translationsController.translate("label.welcome"));
    }

    @Override
    public Node getNode() {
        return this.containerPane;
    }

    @Override
    public void update() {
        if (gameModel.isGameStarted() && !gameModel.isGameOver()) {
            this.userFeedbackBar.setText(translationsController.translate("label.remainingMines") + ": " + gameModel.getRemainingMines());
        }
    }

    @Override
    public void newGameMessage() {
        this.userFeedbackBar.setText(translationsController.translate("label.startString") + ": " + gameModel.getMineCount() + translationsController.translate("label.mines"));
    }

    @Override
    public void flagUpdateMessage(int remainingMines) {
        this.userFeedbackBar.setText(translationsController.translate("label.remainingMines") + ": " + remainingMines);
    }

    @Override
    public void gameOverMessage(String message) {
        this.userFeedbackBar.setText(message);
    }
}