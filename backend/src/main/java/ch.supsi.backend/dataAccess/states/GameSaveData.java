package ch.supsi.backend.dataAccess.states;

import ch.supsi.backend.business.dto.IGameStateBusiness;

public interface GameSaveData {
    void saveGame(IGameStateBusiness gameState, String fileName);
    IGameStateBusiness loadGame(String fileName);
    boolean saveExists(String fileName);
    void deleteSave(String fileName);

}

