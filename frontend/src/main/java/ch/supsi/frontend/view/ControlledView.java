package ch.supsi.frontend.view;

import ch.supsi.frontend.controller.EventHandler;
import ch.supsi.backend.business.model.AbstractModel;
import ch.supsi.frontend.controller.gameMapperController.IGameMapperController;

public interface ControlledView extends DataView {

    void initialize(EventHandler eventHandler, AbstractModel model, IGameMapperController gameMapperController);

}
