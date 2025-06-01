package ch.supsi.frontend.model.preferences;

import ch.supsi.backend.application.preferences.PreferencesApplicationInterface;

import java.nio.file.Path;

public class PreferencesModel implements IPreferencesModel {

    private static PreferencesModel myself;
    private PreferencesApplicationInterface preferences;

    private PreferencesModel() {
    }

    public static PreferencesModel getInstance(PreferencesApplicationInterface preferencesApplciation) {
        if (myself == null) {
            myself = new PreferencesModel();
            myself.initialize(preferencesApplciation);
        }
        return myself;
    }

    public void initialize(PreferencesApplicationInterface preferences) {
        this.preferences = preferences;
    }

    @Override
    public void setPreferences(String value, Object object) {
        preferences.setPreference(value, object);
    }

    @Override
    public Object getPreferences(String key) {
        return preferences.getPreference(key);
    }

    @Override
    public Path getUserPreferencesDirectoryPath() {
        return preferences.getUserPreferencesDirectoryPath();
    }
}
