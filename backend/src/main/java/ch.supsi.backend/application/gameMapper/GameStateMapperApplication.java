package ch.supsi.backend.application.gameMapper;

import ch.supsi.backend.business.dto.GameStateBusiness;
import ch.supsi.backend.business.mapper.GameStateMapperBusiness;
import ch.supsi.backend.business.model.AbstractModel;

public class GameStateMapperApplication implements IGameStateMapperApplication {

    private final GameStateMapperBusiness gameStateMapperBusiness;

    public GameStateMapperApplication(GameStateMapperBusiness gameStateMapperBusiness) {
        this.gameStateMapperBusiness = gameStateMapperBusiness;
    }
    @Override
    public void toDTO(AbstractModel gameModel, String fileName) {
        gameStateMapperBusiness.toDTO(gameModel, fileName);
    }

    @Override
    public void fromDTO(AbstractModel gameModel, String fileName) {
        gameStateMapperBusiness.fromDTO(gameModel, fileName);
    }
}
