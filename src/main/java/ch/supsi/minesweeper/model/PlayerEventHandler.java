package ch.supsi.minesweeper.model;

import ch.supsi.minesweeper.controller.EventHandler;

public interface PlayerEventHandler extends EventHandler {

    void move();
    void move(int row, int col, boolean isRightClick);

    // add all the relevant missing behaviours
    // ...

}
