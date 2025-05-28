package ch.supsi.frontend.model;

import ch.supsi.backend.business.Cell;

public interface BoardAccess {
    Cell[][] getBoard();

    Cell getCell(int x, int y);
}
