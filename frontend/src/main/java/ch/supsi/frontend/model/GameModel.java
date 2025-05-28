package ch.supsi.frontend.model;

import ch.supsi.backend.application.cell.CellActionApplication;
import ch.supsi.backend.application.game.GameBoardApplication;
import ch.supsi.backend.application.game.GameBombApplication;
import ch.supsi.backend.business.cell.Cell;
import ch.supsi.backend.business.PropertiesController;
import ch.supsi.backend.business.mine.MinePlacementStrategy;
import ch.supsi.backend.business.model.AbstractModel;
import ch.supsi.frontend.controller.GameEventHandler;
import ch.supsi.frontend.controller.PlayerEventHandler;
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


    private MinePlacementStrategy bombPlacer;
    private CellActionApplication mineRevealer;
    private GameBombApplication gameBombApplication;
    private GameBoardApplication gameBoardApplication;



    private GameModel(MinePlacementStrategy bombPlacer, CellActionApplication mineRevealer, GameBombApplication gameBombApplication, GameBoardApplication gameBoardApplication) {
        try {
            values = PropertiesController.readFileProperties();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        int tmp = Integer.parseInt(Objects.requireNonNull(values)[0]);

        this.gameBoardApplication = gameBoardApplication;
        this.gameBombApplication = gameBombApplication;
        this.bombPlacer = bombPlacer;
        this.mineRevealer = mineRevealer;

        this.mineCount = tmp > gameBombApplication.getMaxBomb() || tmp < gameBombApplication.getMinBomb() ? DEFAULT_MINE_COUNT : tmp;
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


    public static GameModel getInstance(MinePlacementStrategy bombPlacer, CellActionApplication mineRevealer, GameBombApplication gameBombApplication, GameBoardApplication gameBoardApplication) {
        if (myself == null) {
            myself = new GameModel(bombPlacer, mineRevealer, gameBombApplication, gameBoardApplication);

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
        if (row >= 0 && row < gameBoardApplication.getSize() && col >= 0 && col < gameBoardApplication.getSize()) {
            return board[row][col];
        }
        return null;
    }

    private void initializeBoard() {
        board = new Cell[gameBoardApplication.getSize()][gameBoardApplication.getSize()];
        for (int i = 0; i < gameBoardApplication.getSize(); i++) {
            for (int j = 0; j < gameBoardApplication.getSize(); j++) {
                board[i][j] = new Cell(i, j);
            }
        }

        revealedCellCount = 0;
        flaggedCellCount = 0;
    }

    @Override
    public void checkWinCondition() {
        // Il gioco è vinto se tutte le celle non mine sono rivelate
        if (revealedCellCount == (gameBoardApplication.getSize() * gameBoardApplication.getSize() - mineCount)) {
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
            mineRevealer.toggleFlag(this, row, col);
        } else {
            mineRevealer.revealCell(this, bombPlacer, row, col);
        }
    }
}