package ch.supsi.backend.application.gameMapper;

import ch.supsi.backend.business.dto.GameStateBusiness;
import ch.supsi.backend.business.model.AbstractModel;

public interface IGameStateMapperApplication {
    void toDTO(String fileName);
    void fromDTO(String fileName);
}
