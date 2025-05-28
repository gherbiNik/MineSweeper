package ch.supsi.backend.business.model;

import ch.supsi.backend.business.cell.Cell;

public interface BoardAccess {
    Cell[][] getBoard();

    Cell getCell(int x, int y);
}
