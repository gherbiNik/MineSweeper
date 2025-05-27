package ch.supsi.frontend.view;

import ch.supsi.frontend.controller.EventHandler;
import ch.supsi.frontend.model.AbstractModel;

public interface ControlledView extends DataView {

    void initialize(EventHandler eventHandler, AbstractModel model);

}
