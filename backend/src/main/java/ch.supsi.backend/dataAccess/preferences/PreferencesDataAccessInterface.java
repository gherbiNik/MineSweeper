package ch.supsi.backend.dataAccess.preferences;

import java.util.Properties;

public interface PreferencesDataAccessInterface {
    Properties getPreferences();

    void setPreference(String key, String string);
}
