package ch.supsi.frontend.controller;

import ch.supsi.backend.application.dto.CellStateDTO;
import ch.supsi.backend.application.dto.GameStateDTO;
import ch.supsi.backend.business.cell.Cell;
import ch.supsi.frontend.model.GameModel;

public class GameStateMapper {

    public static GameStateDTO toDTO(GameModel gameModel) {
        Cell[][] board = gameModel.getBoard();
        int size = board.length;

        CellStateDTO[][] cellDTOs = new CellStateDTO[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Cell cell = board[i][j];
                cellDTOs[i][j] = new CellStateDTO(
                        cell.getRow(),
                        cell.getCol(),
                        cell.isMine(),
                        cell.isRevealed(),
                        cell.isFlagged(),
                        cell.getAdjacentMines()
                );
            }
        }

        return new GameStateDTO(
                cellDTOs,
                gameModel.getMineCount(),
                gameModel.getRevealedCellCount(),
                gameModel.getFlaggedCellCount(),
                gameModel.isGameStarted(),
                gameModel.isGameOver(),
                gameModel.isGameWon(),
                gameModel.getGameBoardApplication().getSize(),
                gameModel.getGameBombApplication().getMaxBomb(),
                gameModel.getGameBombApplication().getMinBomb(),
                System.currentTimeMillis()
        );
    }

    public static void fromDTO(GameStateDTO dto, GameModel gameModel) {
        CellStateDTO[][] cellDTOs = dto.getCells();
        int size = cellDTOs.length;
        Cell[][] board = new Cell[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                CellStateDTO cellDTO = cellDTOs[i][j];
                Cell cell = new Cell(cellDTO.getRow(), cellDTO.getCol());

                cell.setMine(cellDTO.isMine());
                cell.setRevealed(cellDTO.isRevealed());
                cell.setFlagged(cellDTO.isFlagged());
                cell.setAdjacentMines(cellDTO.getAdjacentMines());

                board[i][j] = cell;
            }
        }

        gameModel.setBoard(board);
        gameModel.setRevealedCellCount(dto.getRevealedCellCount());
        gameModel.setFlaggedCellCount(dto.getFlaggedCellCount());
        gameModel.setGameStarted(dto.isGameStarted());
        gameModel.setGameOver(dto.isGameOver());
        gameModel.setGameWon(dto.isGameWon());

        gameModel.getGameBoardApplication().setDimensions(dto.getBoardSize());
        gameModel.getGameBombApplication().setMaxBomb(dto.getMaxBomb());
        gameModel.getGameBombApplication().setMinBomb(dto.getMinBomb());
    }
}