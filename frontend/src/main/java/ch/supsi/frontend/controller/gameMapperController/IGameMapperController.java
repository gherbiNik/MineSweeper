package ch.supsi.frontend.controller.gameMapperController;

import ch.supsi.frontend.controller.EventHandler;

import java.io.File;

public interface IGameMapperController extends EventHandler {
    void save();

    void open(String fileName);

    void saveAs(File file);

}
