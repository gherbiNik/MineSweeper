package ch.supsi.backend.dataAccess.states;

public class NoGameSavedEx extends RuntimeException {
    public NoGameSavedEx(String message) {
        super(message);
    }
}
