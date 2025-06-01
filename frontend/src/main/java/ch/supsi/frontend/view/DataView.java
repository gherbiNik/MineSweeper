package ch.supsi.frontend.view;

public interface DataView {
    void update();

    default void newGameMessage(){};

    default void flagUpdateMessage(int remainingMines){};

    default void gameOverMessage(String message){};
}
