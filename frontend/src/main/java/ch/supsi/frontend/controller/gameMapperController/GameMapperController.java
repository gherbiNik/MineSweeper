package ch.supsi.frontend.controller.gameMapperController;

import ch.supsi.frontend.model.gameMapperModel.IGameMapperModel;
import ch.supsi.frontend.view.DataView;

import java.io.File;
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
    public void save() {
        gameMapperModel.save();
        this.views.forEach(DataView::update);
    }



    @Override
    public void open(String fileName) {
        gameMapperModel.open(fileName);
        this.views.forEach(DataView::update);
    }

    @Override
    public void saveAs(File file) {
        gameMapperModel.saveAs(file);
        this.views.forEach(DataView::update);
    }
}
