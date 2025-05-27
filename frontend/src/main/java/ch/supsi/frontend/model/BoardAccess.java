package ch.supsi.frontend.model;

public interface BoardAccess {
    Cell[][] getBoard();

    Cell getCell(int x, int y);
}
