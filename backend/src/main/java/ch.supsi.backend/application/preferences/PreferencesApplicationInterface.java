package ch.supsi.backend.application.preferences;

public interface PreferencesApplicationInterface {
    Object getPreference(String key);
    void setPreference(String key, Object value);
}
