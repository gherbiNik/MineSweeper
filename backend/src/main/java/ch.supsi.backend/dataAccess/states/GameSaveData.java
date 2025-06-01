package ch.supsi.backend.dataAccess.states;

import ch.supsi.backend.business.dto.IGameStateBusiness;

import java.io.File;

public interface GameSaveData {
    void saveGame(IGameStateBusiness gameState);
    void saveGameAs(IGameStateBusiness gameState, File file);
    IGameStateBusiness loadGame(String fileName);

}

