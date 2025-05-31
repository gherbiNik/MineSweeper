package ch.supsi.backend.business.mine;

import ch.supsi.backend.business.game.GameBoardInfo;
import ch.supsi.backend.business.model.AbstractModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class BombPlacer implements MinePlacementStrategy {

    private GameBoardInfo gameInfo;

    public BombPlacer(GameBoardInfo gameInfo) {
        this.gameInfo = gameInfo;
    }

    @Override
    public void placeMines(AbstractModel model, int mineCount) {
        Random random = new Random();
        int minesPlaced = 0;

        // Creates clusters of bombs
        while (minesPlaced < model.getMineCount()) {
            // Select a random position for a potential cluster center
            int row = random.nextInt(gameInfo.getSize());
            int col = random.nextInt(gameInfo.getSize());


            if (!model.getBoard()[row][col].isMine()) {
                model.getBoard()[row][col].setMine(true);
                minesPlaced++;

                int maxAdditionalMines = Math.min(gameInfo.getSize(), model.getMineCount()) - minesPlaced;
                int numBombsAround = (maxAdditionalMines > 0) ? random.nextInt(maxAdditionalMines + 1) : 0;

                // Place additional mines in a cluster around this one
                if (numBombsAround > 0) {
                    int additionalMines = placeMinesAround(model, row, col, numBombsAround);
                    minesPlaced += additionalMines;
                }
            }
        }

        calculateAdjacentMines(model);
        model.setGameStarted(true);
    }

    private int placeMinesAround(AbstractModel model, int row, int col, int numBombsAround) {
        int bombsPlaced = 0;

        List<int[]> validCells = new ArrayList<>();

        // Check all 8 positions around the current cell
        for (int i = Math.max(0, row - 1); i <= Math.min(gameInfo.getSize() - 1, row + 1); i++) {
            for (int j = Math.max(0, col - 1); j <= Math.min(gameInfo.getSize() - 1, col + 1); j++) {

                // Skipping the center of the cluster
                if (i == row && j == col) {
                    continue;
                }

                if (!model.getBoard()[i][j].isMine()) {
                    validCells.add(new int[]{i, j});
                }
            }
        }

        Collections.shuffle(validCells);

        // Place as many as possible
        int minesToPlace = Math.min(numBombsAround, validCells.size());

        for (int i = 0; i < minesToPlace; i++) {
            int[] cell = validCells.get(i);
            model.getBoard()[cell[0]][cell[1]].setMine(true);
            bombsPlaced++;
        }

        return bombsPlaced;
    }


    private void calculateAdjacentMines(AbstractModel model) {
        for (int i = 0; i < gameInfo.getSize(); i++) {
            for (int j = 0; j < gameInfo.getSize(); j++) {
                if (!model.getBoard()[i][j].isMine()) {
                    int count = countAdjacentMines(model, i, j);
                    model.getBoard()[i][j].setAdjacentMines(count);
                }
            }
        }
    }

    private int countAdjacentMines(AbstractModel model, int row, int col) {
        int count = 0;

        // Controlla le 8 celle adiacenti
        for (int i = Math.max(0, row - 1); i <= Math.min(gameInfo.getSize() - 1, row + 1); i++) {
            for (int j = Math.max(0, col - 1); j <= Math.min(gameInfo.getSize() - 1, col + 1); j++) {
                if (!(i == row && j == col) && model.getBoard()[i][j].isMine()) {
                    count++;
                }
            }
        }

        return count;
    }
}
