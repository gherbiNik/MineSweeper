package ch.supsi.backend.application.gameMapper;

import ch.supsi.backend.application.game.GameApplicationInterface;
import ch.supsi.backend.business.dto.GameStateBusiness;
import ch.supsi.backend.business.mapper.GameStateMapperBusiness;
import ch.supsi.backend.business.model.AbstractModel;

public class GameStateMapperApplication implements IGameStateMapperApplication {

    private static GameStateMapperApplication myself;
    private GameStateMapperBusiness gameStateMapperBusiness;

    private GameStateMapperApplication() {
    }

    public static GameStateMapperApplication getInstance(GameApplicationInterface gameApplicationInterface, GameStateMapperBusiness gameStateMapperBusiness) {
        if (myself == null) {
            myself = new GameStateMapperApplication();
            myself.intialize(gameStateMapperBusiness);
        }
        return myself;
    }

    private void intialize(GameStateMapperBusiness gameStateMapperBusiness) {
        this.gameStateMapperBusiness = gameStateMapperBusiness;
    }
    @Override
    public void toDTO(String fileName) {
        gameStateMapperBusiness.toDTO(fileName);
    }

    @Override
    public void fromDTO(String fileName) {
        gameStateMapperBusiness.fromDTO(fileName);
    }
}
