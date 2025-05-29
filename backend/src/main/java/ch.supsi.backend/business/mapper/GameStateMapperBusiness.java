package ch.supsi.backend.business.mapper;

import ch.supsi.backend.business.dto.GameStateBusiness;
import ch.supsi.backend.business.dto.IGameStateBusiness;
import ch.supsi.backend.business.model.AbstractModel;

public interface GameStateMapperBusiness {
    void toDTO(AbstractModel gameModel, String fileName);
    void fromDTO(AbstractModel gameModel, String fileName);

}
