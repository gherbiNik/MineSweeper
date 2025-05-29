package ch.supsi.backend.business.service;

import ch.supsi.backend.application.dto.GameStateDTO;

public interface GameSaveService {
    void saveGame(GameStateDTO gameState, String fileName);
    GameStateDTO loadGame(String fileName);
    boolean saveExists(String fileName);
    void deleteSave(String fileName);
}