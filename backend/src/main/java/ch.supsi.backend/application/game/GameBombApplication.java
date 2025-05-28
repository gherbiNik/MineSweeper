package ch.supsi.backend.application.game;

public class GameBombApplication implements GameBombInfo {
    private int maxBomb;
    private int minBomb;
    @Override
    public void setMaxBomb(int maxBomb) {
        this.maxBomb = maxBomb;
    }

    @Override
    public void setMinBomb(int minBomb) {
        this.minBomb = minBomb;
    }


    @Override
    public int getMaxBomb() {
        return maxBomb;
    }

    @Override
    public int getMinBomb() {
        return minBomb;
    }
}
