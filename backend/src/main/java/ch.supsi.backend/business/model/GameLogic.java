package ch.supsi.backend.business.model;

import ch.supsi.backend.business.cell.Cell;
import ch.supsi.backend.business.cell.ICell;
import ch.supsi.backend.business.game.GameBoardInfo;
import ch.supsi.backend.business.game.GameBombInfo;
import ch.supsi.backend.business.mine.CellAction;
import ch.supsi.backend.business.mine.MinePlacementStrategy;
import ch.supsi.backend.business.preferences.PreferencesBusinessInterface;

public class GameLogic extends AbstractModel  {

    private static GameLogic myself;
    private ICell[][] board;

    private int revealedCellCount;
    private int flaggedCellCount;
    private boolean gameStarted;
    private boolean gameOver;
    private boolean gameWon;
    private int mineCount;

    private MinePlacementStrategy bombPlacer;
    private CellAction mineRevealer;
    private GameBombInfo gameBombBusiness;
    private GameBoardInfo gameBoardBusiness;
    private PreferencesBusinessInterface preferencesBusinessInterface;


    private GameLogic() {
    }

    public static GameLogic getInstance(MinePlacementStrategy bombPlacer, GameBoardInfo gameBoardBusiness, GameBombInfo gameBombBusiness, PreferencesBusinessInterface preferencesBusinessInterface) {
        if (myself==null) {
            myself = new GameLogic();
            myself.initialize(bombPlacer, gameBoardBusiness, gameBombBusiness, preferencesBusinessInterface);
        }
        return myself;
    }

    private void initialize (MinePlacementStrategy bombPlacer,GameBoardInfo gameBoardBusiness, GameBombInfo gameBombBusiness, PreferencesBusinessInterface preferencesBusinessInterface) {
        this.bombPlacer = bombPlacer;
        this.mineRevealer = mineRevealer;
        this.gameBoardBusiness = gameBoardBusiness;
        this.gameBombBusiness = gameBombBusiness;
        this.preferencesBusinessInterface = preferencesBusinessInterface;
        Object obj = preferencesBusinessInterface.getPreference("bomb-number");
        if (obj != null) {
            String valueStr = obj.toString(); // o (String) obj se sei sicuro che sia String
            this.mineCount = Integer.parseInt(valueStr);
        }
    }

    public void setMineRevealer(CellAction mineRevealer) {
        this.mineRevealer = mineRevealer;
    }

    @Override
    public  void initializeBoard(){
        board = new ICell[gameBoardBusiness.getSize()][gameBoardBusiness.getSize()];
        for (int i = 0; i < gameBoardBusiness.getSize(); i++) {
            for (int j = 0; j < gameBoardBusiness.getSize(); j++) {
                board[i][j] = new Cell(i, j);
            }
        }

        revealedCellCount = 0;
        flaggedCellCount = 0;
    }

    @Override
    public void setRevealedCellCount(int revealedCellCount) {
        this.revealedCellCount = revealedCellCount;
    }

    @Override
    public void checkWinCondition() {
        if (revealedCellCount == (gameBoardBusiness.getSize() * gameBoardBusiness.getSize() - mineCount)) {
            gameWon = true;
            gameOver = true;
        }
    }

    @Override
    public void setFlaggedCellCount(int flaggedCellCount) {
        this.flaggedCellCount = flaggedCellCount;
    }

    @Override
    public int getFlaggedCellCount() {
        return flaggedCellCount;
    }

    @Override
    public ICell[][] getBoard() {
        return board;
    }

    @Override
    public ICell getCell(int row, int col) {
        if (row >= 0 && row < gameBoardBusiness.getSize() && col >= 0 && col < gameBoardBusiness.getSize()) {
            return board[row][col];
        }
        return null;
    }



    @Override
    public void setBoard(ICell[][] board) {
        this.board = board;
    }

    @Override
    public boolean isGameOver() {
        return gameOver;
    }

    @Override
    public boolean isGameWon() {
        return gameWon;
    }

    @Override
    public boolean isGameStarted() {
        return gameStarted;
    }

    @Override
    public void setGameStarted(boolean gameStarted) {
        this.gameStarted = gameStarted;
    }

    @Override
    public void setGameWon(boolean gameWon) {
        this.gameWon = gameWon;
    }

    @Override
    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    @Override
    public int getMineCount() {
        return mineCount;
    }

    @Override
    public int getRemainingMines() {
        return mineCount - flaggedCellCount;
    }

    @Override
    public int getRevealedCellCount() {
        return revealedCellCount;
    }

    @Override
    public GameBombInfo getGameBombBusiness() {
        return this.gameBombBusiness;
    }

    @Override
    public GameBoardInfo getGameBoardBusiness() {
        return this.gameBoardBusiness;
    }

    @Override
    public void move(int row, int col, boolean isRightClick) {
        if (isRightClick) {
            mineRevealer.toggleFlag(row, col);
        } else {
            mineRevealer.revealCell(bombPlacer, row, col);
        }
    }

    @Override
    public void newGame() {
        initializeBoard();
        gameStarted = true;
        gameOver = false;
        gameWon = false;
        flaggedCellCount = 0;
        bombPlacer.placeMines(myself, mineCount);
    }

}