package ch.supsi.minesweeper.model;

public interface BoardAccess {
    Cell[][] getBoard();
    Cell getCell(int x, int y);
}
