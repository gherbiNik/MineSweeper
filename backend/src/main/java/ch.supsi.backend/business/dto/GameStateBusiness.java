package ch.supsi.backend.business.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GameStateBusiness implements IGameStateBusiness {
    @JsonProperty("cells")
    private final CellStateBusiness[][] cells;

    @JsonProperty("mineCount")
    private final int mineCount;

    @JsonProperty("revealedCellCount")
    private final int revealedCellCount;

    @JsonProperty("flaggedCellCount")
    private final int flaggedCellCount;

    @JsonProperty("gameStarted")
    private final boolean gameStarted;

    @JsonProperty("gameOver")
    private final boolean gameOver;

    @JsonProperty("gameWon")
    private final boolean gameWon;

    @JsonProperty("boardSize")
    private final int boardSize;

    @JsonProperty("maxBomb")
    private final int maxBomb;

    @JsonProperty("minBomb")
    private final int minBomb;

    @JsonProperty("timestamp")
    private final long timestamp;

    @JsonCreator
    public GameStateBusiness(
            @JsonProperty("cells") CellStateBusiness[][] cells,
            @JsonProperty("mineCount") int mineCount,
            @JsonProperty("revealedCellCount") int revealedCellCount,
            @JsonProperty("flaggedCellCount") int flaggedCellCount,
            @JsonProperty("gameStarted") boolean gameStarted,
            @JsonProperty("gameOver") boolean gameOver,
            @JsonProperty("gameWon") boolean gameWon,
            @JsonProperty("boardSize") int boardSize,
            @JsonProperty("maxBomb") int maxBomb,
            @JsonProperty("minBomb") int minBomb,
            @JsonProperty("timestamp") long timestamp) {
        this.cells = cells;
        this.mineCount = mineCount;
        this.revealedCellCount = revealedCellCount;
        this.flaggedCellCount = flaggedCellCount;
        this.gameStarted = gameStarted;
        this.gameOver = gameOver;
        this.gameWon = gameWon;
        this.boardSize = boardSize;
        this.maxBomb = maxBomb;
        this.minBomb = minBomb;
        this.timestamp = timestamp;
    }

    public ICellStateBusiness[][] getCells() { return cells; }
    public int getMineCount() { return mineCount; }
    public int getRevealedCellCount() { return revealedCellCount; }
    public int getFlaggedCellCount() { return flaggedCellCount; }
    public boolean isGameStarted() { return gameStarted; }
    public boolean isGameOver() { return gameOver; }
    public boolean isGameWon() { return gameWon; }
    public int getBoardSize() { return boardSize; }
    public int getMaxBomb() { return maxBomb; }
    public int getMinBomb() { return minBomb; }
    public long getTimestamp() { return timestamp; }
}
