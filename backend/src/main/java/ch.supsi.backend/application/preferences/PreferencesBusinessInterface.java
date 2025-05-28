package ch.supsi.backend.application.preferences;

public interface PreferencesBusinessInterface {
    String getCurrentLanguage();

    Object getPreference(String key);
}
