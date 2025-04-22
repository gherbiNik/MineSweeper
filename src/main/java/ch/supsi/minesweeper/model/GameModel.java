package ch.supsi.minesweeper.model;

public class GameModel extends AbstractModel implements GameEventHandler, PlayerEventHandler{

    private static GameModel myself;
    private Cell[][] board;
    private int mineCount;
    private int revealedCellCount;
    private int flaggedCellCount;

    public static final int GRID_SIZE = 9;

    private GameModel() {
        super();
    }

    public static GameModel getInstance() {
        if (myself == null) {
            myself = new GameModel();
        }

        return myself;
    }

    @Override
    public void newGame() {

    }

    @Override
    public void save() {

    }

    @Override
    public void move() {
        return;
    }

    public GameModel(int mineCount) {
        this.mineCount = mineCount;
        initializeBoard();
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

    public void placeMines(int initialRow, int initialCol) {
        // Place mines randomly, avoiding the initial click
        // Implementation here
    }

    public boolean revealCell(int row, int col) {
        // Logic to reveal a cell
        // Return true if successful, false if mine
        // Implementation here
    }

    public void toggleFlag(int row, int col) {
        // Logic to flag/unflag a cell
        // Implementation here
    }


    // add all the relevant missing behaviours
    // ...

}
