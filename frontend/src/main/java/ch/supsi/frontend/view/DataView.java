package ch.supsi.minesweeper.view;

public interface DataView {
    void update();
    void newGameMessage();
    void flagUpdateMessage(int remainingMines);
    void gameOverMessage(String message);
}
