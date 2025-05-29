package ch.supsi.frontend.view;

import ch.supsi.backend.application.l10n.TranslationsController;
import ch.supsi.frontend.controller.EventHandler;
import ch.supsi.backend.business.model.AbstractModel;
import ch.supsi.frontend.controller.GameEventHandler;
import ch.supsi.frontend.model.GameModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

import java.io.IOException;
import java.net.URL;

public class MenuBarViewFxml implements ControlledFxView {

    private static MenuBarViewFxml myself;

    private GameEventHandler gameEventHandler;
    private GameModel gameModel;

    @FXML
    private MenuBar menuBar;

    @FXML
    private Menu fileMenu;
    @FXML
    private Menu editMenu;
    @FXML
    private Menu helpMenu;

    @FXML
    private MenuItem newMenuItem;
    @FXML
    private MenuItem openMenuItem;
    @FXML
    private MenuItem saveMenuItem;
    @FXML
    private MenuItem saveAsMenuItem;
    @FXML
    private MenuItem quitMenuItem;
    @FXML
    private MenuItem preferencesMenuItem;
    @FXML
    private MenuItem aboutMenuItem;
    @FXML
    private MenuItem helpMenuItem;

    private MenuBarViewFxml() {
    }

    public static MenuBarViewFxml getInstance() {
        if (myself == null) {
            myself = new MenuBarViewFxml();

            try {
                URL fxmlUrl = MenuBarViewFxml.class.getResource("/menubar.fxml");
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
    public void initialize(EventHandler eventHandler, AbstractModel model) {
        this.changeLanguage();
        this.createBehaviour();
        this.gameEventHandler = (GameEventHandler) eventHandler;
        this.gameModel = (GameModel) model;
    }

    //TODO chiedere agli altri se va bene mettere cosi il translation controller
    private void changeLanguage() {
        TranslationsController translationsController = new TranslationsController();
        this.newMenuItem.setText(translationsController.translate("label.new"));
        this.saveAsMenuItem.setText(translationsController.translate("label.saveAs"));
        this.saveMenuItem.setText(translationsController.translate("label.save"));
        this.openMenuItem.setText(translationsController.translate("label.open"));
        this.quitMenuItem.setText(translationsController.translate("label.quit"));
        this.quitMenuItem.setText(translationsController.translate("label.quit"));
        this.preferencesMenuItem.setText(translationsController.translate("label.preferences"));
        this.editMenu.setText(translationsController.translate("label.edit"));
        this.helpMenu.setText(translationsController.translate("label.help"));
    }

    private void createBehaviour() {
        // new
        this.newMenuItem.setOnAction(event -> this.gameEventHandler.newGame());

        // save
        this.saveMenuItem.setOnAction(event -> this.gameEventHandler.save());

        // add event handlers for all necessary menu items
        // Aggiungi qui gestori eventi per altre voci di menu

        // open
        this.openMenuItem.setOnAction(event -> {
            // Implementare la logica per aprire un gioco salvato
            // Per esempio:
            // this.gameEventHandler.openGame();
        });

        // quit
        this.quitMenuItem.setOnAction(
                event -> this.gameEventHandler.quit()
                // Implementare la logica per uscire dall'applicazione
                // Per esempio:
                // Platform.exit();
        );
    }

    @Override
    public Node getNode() {
        return this.menuBar;
    }

    @Override
    public void update() {
        // Aggiorna lo stato dei menu in base allo stato del gioco
        boolean gameStarted = gameModel.isGameStarted();
        boolean gameOver = gameModel.isGameOver();

        // Esempio: abilita/disabilita elementi del menu in base allo stato del gioco
        saveMenuItem.setDisable(!gameStarted || gameOver);
        saveAsMenuItem.setDisable(!gameStarted || gameOver);
    }

    @Override
    public void newGameMessage() {
        // Abilita/disabilita elementi del menu quando viene creato un nuovo gioco
        saveMenuItem.setDisable(false);
        saveAsMenuItem.setDisable(false);
    }

    @Override
    public void flagUpdateMessage(int remainingMines) {
    }

    @Override
    public void gameOverMessage(String message) {
    }
}