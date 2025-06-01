package ch.supsi.backend.business.dto;

public interface ICellStateBusiness {
    int getRow();
    int getCol();
    boolean isMine();
    boolean isRevealed();
    boolean isFlagged();
    int getAdjacentMines();
}
