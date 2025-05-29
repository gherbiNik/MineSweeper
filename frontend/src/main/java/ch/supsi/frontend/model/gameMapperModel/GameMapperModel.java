package ch.supsi.frontend.model.gameMapperModel;

import ch.supsi.backend.business.dto.GameStateBusiness;
import ch.supsi.backend.application.gameMapper.IGameStateMapperApplication;
import ch.supsi.backend.business.model.AbstractModel;
import ch.supsi.frontend.controller.gameMapperController.GameMapperController;

public class GameMapperModel implements IGameMapperModel{

    private static GameMapperModel myself;
    private final IGameStateMapperApplication gameStateMapperApplication;

    private GameMapperModel(IGameStateMapperApplication gameStateMapperApplication) {
        this.gameStateMapperApplication = gameStateMapperApplication;
    }

    public static GameMapperModel getInstance(IGameStateMapperApplication gameStateMapperApplication){
        if (myself == null) {
            myself = new GameMapperModel(gameStateMapperApplication);
        }
        return myself;
    }



    @Override
    public void save(AbstractModel model, String fileName) {
        gameStateMapperApplication.toDTO(model, fileName);
    }

    @Override
    public void open(AbstractModel gameModel, String fileName) {
        gameStateMapperApplication.fromDTO(gameModel, fileName);

    }
}
