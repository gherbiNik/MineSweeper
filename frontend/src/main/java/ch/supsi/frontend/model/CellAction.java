package ch.supsi.minesweeper.model;

public interface CellAction {
    void revealCell(AbstractModel model, MinePlacementStrategy bombPlacer,int row, int col);
    void revealAllMines(AbstractModel model);
    void toggleFlag(AbstractModel model, int row, int col);
}
