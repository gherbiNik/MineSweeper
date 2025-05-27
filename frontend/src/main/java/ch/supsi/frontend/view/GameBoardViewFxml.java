package ch.supsi.frontend.view;

import ch.supsi.backend.data.Cell;
import ch.supsi.frontend.controller.EventHandler;
import ch.supsi.frontend.model.AbstractModel;

import ch.supsi.frontend.model.GameModel;
import ch.supsi.frontend.model.PlayerEventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;

public class GameBoardViewFxml implements ControlledFxView {

    private static GameBoardViewFxml myself;
    private PlayerEventHandler playerEventHandler;
    private GameModel gameModel;
    private Button[][] cells;

    @FXML
    private GridPane containerPane;

    @FXML
    private Button cell00, cell01, cell02, cell03, cell04, cell05, cell06, cell07, cell08;
    @FXML
    private Button cell10, cell11, cell12, cell13, cell14, cell15, cell16, cell17, cell18;
    @FXML
    private Button cell20, cell21, cell22, cell23, cell24, cell25, cell26, cell27, cell28;
    @FXML
    private Button cell30, cell31, cell32, cell33, cell34, cell35, cell36, cell37, cell38;
    @FXML
    private Button cell40, cell41, cell42, cell43, cell44, cell45, cell46, cell47, cell48;
    @FXML
    private Button cell50, cell51, cell52, cell53, cell54, cell55, cell56, cell57, cell58;
    @FXML
    private Button cell60, cell61, cell62, cell63, cell64, cell65, cell66, cell67, cell68;
    @FXML
    private Button cell70, cell71, cell72, cell73, cell74, cell75, cell76, cell77, cell78;
    @FXML
    private Button cell80, cell81, cell82, cell83, cell84, cell85, cell86, cell87, cell88;

    private GameBoardViewFxml() {
        cells = new Button[9][9];
    }

