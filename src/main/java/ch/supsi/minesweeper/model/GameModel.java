package ch.supsi.minesweeper.model;

import ch.supsi.minesweeper.controller.PropertiesController;
import javafx.application.Platform;

import java.io.IOException;
import java.util.*;

public class GameModel extends AbstractModel implements GameEventHandler, PlayerEventHandler {

    private static GameModel myself;
    private Cell[][] board;
    private String[] values;
    private final int mineCount;
    private int revealedCellCount;
    private int flaggedCellCount;
    private boolean gameStarted;
    private boolean gameOver;
    private boolean gameWon;
    public static final int DEFAULT_MINE_COUNT = 20;
    public static final int CLUSTER_DIM = 2;


    public static final int GRID_SIZE = 9;
    public static final int MAX_MINES = GRID_SIZE * GRID_SIZE - 1;
    public static final int MIN_MINES = 1;
    private MinePlacementStrategy bombPlacer;
    private CellAction mineRevealer;

    private GameModel() {

        try {
            values = PropertiesController.readFileProperties();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        int tmp = Integer.parseInt(Objects.requireNonNull(values)[0]);

        this.mineCount = tmp > MAX_MINES || tmp < MIN_MINES ? DEFAULT_MINE_COUNT : tmp;
        gameStarted = false;
        gameOver = false;
        gameWon = false;

    }

    public Cell[][] getBoard() {
        return board;
    }

    public void setGameStarted(boolean gameStarted) {
        this.gameStarted = gameStarted;
    }


    //TODO togliere i new e usare Dependency Injection
    public static GameModel getInstance() {
        if (myself == null) {
            myself = new GameModel();
            myself.bombPlacer = new BombPlacer();
            myself.mineRevealer = new MineRevealer();
        }
        return myself;
    }

    public int getMineCount() {
        return mineCount;
    }



    public int getRemainingMines() {
        return mineCount - flaggedCellCount;
    }

    public int getRevealedCellCount() {
        return revealedCellCount;
    }

    public boolean isGameStarted() {
        return gameStarted;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public int getFlaggedCellCount() {
        return flaggedCellCount;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public void setFlaggedCellCount(int flaggedCellCount) {
        this.flaggedCellCount = flaggedCellCount;
    }

    public void setRevealedCellCount(int revealedCellCount) {
        this.revealedCellCount = revealedCellCount;
    }

    public boolean isGameWon() {
        return gameWon;
    }

    public Cell getCell(int row, int col) {
        if (row >= 0 && row < GRID_SIZE && col >= 0 && col < GRID_SIZE) {
            return board[row][col];
        }
        return null;
    }

    private void initializeBoard() {
        board = new Cell[GRID_SIZE][GRID_SIZE];
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                board[i][j] = new Cell(i, j);
            }
        }

        revealedCellCount = 0;
        flaggedCellCount = 0;
    }


    public void checkWinCondition() {
        // Il gioco è vinto se tutte le celle non mine sono rivelate
        if (revealedCellCount == (GRID_SIZE * GRID_SIZE - mineCount)) {
            gameWon = true;
            gameOver = true;
        }
    }

    @Override
    public void newGame() {
        initializeBoard();
        gameStarted = false;
        gameOver = false;
        gameWon = false;
        flaggedCellCount = 0;
    }

    @Override
    public void save() {
        // Implementa il salvataggio del gioco
    }

    @Override
    public void quit() {
        //Chiedere al giocatore se salvare o no
        gameStarted = false;
        gameOver = false;
        gameWon = false;
        //TODO SISTEMARE METTENDO POPUP
        Platform.exit();
    }

    @Override
    public void open() {

    }

    @Override
    public void move(int row, int col, boolean isRightClick) {
        if (isRightClick) {
            mineRevealer.toggleFlag(this ,row, col);
        } else {
            mineRevealer.revealCell(this, bombPlacer, row, col);
        }
    }
}