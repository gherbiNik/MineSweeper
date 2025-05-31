package ch.supsi.frontend.model.preferences;

public interface IPreferencesModel {
    void setPreferences(String value, Object object);
    Object getPreferences(String key);
}
