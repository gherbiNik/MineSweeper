package ch.supsi.frontend.view;

import ch.supsi.backend.application.l10n.TranslationsApplication;
import ch.supsi.backend.application.l10n.TranslationsApplicationInterface;
import ch.supsi.frontend.controller.EventHandler;
import ch.supsi.backend.business.model.AbstractModel;
import ch.supsi.frontend.controller.GameEventHandler;
import ch.supsi.frontend.controller.gameMapperController.IGameMapperController;
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
    private IGameMapperController gameMapperController;
    private GameModel gameModel;
    private PreferenceView preferenceView;

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
    public void initialize(EventHandler eventHandler, AbstractModel model, IGameMapperController gameMapperController, ShowView preferenceView, TranslationsApplicationInterface translationsApplicationInterface) {
        this.changeLanguage(translationsApplicationInterface);
        this.createBehaviour();
        this.gameEventHandler = (GameEventHandler) eventHandler;
        this.gameMapperController = gameMapperController;
        this.gameModel = (GameModel) model;
        this.preferenceView = (PreferenceView) preferenceView;
    }

    //TODO IMPLEMENTARE IN MODO CORRETTO RISPETTANDO ARCHITETTURA
    private void changeLanguage(TranslationsApplicationInterface translationsApplicationInterface) {

        this.newMenuItem.setText(translationsApplicationInterface.translate("label.new"));
        this.saveAsMenuItem.setText(translationsApplicationInterface.translate("label.saveAs"));
        this.saveMenuItem.setText(translationsApplicationInterface.translate("label.save"));
        this.openMenuItem.setText(translationsApplicationInterface.translate("label.open"));
        this.quitMenuItem.setText(translationsApplicationInterface.translate("label.quit"));
        this.quitMenuItem.setText(translationsApplicationInterface.translate("label.quit"));
        this.preferencesMenuItem.setText(translationsApplicationInterface.translate("label.preferences"));
        this.editMenu.setText(translationsApplicationInterface.translate("label.edit"));
        this.helpMenu.setText(translationsApplicationInterface.translate("label.help"));
    }

    private void createBehaviour() {
        // new
        this.newMenuItem.setOnAction(event -> this.gameEventHandler.newGame());

        // save
        this.saveMenuItem.setOnAction(event -> this.gameMapperController.save(gameModel, "fileSalavataggio"));

        // add event handlers for all necessary menu items
        // Aggiungi qui gestori eventi per altre voci di menu

        // open
        this.openMenuItem.setOnAction(event -> { this.gameMapperController.open(gameModel, "fileSalavataggio"); ;});

        // quit
        this.quitMenuItem.setOnAction(
                event -> this.gameEventHandler.quit()
                // Implementare la logica per uscire dall'applicazione
                // Per esempio:
                // Platform.exit();
        );
        this.preferencesMenuItem.setOnAction(event -> {
            this.preferenceView.showView();
        });
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