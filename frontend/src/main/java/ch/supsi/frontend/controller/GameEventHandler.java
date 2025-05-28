package ch.supsi.frontend.controller;

public interface GameEventHandler extends EventHandler {

    void newGame();

    void save();

    void quit();

    void open();

    // add all the relevant missing behaviours
    // ...
}
