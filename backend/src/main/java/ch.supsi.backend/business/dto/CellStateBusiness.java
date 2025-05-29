package ch.supsi.backend.business.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonAutoDetect(
        fieldVisibility = JsonAutoDetect.Visibility.ANY,      // prendi solo i campi
        getterVisibility = JsonAutoDetect.Visibility.NONE,    // ignora i getter
        isGetterVisibility = JsonAutoDetect.Visibility.NONE   // ignora i boolean‐getter
)
public class CellStateBusiness implements ICellStateBusiness {
    @JsonProperty("row")
    private final int row;

    @JsonProperty("col")
    private final int col;

    @JsonProperty("isMine")
    private final boolean isMine;

    @JsonProperty("isRevealed")
    private final boolean isRevealed;

    @JsonProperty("isFlagged")
    private final boolean isFlagged;

    @JsonProperty("adjacentMines")
    private final int adjacentMines;

    @JsonCreator
    public CellStateBusiness(
            @JsonProperty("row") int row,
            @JsonProperty("col") int col,
            @JsonProperty("isMine") boolean isMine,
            @JsonProperty("isRevealed") boolean isRevealed,
            @JsonProperty("isFlagged") boolean isFlagged,
            @JsonProperty("adjacentMines") int adjacentMines) {
        this.row = row;
        this.col = col;
        this.isMine = isMine;
        this.isRevealed = isRevealed;
        this.isFlagged = isFlagged;
        this.adjacentMines = adjacentMines;
    }

    public int getRow() { return row; }
    public int getCol() { return col; }
    public boolean isMine() { return isMine; }
    public boolean isRevealed() { return isRevealed; }
    public boolean isFlagged() { return isFlagged; }
    public int getAdjacentMines() { return adjacentMines; }
}