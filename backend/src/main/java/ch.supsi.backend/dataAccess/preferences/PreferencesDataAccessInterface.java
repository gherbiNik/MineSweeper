package ch.supsi.backend.dataAccess.preferences;

import java.nio.file.Path;
import java.util.Properties;

public interface PreferencesDataAccessInterface {
    Properties getPreferences();

    void setPreference(String key, String string);

    Path getUserPreferencesDirectoryPath();
}
