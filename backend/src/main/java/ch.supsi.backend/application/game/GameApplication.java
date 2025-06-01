package ch.supsi.backend.application.game;

import ch.supsi.backend.business.cell.ICell;
import ch.supsi.backend.business.model.AbstractModel;

public class GameApplication implements  GameApplicationInterface {
    private static GameApplication instance;
    private AbstractModel gameLogic;

    public static GameApplication getInstance(AbstractModel gameLogic) {
        if (instance == null) {
            instance = new GameApplication();
            instance.initialize(gameLogic);
        }
        return instance;
    }
    private GameApplication() {}

    private void initialize(AbstractModel gameLogic) {
        this.gameLogic = gameLogic;
    }


    @Override
    public void newGame() {
        this.gameLogic.newGame();
    }

    @Override
    public void move(int row, int col, boolean isRightClick) {
        this.gameLogic.move(row, col, isRightClick);
    }

    @Override
    public void setRevealedCellCount(int revealedCellCount) {
        this.gameLogic.setRevealedCellCount(revealedCellCount);
    }

    @Override
    public void checkWinCondition() {
        this.gameLogic.checkWinCondition();
    }

    @Override
    public void setFlaggedCellCount(int flaggedCellCount) {
        this.gameLogic.setFlaggedCellCount(flaggedCellCount);
    }

    @Override
    public int getFlaggedCellCount() {
        return this.gameLogic.getFlaggedCellCount();
    }

    @Override
    public void initializeBoard() {
        this.gameLogic.initializeBoard();
    }

    @Override
    public ICell[][] getBoard() {
        return this.gameLogic.getBoard();
    }

    @Override
    public ICell getCell(int x, int y) {
        return this.gameLogic.getCell(x, y);
    }

    @Override
    public void setBoard(ICell[][] board) {
        this.gameLogic.setBoard(board);
    }

    @Override
    public int getMineCount() {
        return this.gameLogic.getMineCount();
    }

    @Override
    public int getRemainingMines() {
        return this.gameLogic.getRemainingMines();
    }

    @Override
    public int getRevealedCellCount() {
        return this.gameLogic.getRevealedCellCount();
    }


    @Override
    public boolean isGameOver() {
        return gameLogic.isGameOver();
    }

    @Override
    public boolean isGameWon() {
        return gameLogic.isGameWon();
    }

    @Override
    public boolean isGameStarted() {
        return gameLogic.isGameStarted();
    }

    @Override
    public void setGameStarted(boolean b) {
        gameLogic.setGameStarted(b);
    }

    @Override
    public void setGameWon(boolean gameWon) {
        gameLogic.setGameWon(gameWon);
    }

    @Override
    public void setGameOver(boolean gameOver) {
        gameLogic.setGameOver(gameOver);
    }
}
