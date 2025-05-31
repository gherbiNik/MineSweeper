package ch.supsi.frontend.view;

import ch.supsi.backend.business.model.AbstractModel;
import ch.supsi.frontend.controller.EventHandler;
import ch.supsi.frontend.controller.gameMapperController.IGameMapperController;
import ch.supsi.frontend.controller.gameMapperController.InfoController;

public interface InfoView {

    void display(String infos);

}
