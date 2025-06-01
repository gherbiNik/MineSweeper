package ch.supsi.backend.application.cell;

import ch.supsi.backend.business.mine.MinePlacementStrategy;
import ch.supsi.backend.business.model.AbstractModel;

public interface CellActionApplication {

    void toggleFlag(int row, int col);

    void revealCell(MinePlacementStrategy bombPlacer, int row, int col);
}
