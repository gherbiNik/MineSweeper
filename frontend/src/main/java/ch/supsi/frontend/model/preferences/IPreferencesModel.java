package ch.supsi.frontend.model.preferences;

import java.nio.file.Path;

public interface IPreferencesModel {
    void setPreferences(String value, Object object);
    Object getPreferences(String key);
    Path getUserPreferencesDirectoryPath();
}
