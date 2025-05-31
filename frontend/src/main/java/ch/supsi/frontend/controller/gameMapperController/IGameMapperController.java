package ch.supsi.frontend.controller.gameMapperController;

import ch.supsi.frontend.controller.EventHandler;

public interface IGameMapperController extends EventHandler {
    void save(String fileName);

    void open(String fileName);

}
