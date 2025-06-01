package ch.supsi.backend.business.mapper;

import java.io.File;

public interface GameStateMapperBusiness {
    void toDTO();
    void fromDTO(String fileName);
    void toDTOas(File file);

}
