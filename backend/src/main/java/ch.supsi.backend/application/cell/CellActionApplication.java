package ch.supsi.backend.application.cell;

import ch.supsi.backend.business.mine.MinePlacementStrategy;

public interface CellActionApplication {

    void toggleFlag(int row, int col);

    void revealCell(MinePlacementStrategy bombPlacer, int row, int col);
}
