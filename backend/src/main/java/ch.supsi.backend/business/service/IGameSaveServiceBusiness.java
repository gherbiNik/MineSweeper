package ch.supsi.backend.business.service;

import ch.supsi.backend.business.dto.GameStateBusiness;
import ch.supsi.backend.business.dto.IGameStateBusiness;

import java.io.File;

public interface IGameSaveServiceBusiness {
    void saveGame(IGameStateBusiness gameState);
    void saveGameAs(IGameStateBusiness gameState, File file);
    IGameStateBusiness loadGame(String filename);
}