package ch.supsi.backend.business.cell;

public interface ICell {
    boolean isMine();
    void setMine(boolean mine);

    boolean isRevealed();
    void setRevealed(boolean revealed);

    boolean isFlagged();
    void toggleFlag();
    void setFlagged(boolean flagged);

    int getRow();
    int getCol();

    int getAdjacentMines();
    void setAdjacentMines(int adjacentMines);
}
