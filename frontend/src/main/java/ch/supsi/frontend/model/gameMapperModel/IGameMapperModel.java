package ch.supsi.frontend.model.gameMapperModel;

import ch.supsi.backend.business.dto.GameStateBusiness;
import ch.supsi.backend.business.model.AbstractModel;

public interface IGameMapperModel {
    void save(AbstractModel model, String fileName);

    void open(AbstractModel gameModel, String fileName);
}
