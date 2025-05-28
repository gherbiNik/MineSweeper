package ch.supsi.backend.business.mine;

import ch.supsi.backend.application.cell.CellActionApplication;
import ch.supsi.backend.business.model.AbstractModel;

public interface CellAction extends CellActionApplication {
    void revealCell(AbstractModel model, MinePlacementStrategy bombPlacer, int row, int col);

    void revealAllMines(AbstractModel model);

    void toggleFlag(AbstractModel model, int row, int col);
}
