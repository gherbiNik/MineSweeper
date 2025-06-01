package ch.supsi.backend.business.service;

import ch.supsi.backend.business.dto.IGameStateBusiness;
import ch.supsi.backend.dataAccess.states.GameSaveData;

import java.io.File;

public class GameSaveServiceBusiness implements IGameSaveServiceBusiness {
    private final GameSaveData jacksonGameSaveData;

    public GameSaveServiceBusiness(GameSaveData jacksonGameSaveData) {
        this.jacksonGameSaveData = jacksonGameSaveData;
    }

    @Override
    public void saveGame(IGameStateBusiness gameState) {
        jacksonGameSaveData.saveGame(gameState);
    }

    @Override
    public void saveGameAs(IGameStateBusiness gameState, File file) {
        jacksonGameSaveData.saveGameAs(gameState, file);
    }

    @Override
    public IGameStateBusiness loadGame(String fileName) {
        return jacksonGameSaveData.loadGame(fileName);
    }





}
