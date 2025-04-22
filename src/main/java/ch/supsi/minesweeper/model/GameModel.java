package ch.supsi.minesweeper.model;

import java.util.Random;

public class GameModel extends AbstractModel implements GameEventHandler, PlayerEventHandler {

    private static GameModel myself;
    private Cell[][] board;
    private int mineCount;
    private int revealedCellCount;
    private int flaggedCellCount;
    private boolean gameStarted;
    private boolean gameOver;
    private boolean gameWon;
    private static final int DEFAULT_MINE_COUNT = 10;

    public static final int GRID_SIZE = 9;
    public static final int MAX_MINES = GRID_SIZE * GRID_SIZE - 1;
    public static final int MIN_MINES = 1;

    private GameModel() {
        this.mineCount = DEFAULT_MINE_COUNT;
        gameStarted = false;
        gameOver = false;
        gameWon = false;
    }

    public static GameModel getInstance() {
        if (myself == null) {
            myself = new GameModel();
        }
        return myself;
    }

    @Override
    public void newGame() {
        mineCount = Math.min(Math.max(mineCount, MIN_MINES), MAX_MINES);
        initializeBoard();
        gameStarted = false;
        gameOver = false;
        gameWon = false;
        flaggedCellCount = 0;
    }

    public void setMineCount(int mineCount) {
        this.mineCount = Math.min(Math.max(mineCount, MIN_MINES), MAX_MINES);
    }

    public int getMineCount() {
        return mineCount;
    }

    public int getFlaggedCount() {
        return flaggedCellCount;
    }

    public int getRemainingMines() {
        return mineCount - flaggedCellCount;
    }

    public boolean isGameStarted() {
        return gameStarted;
    }

    public boolean isGameOver() {
        return gameOver;
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

    public void placeMines() {
        // Posiziona le mine in modo casuale
        Random random = new Random();
        int minesPlaced = 0;

        while (minesPlaced < mineCount) {
            int row = random.nextInt(GRID_SIZE);
            int col = random.nextInt(GRID_SIZE);

            if (!board[row][col].isMine()) {
                board[row][col].setMine(true);
                minesPlaced++;
            }
        }

        // Calcola il numero di mine adiacenti per ogni cella
        calculateAdjacentMines();

        gameStarted = true;
    }

    private void calculateAdjacentMines() {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                if (!board[i][j].isMine()) {
                    int count = countAdjacentMines(i, j);
                    board[i][j].setAdjacentMines(count);
                }
            }
        }
    }

    private int countAdjacentMines(int row, int col) {
        int count = 0;

        // Controlla le 8 celle adiacenti
        for (int i = Math.max(0, row - 1); i <= Math.min(GRID_SIZE - 1, row + 1); i++) {
            for (int j = Math.max(0, col - 1); j <= Math.min(GRID_SIZE - 1, col + 1); j++) {
                if (!(i == row && j == col) && board[i][j].isMine()) {
                    count++;
                }
            }
        }

        return count;
    }

    public boolean revealCell(int row, int col) {
        Cell cell = board[row][col];

        // Se la cella è già rivelata o contrassegnata, non fare nulla
        if (cell.isRevealed() || cell.isFlagged() || gameOver) {
            return true;
        }

        // Se non abbiamo ancora posizionato le mine, fallo ora
        if (!gameStarted) {
            placeMines();
        }

        cell.setRevealed(true);
        revealedCellCount++;

        // Se è una mina, il gioco è perso
        if (cell.isMine()) {
            gameOver = true;
            revealAllMines();
            return false;
        }

        // Se non ci sono mine adiacenti, rivela automaticamente le celle adiacenti
        if (cell.getAdjacentMines() == 0) {
            for (int i = Math.max(0, row - 1); i <= Math.min(GRID_SIZE - 1, row + 1); i++) {
                for (int j = Math.max(0, col - 1); j <= Math.min(GRID_SIZE - 1, col + 1); j++) {
                    if (!(i == row && j == col)) {
                        revealCell(i, j);
                    }
                }
            }
        }

        // Controlla se il gioco è vinto
        checkWinCondition();

        return true;
    }

    private void revealAllMines() {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                if (board[i][j].isMine()) {
                    board[i][j].setRevealed(true);
                }
            }
        }
    }

    private void checkWinCondition() {
        // Il gioco è vinto se tutte le celle non mine sono rivelate
        if (revealedCellCount == (GRID_SIZE * GRID_SIZE - mineCount)) {
            gameWon = true;
            gameOver = true;
        }
    }

    public boolean toggleFlag(int row, int col) {
        if (!gameStarted || gameOver) {
            return false;
        }

        Cell cell = board[row][col];

        // Non consentire di contrassegnare celle già rivelate
        if (cell.isRevealed()) {
            return false;
        }

        // Gestisce il conteggio delle bandierine
        if (!cell.isFlagged() && flaggedCellCount >= mineCount) {
            // Non puoi inserire più bandierine del numero di mine
            return false;
        }

        // Toggle flag
        boolean wasFlagged = cell.isFlagged();
        cell.toggleFlag();

        // Aggiorna il conteggio delle bandierine
        if (cell.isFlagged() && !wasFlagged) {
            flaggedCellCount++;
        } else if (!cell.isFlagged() && wasFlagged) {
            flaggedCellCount--;
        }

        return true;
    }

    @Override
    public void save() {
        // Implementa il salvataggio del gioco
    }

    @Override
    public void move() {
        // Questo metodo dovrà essere implementato nella versione concreta
    }

    // Aggiungi un metodo per eseguire una mossa con coordinata specifica
    public void move(int row, int col, boolean isRightClick) {
        if (isRightClick) {
            toggleFlag(row, col);
        } else {
            revealCell(row, col);
        }
    }
}