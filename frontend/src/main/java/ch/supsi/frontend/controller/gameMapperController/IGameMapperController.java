package ch.supsi.frontend.controller.gameMapperController;

import ch.supsi.backend.business.dto.GameStateBusiness;
import ch.supsi.backend.business.model.AbstractModel;
import ch.supsi.frontend.controller.EventHandler;

public interface IGameMapperController extends EventHandler {
    void save(AbstractModel model, String fileName);

    void open(AbstractModel model,String fileName);

}
