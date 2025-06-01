package ch.supsi.backend.business.mine;

import ch.supsi.backend.application.cell.CellActionApplication;
import ch.supsi.backend.business.model.AbstractModel;

public interface CellAction extends CellActionApplication {
    void revealCell(MinePlacementStrategy bombPlacer, int row, int col);

    void revealAllMines();

    void toggleFlag(int row, int col);
}
