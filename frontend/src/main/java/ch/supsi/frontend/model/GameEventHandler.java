package ch.supsi.frontend.model;

import ch.supsi.frontend.controller.EventHandler;

public interface GameEventHandler extends EventHandler {

    void newGame();

    void save();

    void quit();

    void open();

    // add all the relevant missing behaviours
    // ...
}
