package ch.supsi.frontend.view;

import ch.supsi.frontend.controller.EventHandler;
import ch.supsi.backend.business.model.AbstractModel;
import ch.supsi.frontend.controller.gameMapperController.IGameMapperController;
import ch.supsi.frontend.controller.gameMapperController.IInfoController;
import ch.supsi.frontend.controller.gameMapperController.InfoController;

public interface ControlledView extends DataView {

    void initialize(EventHandler eventHandler, AbstractModel model, IGameMapperController gameMapperController);

}
