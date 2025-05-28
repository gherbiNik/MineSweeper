package ch.supsi.backend.business.model;

public interface GameState {
    boolean isGameOver();

    boolean isGameWon();

    boolean isGameStarted();

    void setGameStarted(boolean b);
}
