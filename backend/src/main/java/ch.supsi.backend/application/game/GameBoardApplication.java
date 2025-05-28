package ch.supsi.backend.application.game;

public class GameBoardApplication implements GameBoardInfo {
    private int size;

    @Override
    public void setDimensions(int size) {
        this.size = size;
    }

    @Override
    public int getSize() {
        return size;
    }
}
