package ch.supsi.backend.business.mine;

import ch.supsi.backend.business.model.AbstractModel;

public interface MinePlacementStrategy {
    void placeMines(AbstractModel model, int mineCount);

}
