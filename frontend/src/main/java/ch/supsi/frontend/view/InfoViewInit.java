package ch.supsi.frontend.view;

import ch.supsi.backend.business.model.AbstractModel;
import ch.supsi.frontend.controller.EventHandler;
import ch.supsi.frontend.controller.gameMapperController.IGameMapperController;
import ch.supsi.frontend.controller.gameMapperController.IInfoController;

public interface InfoViewInit {
    void initialize(EventHandler eventHandler, AbstractModel model, IGameMapperController gameMapperController, IInfoController infoController);
}
