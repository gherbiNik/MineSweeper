package ch.supsi.frontend.view;

public interface DataView {
    void update();

    void newGameMessage();

    void flagUpdateMessage(int remainingMines);

    void gameOverMessage(String message);
}
