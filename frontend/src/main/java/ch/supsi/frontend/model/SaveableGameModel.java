package ch.supsi.frontend.model;

import ch.supsi.backend.application.cell.CellActionApplication;
import ch.supsi.backend.application.game.GameBoardApplication;
import ch.supsi.backend.application.game.GameBombApplication;
import ch.supsi.backend.business.mine.MinePlacementStrategy;
import ch.supsi.backend.business.service.GameSaveService;
import ch.supsi.backend.application.dto.GameStateDTO;
import ch.supsi.frontend.controller.GameStateMapper;

public class SaveableGameModel extends GameModel {
    private static SaveableGameModel instance;
    private final GameSaveService saveService;


    private SaveableGameModel(
            MinePlacementStrategy bombPlacer,
            CellActionApplication mineRevealer,
            GameBombApplication gameBombApplication,
            GameBoardApplication gameBoardApplication,
            GameSaveService saveService
    ) {
        super(bombPlacer, mineRevealer, gameBombApplication, gameBoardApplication);
        this.saveService = saveService;
    }

    public static SaveableGameModel getInstance(
            MinePlacementStrategy bombPlacer,
            CellActionApplication mineRevealer,
            GameBombApplication gameBombApplication,
            GameBoardApplication gameBoardApplication,
            GameSaveService saveService
    ) {
        if (instance == null) {
            instance = new SaveableGameModel(
                    bombPlacer,
                    mineRevealer,
                    gameBombApplication,
                    gameBoardApplication,
                    saveService
            );
        }
        return instance;
    }

    public void saveGame(String saveName){
        GameStateDTO gameState = GameStateMapper.toDTO(this);
        saveService.saveGame(gameState, saveName);
    }

    public void loadGame(String saveName){
        GameStateDTO gameState = saveService.loadGame(saveName);
        GameStateMapper.fromDTO(gameState, this);

    }

    public boolean hasSave(String saveName) {
        return saveService.saveExists(saveName);
    }

    public void deleteSave(String saveName){
        saveService.deleteSave(saveName);
    }
}

