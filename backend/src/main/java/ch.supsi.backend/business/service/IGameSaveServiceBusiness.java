package ch.supsi.backend.business.service;

import ch.supsi.backend.business.dto.GameStateBusiness;
import ch.supsi.backend.business.dto.IGameStateBusiness;

public interface IGameSaveServiceBusiness {
    void saveGame(IGameStateBusiness gameState, String fileName);
    IGameStateBusiness loadGame(String fileName);
    boolean saveExists(String fileName);
    void deleteSave(String fileName);
}