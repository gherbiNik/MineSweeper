package ch.supsi.frontend.controller.gameMapperController;

import ch.supsi.backend.business.dto.GameStateBusiness;
import ch.supsi.backend.business.model.AbstractModel;
import ch.supsi.frontend.model.gameMapperModel.IGameMapperModel;
import ch.supsi.frontend.view.DataView;

import java.util.List;

public class GameMapperController implements IGameMapperController{
    private static GameMapperController myself;
    private final IGameMapperModel gameMapperModel;
    private List<DataView> views;

    public static GameMapperController getInstance(IGameMapperModel gameModel) {
        if (myself == null) {
            myself = new GameMapperController(gameModel);
        }
        return myself;
    }

    private GameMapperController(IGameMapperModel gameMapperModel) {
        this.gameMapperModel = gameMapperModel;
    }

    public void initialize(List<DataView> views) {
        this.views = views;
    }



    @Override
    public void save(AbstractModel model, String fileName) {
        gameMapperModel.save(model, fileName);
        this.views.forEach(DataView::update);
    }

    @Override
    public void open(AbstractModel model, String fileName) {
        gameMapperModel.open(model, fileName);
        this.views.forEach(DataView::update);
    }
}
