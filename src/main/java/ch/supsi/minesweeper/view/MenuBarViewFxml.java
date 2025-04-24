package ch.supsi.minesweeper.view;

import ch.supsi.minesweeper.controller.EventHandler;
import ch.supsi.minesweeper.model.AbstractModel;
import ch.supsi.minesweeper.model.GameEventHandler;
import ch.supsi.minesweeper.model.GameModel;
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

    private MenuBarViewFxml() {}

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
        this.createBehaviour();
        this.gameEventHandler = (GameEventHandler) eventHandler;
        this.gameModel = (GameModel) model;
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
        // get your data from the model, if needed
        // then update this view here
        System.out.println(this.getClass().getSimpleName() + " updated..." + System.currentTimeMillis());

        // Aggiorna lo stato dei menu in base allo stato del gioco
        boolean gameStarted = gameModel.isGameStarted();
        boolean gameOver = gameModel.isGameOver();

        // Esempio: abilita/disabilita elementi del menu in base allo stato del gioco
        saveMenuItem.setDisable(!gameStarted || gameOver);
        saveAsMenuItem.setDisable(!gameStarted || gameOver);
    }

    @Override
    public void newGameMessage() {
        System.out.println("New Game Created" + System.currentTimeMillis());

        // Abilita/disabilita elementi del menu quando viene creato un nuovo gioco
        saveMenuItem.setDisable(false);
        saveAsMenuItem.setDisable(false);
    }

    @Override
    public void flagUpdateMessage(int remainingMines) {
        // Nel caso del MenuBar, potremmo non dover fare nulla di specifico
        // quando viene aggiornato il conteggio delle bandierine,
        // ma implementiamo comunque il metodo come richiesto dall'interfaccia
        System.out.println("MenuBar: Flag update - Remaining mines: " + remainingMines);

        // Se vogliamo, possiamo aggiornare qualche elemento del menu
        // ad esempio, aggiungere il numero di mine rimanenti nel titolo di un menu
        // fileMenu.setText("File (" + remainingMines + " mines left)");
    }

    @Override
    public void gameOverMessage(String message) {
        System.out.println("MenuBar: Game over - " + message);

        // Quando il gioco è finito, possiamo aggiornare lo stato dei menu
        // Ad esempio, disabilitare il salvataggio ma abilitare nuovo gioco
        saveMenuItem.setDisable(true);
        saveAsMenuItem.setDisable(true);

        // Potremmo anche visualizzare un messaggio o cambiare qualche elemento visivo
        // ad esempio, cambiare il titolo di un menu o il colore di sfondo
        // fileMenu.setStyle("-fx-background-color: " + (message.contains("vinto") ? "lightgreen" : "lightpink") + ";");
    }
}