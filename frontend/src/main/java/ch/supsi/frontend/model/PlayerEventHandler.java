package ch.supsi.frontend.model;

import ch.supsi.frontend.controller.EventHandler;

public interface PlayerEventHandler extends EventHandler {

    void move(int row, int col, boolean isRightClick);

    // add all the relevant missing behaviours
    // ...

}
