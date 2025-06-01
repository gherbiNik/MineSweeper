package ch.supsi.backend.application.preferences;

import java.nio.file.Path;

public interface PreferencesApplicationInterface {
    Object getPreference(String key);
    void setPreference(String key, Object value);
    Path getUserPreferencesDirectoryPath();
}
