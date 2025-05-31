package ch.supsi.frontend.model.gameMapperModel;

import ch.supsi.backend.application.gameMapper.IGameStateMapperApplication;

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
    public void save(String fileName) {
        gameStateMapperApplication.toDTO(fileName);
    }

    @Override
    public void open(String fileName) {
        gameStateMapperApplication.fromDTO(fileName);

    }
}
