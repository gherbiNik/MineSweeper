package ch.supsi.backend.application.cell;

import ch.supsi.backend.business.mine.MinePlacementStrategy;
import ch.supsi.backend.business.mine.MineRevealer;

public class MineRevealerApplication implements CellActionApplication{
    private MineRevealer mineRevealer;
    private static MineRevealerApplication myself;

    private MineRevealerApplication() {
    }

    public static MineRevealerApplication getInstance(MineRevealer mineRevealer)
    {
        if(myself == null)
        {
            myself = new MineRevealerApplication();
            myself.initialize(mineRevealer);
        }
        return myself;

    }

    private void initialize(MineRevealer mineRevealer) {
        this.mineRevealer = mineRevealer;
    }


    @Override
    public void toggleFlag(int row, int col) {
        mineRevealer.toggleFlag(row, col);
    }

    @Override
    public void revealCell(MinePlacementStrategy bombPlacer, int row, int col) {
        mineRevealer.revealCell(bombPlacer ,row, col);
    }
}
