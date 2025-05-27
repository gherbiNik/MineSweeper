package ch.supsi.minesweeper.model;

public interface MinePlacementStrategy {
    void placeMines(AbstractModel model, int mineCount);

}
