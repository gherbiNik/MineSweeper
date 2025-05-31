package ch.supsi.backend.business.mapper;

import ch.supsi.backend.business.cell.ICell;
import ch.supsi.backend.business.dto.CellStateBusiness;
import ch.supsi.backend.business.dto.GameStateBusiness;
import ch.supsi.backend.business.cell.Cell;
import ch.supsi.backend.business.dto.ICellStateBusiness;
import ch.supsi.backend.business.dto.IGameStateBusiness;
import ch.supsi.backend.business.model.AbstractModel;
import ch.supsi.backend.business.service.IGameSaveServiceBusiness;


public class GameStateMapper implements GameStateMapperBusiness{

    public static GameStateMapper myself;
    private IGameSaveServiceBusiness gameSaveServiceBusiness;
    private AbstractModel gameModel;


    private GameStateMapper() {
    }

    public static GameStateMapper getInstance(IGameSaveServiceBusiness gameSaveServiceBusiness, AbstractModel model) {
        if (myself == null) {
            myself = new GameStateMapper();
            myself.initialize(gameSaveServiceBusiness, model);
        }
        return myself;
    }

    private void initialize(IGameSaveServiceBusiness gameSaveServiceBusiness, AbstractModel model) {
        this.gameSaveServiceBusiness = gameSaveServiceBusiness;
        this.gameModel = model;
    }

    @Override
    public void toDTO(String filename) {
        ICell[][] board = gameModel.getBoard();
        int size = board.length;

        CellStateBusiness[][] cellStateBusinesses = new CellStateBusiness[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                ICell cell = board[i][j];
                cellStateBusinesses[i][j] = new CellStateBusiness(
                        cell.getRow(),
                        cell.getCol(),
                        cell.isMine(),
                        cell.isRevealed(),
                        cell.isFlagged(),
                        cell.getAdjacentMines()
                );
            }
        }

        GameStateBusiness t = new GameStateBusiness(
                cellStateBusinesses,
                gameModel.getMineCount(),
                gameModel.getRevealedCellCount(),
                gameModel.getFlaggedCellCount(),
                gameModel.isGameStarted(),
                gameModel.isGameOver(),
                gameModel.isGameWon(),
                gameModel.getGameBoardBusiness().getSize(),
                gameModel.getGameBombBusiness().getMaxBomb(),
                gameModel.getGameBombBusiness().getMinBomb(),
                System.currentTimeMillis()
        );
        gameSaveServiceBusiness.saveGame(t,filename);
    }

    @Override
    public void fromDTO(String filename) {
        IGameStateBusiness gameStateBusiness = gameSaveServiceBusiness.loadGame(filename);
            ICellStateBusiness[][] cellDTOs = gameStateBusiness.getCells();
        int size = cellDTOs.length;
        Cell[][] board = new Cell[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                ICellStateBusiness cellDTO = cellDTOs[i][j];
                Cell cell = new Cell(cellDTO.getRow(), cellDTO.getCol());

                cell.setMine(cellDTO.isMine());
                cell.setRevealed(cellDTO.isRevealed());
                cell.setFlagged(cellDTO.isFlagged());
                cell.setAdjacentMines(cellDTO.getAdjacentMines());

                board[i][j] = cell;
            }
        }

        gameModel.setBoard(board);
        gameModel.setRevealedCellCount(gameStateBusiness.getRevealedCellCount());
        gameModel.setFlaggedCellCount(gameStateBusiness.getFlaggedCellCount());
        gameModel.setGameStarted(gameStateBusiness.isGameStarted());
        gameModel.setGameOver(gameStateBusiness.isGameOver());
        gameModel.setGameWon(gameStateBusiness.isGameWon());

        gameModel.getGameBoardBusiness().setDimensions(gameStateBusiness.getBoardSize());
        gameModel.getGameBombBusiness().setMaxBomb(gameStateBusiness.getMaxBomb());
        gameModel.getGameBombBusiness().setMinBomb(gameStateBusiness.getMinBomb());
    }
}