package ch.supsi.backend.application.gameMapper;

import java.io.File;

public interface IGameStateMapperApplication {
    void toDTO();
    void fromDTO(String fileName);
    void toDTOas(File file);
}
