package ch.supsi.backend.business.model;

import ch.supsi.backend.business.cell.ICell;

public interface BoardAccess {
    ICell[][] getBoard();

    ICell getCell(int x, int y);

    void setBoard(ICell[][] board);
}
