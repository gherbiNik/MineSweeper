package ch.supsi.backend.business.game;

public class GameBoardBusiness implements GameBoardInfo {
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
