package ch.supsi.minesweeper.view;

import ch.supsi.minesweeper.model.AbstractModel;
import ch.supsi.minesweeper.model.GameModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;

public class WelcomeViewFxml implements UncontrolledFxView{
    private static WelcomeViewFxml myself;

    @FXML
    private Label label;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private VBox vBox;

    private GameModel gameModel;

    @Override
    public Node getNode() {
        return this.anchorPane;
    }

    @Override
    public void initialize(AbstractModel model) {
        this.gameModel = (GameModel) model;
        update();
    }

    public static WelcomeViewFxml getInstance() {
        if (myself == null) {
            myself = new WelcomeViewFxml();

            try {
                URL fxmlUrl = WelcomeViewFxml.class.getResource("/welcome.fxml");
                if (fxmlUrl != null) {
                    FXMLLoader fxmlLoader = new FXMLLoader(fxmlUrl);
                    fxmlLoader.setController(myself);
                    fxmlLoader.load();
                } else {
                    System.err.println("Could not find welcome.fxml resource");
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return myself;
    }

    @Override
    public void update() {
        if (label != null) {
            label.setText("Welcome to Mine Sweeper :)");
        } else {
            System.err.println("Label not initialized in WelcomeViewFxml");
        }
    }

    @Override
    public void newGameMessage() {

    }

    @Override
    public void flagUpdateMessage(int remainingMines) {}

    @Override
    public void gameOverMessage(String message) {}
}