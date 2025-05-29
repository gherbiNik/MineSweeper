package ch.supsi.backend.business.service;

import ch.supsi.backend.business.dto.IGameStateBusiness;
import ch.supsi.backend.dataAccess.states.GameSaveData;

public class GameSaveServiceBusiness implements IGameSaveServiceBusiness {
    private final GameSaveData jacksonGameSaveData;

    public GameSaveServiceBusiness(GameSaveData jacksonGameSaveData) {
        this.jacksonGameSaveData = jacksonGameSaveData;
    }

    @Override
        public void saveGame(IGameStateBusiness gameState, String fileName) {
        jacksonGameSaveData.saveGame(gameState,fileName);
    }

    @Override
    public IGameStateBusiness loadGame(String fileName) {
        return jacksonGameSaveData.loadGame(fileName);
    }

    @Override
    public boolean saveExists(String fileName) {
        return jacksonGameSaveData.saveExists(fileName);
    }

    @Override
    public void deleteSave(String fileName) {
        jacksonGameSaveData.deleteSave(fileName);
    }



}