    public static GameBoardViewFxml getInstance() {
        if (myself == null) {
            myself = new GameBoardViewFxml();

            try {
                URL fxmlUrl = GameBoardViewFxml.class.getResource("/gameboard.fxml");
                if (fxmlUrl != null) {
                    FXMLLoader fxmlLoader = new FXMLLoader(fxmlUrl);
                    fxmlLoader.setController(myself);
                    fxmlLoader.load();
                }

                myself.populateCellsMatrix();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return myself;
    }

    private void populateCellsMatrix() {
        cells[0][0] = cell00;
        cells[0][1] = cell01;
        cells[0][2] = cell02;
        cells[0][3] = cell03;
        cells[0][4] = cell04;
        cells[0][5] = cell05;
        cells[0][6] = cell06;
        cells[0][7] = cell07;
        cells[0][8] = cell08;

        cells[1][0] = cell10;
        cells[1][1] = cell11;
        cells[1][2] = cell12;
        cells[1][3] = cell13;
        cells[1][4] = cell14;
        cells[1][5] = cell15;
        cells[1][6] = cell16;
        cells[1][7] = cell17;
        cells[1][8] = cell18;

        cells[2][0] = cell20;
        cells[2][1] = cell21;
        cells[2][2] = cell22;
        cells[2][3] = cell23;
        cells[2][4] = cell24;
        cells[2][5] = cell25;
        cells[2][6] = cell26;
        cells[2][7] = cell27;
        cells[2][8] = cell28;

        cells[3][0] = cell30;
        cells[3][1] = cell31;
        cells[3][2] = cell32;
        cells[3][3] = cell33;
        cells[3][4] = cell34;
        cells[3][5] = cell35;
        cells[3][6] = cell36;
        cells[3][7] = cell37;
        cells[3][8] = cell38;

        cells[4][0] = cell40;
        cells[4][1] = cell41;
        cells[4][2] = cell42;
        cells[4][3] = cell43;
        cells[4][4] = cell44;
        cells[4][5] = cell45;
        cells[4][6] = cell46;
        cells[4][7] = cell47;
        cells[4][8] = cell48;

        cells[5][0] = cell50;
        cells[5][1] = cell51;
        cells[5][2] = cell52;
        cells[5][3] = cell53;
        cells[5][4] = cell54;
        cells[5][5] = cell55;
        cells[5][6] = cell56;
        cells[5][7] = cell57;
        cells[5][8] = cell58;

        cells[6][0] = cell60;
        cells[6][1] = cell61;
        cells[6][2] = cell62;
        cells[6][3] = cell63;
        cells[6][4] = cell64;
        cells[6][5] = cell65;
        cells[6][6] = cell66;
        cells[6][7] = cell67;
        cells[6][8] = cell68;

        cells[7][0] = cell70;
        cells[7][1] = cell71;
        cells[7][2] = cell72;
        cells[7][3] = cell73;
        cells[7][4] = cell74;
        cells[7][5] = cell75;
        cells[7][6] = cell76;
        cells[7][7] = cell77;
        cells[7][8] = cell78;

        cells[8][0] = cell80;
        cells[8][1] = cell81;
        cells[8][2] = cell82;
        cells[8][3] = cell83;
        cells[8][4] = cell84;
        cells[8][5] = cell85;
        cells[8][6] = cell86;
        cells[8][7] = cell87;
        cells[8][8] = cell88;
    }

    @Override
    public void initialize(EventHandler eventHandler, AbstractModel model) {
        this.playerEventHandler = (PlayerEventHandler) eventHandler;
        this.gameModel = (GameModel) model;
    }

    private void createBehaviour() {
        for (int i = 0; i < GameModel.GRID_SIZE; i++) {
            for (int j = 0; j < GameModel.GRID_SIZE; j++) {
                final int row = i;
                final int col = j;

                this.cells[i][j].setOnMouseClicked(event -> {
                    boolean isRightClick = event.getButton() == MouseButton.SECONDARY;
                    this.playerEventHandler.move(row, col, isRightClick);
                });
            }
        }
    }

    @Override
    public Node getNode() {
        return this.containerPane;
    }

    @Override
    public void update() {

        // Aggiorna la visualizzazione della griglia in base allo stato del modello
        for (int i = 0; i < GameModel.GRID_SIZE; i++) {
            for (int j = 0; j < GameModel.GRID_SIZE; j++) {
                updateCellView(i, j);
            }
        }
    }

    private void updateCellView(int row, int col) {
        Button button = cells[row][col];
        Cell cell = gameModel.getCell(row, col);

        if (cell == null) return;

        if (cell.isRevealed()) {
            if (cell.isMine()) {
                button.setText("💣");
                button.setStyle("-fx-background-color: #FF8888;");
            } else {
                int mines = cell.getAdjacentMines();
                if (mines > 0) {
                    button.setText(String.valueOf(mines));

                    // Colori diversi per numeri diversi
                    switch (mines) {
                        case 1:
                            button.setStyle("-fx-text-fill: blue;");
                            break;
                        case 2:
                            button.setStyle("-fx-text-fill: green;");
                            break;
                        case 3:
                            button.setStyle("-fx-text-fill: red;");
                            break;
                        case 4:
                            button.setStyle("-fx-text-fill: purple;");
                            break;
                        default:
                            button.setStyle("-fx-text-fill: black;");
                            break;
                    }
                } else {
                    button.setText("");
                }
                button.setStyle(button.getStyle() + " -fx-background-color: #DDDDDD;");
            }
        } else if (cell.isFlagged()) {
            button.setText("🚩");
            button.setStyle("-fx-background-color: #FFFFFF;");
        } else {
            button.setText("");
            button.setStyle("-fx-background-color: #BBBBBB;");
        }
    }

    @Override
    public void newGameMessage() {
        activateCell();
        createBehaviour();
        update();
    }

    private void activateCell() {
        for (int i = 0; i < GameModel.GRID_SIZE; i++) {
            for (int j = 0; j < GameModel.GRID_SIZE; j++) {
                cells[i][j].setDisable(false);
            }
        }
    }

    @Override
    public void flagUpdateMessage(int remainingMines) {
        System.out.println("Remaining mines: " + remainingMines);
        update();
    }

    @Override
    public void gameOverMessage(String message) {
        System.out.println(message);
        update();
    }
}