package ch.supsi.backend.application.gameMapper;

import ch.supsi.backend.business.dto.GameStateBusiness;
import ch.supsi.backend.business.model.AbstractModel;

import java.io.File;

public interface IGameStateMapperApplication {
    void toDTO();
    void fromDTO(String fileName);
    void toDTOas(File file);
}
