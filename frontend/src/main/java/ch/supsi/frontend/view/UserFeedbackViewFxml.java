package ch.supsi.frontend.view;

import ch.supsi.frontend.controller.l10n.TranslationControllerInterface;
import ch.supsi.frontend.model.game.GameModelInterface;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;

public class UserFeedbackViewFxml implements UncontrolledFxView, InfoView {

    private static UserFeedbackViewFxml myself;
    private GameModelInterface gameModel;

    private TranslationControllerInterface translationController;

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
    public void initialize(GameModelInterface model, TranslationControllerInterface translationController) {
        this.gameModel = model;
        this.translationController = translationController;
        this.userFeedbackBar.setText(translationController.translate("label.welcome"));
    }

    @Override
    public Node getNode() {
        return this.containerPane;
    }

    @Override
    public void update() {
        if (gameModel.isGameStarted() && !gameModel.isGameOver()) {
            this.userFeedbackBar.setText(translationController.translate("label.remainingMines") + ": " + gameModel.getRemainingMines());
        }
    }

    @Override
    public void newGameMessage() {
        this.userFeedbackBar.setText(translationController.translate("label.startString") + ": " + gameModel.getMineCount() + translationController.translate("label.mines"));
    }

    @Override
    public void flagUpdateMessage(int remainingMines) {
        this.userFeedbackBar.setText(translationController.translate("label.remainingMines") + ": " + remainingMines);
    }

    @Override
    public void gameOverMessage(String message) {
        this.userFeedbackBar.setText(message);
    }

    @Override
    public void display(String infos) {
        this.userFeedbackBar.setText(infos);
    }

}