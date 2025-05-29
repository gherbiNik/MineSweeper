package ch.supsi.backend.dataAccess.states;

import ch.supsi.backend.business.service.GameSaveService;

public class GameSaveServiceFactory {

    private static final String userHomeDirectory = System.getProperty("user.home");

    private static final String preferencesDirectory = ".MineSweeperData";

    public static GameSaveService createJacksonSaveService() {
        return new JacksonGameSaveService(preferencesDirectory);
    }

    public static GameSaveService createJacksonSaveService(String customDirectory) {
        return new JacksonGameSaveService(customDirectory);
    }
}
