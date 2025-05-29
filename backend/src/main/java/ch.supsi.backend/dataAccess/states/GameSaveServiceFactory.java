package ch.supsi.backend.dataAccess.states;


public class GameSaveServiceFactory {

    private static final String userHomeDirectory = System.getProperty("user.home");

    private static final String preferencesDirectory = ".MineSweeperData";

    public static JacksonGameSaveService createJacksonSaveService() {
        return new JacksonGameSaveService(preferencesDirectory);
    }

    public static JacksonGameSaveService createJacksonSaveService(String customDirectory) {
        return new JacksonGameSaveService(customDirectory);
    }
}
