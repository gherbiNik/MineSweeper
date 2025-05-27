package ch.supsi.frontend.model;

import ch.supsi.backend.data.Cell;

import static ch.supsi.frontend.model.GameModel.GRID_SIZE;

public class MineRevealer implements CellAction {

    public void revealCell(AbstractModel model, MinePlacementStrategy bombPlacer, int row, int col) {
        Cell cell = model.getBoard()[row][col];

        // Se la cella è già rivelata o contrassegnata, non fare nulla
        if (cell.isRevealed() || cell.isFlagged() || model.isGameOver()) {
            return;
        }

        // Se non abbiamo ancora posizionato le mine, fallo ora
        if (!model.isGameStarted()) {
            bombPlacer.placeMines(model, model.getMineCount());
        }

        cell.setRevealed(true);
        model.setRevealedCellCount(model.getRevealedCellCount() + 1);

        // Se è una mina, il gioco è perso
        if (cell.isMine()) {
            model.setGameOver(true);
            revealAllMines(model);
            return;
        }

        // Se non ci sono mine adiacenti, rivela automaticamente le celle adiacenti
        if (cell.getAdjacentMines() == 0) {
            for (int i = Math.max(0, row - 1); i <= Math.min(GRID_SIZE - 1, row + 1); i++) {
                for (int j = Math.max(0, col - 1); j <= Math.min(GRID_SIZE - 1, col + 1); j++) {
                    if (!(i == row && j == col)) {
                        revealCell(model, bombPlacer, i, j);
                    }
                }
            }
        }

        // Controlla se il gioco è vinto
        model.checkWinCondition();
    }

    public void revealAllMines(AbstractModel model) {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                if (model.getBoard()[i][j].isMine()) {
                    model.getBoard()[i][j].setRevealed(true);
                }
            }
        }
    }

    public void toggleFlag(AbstractModel model, int row, int col) {
        if (!model.isGameStarted() || model.isGameOver()) {
            return;
        }

        Cell cell = model.getBoard()[row][col];

        // Non consentire di contrassegnare celle già rivelate
        if (cell.isRevealed()) {
            return;
        }

        // Gestisce il conteggio delle bandierine
        if (!cell.isFlagged() && model.getFlaggedCellCount() >= model.getMineCount()) {
            // Non puoi inserire più bandierine del numero di mine
            return;
        }

        // Toggle flag
        boolean wasFlagged = cell.isFlagged();
        cell.toggleFlag();

        // Aggiorna il conteggio delle bandierine
        if (cell.isFlagged() && !wasFlagged) {
            model.setFlaggedCellCount(model.getFlaggedCellCount() + 1);
        } else if (!cell.isFlagged() && wasFlagged) {
            model.setFlaggedCellCount(model.getFlaggedCellCount() - 1);
        }

    }
}
