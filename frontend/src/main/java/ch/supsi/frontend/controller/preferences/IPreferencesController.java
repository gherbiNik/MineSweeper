package ch.supsi.frontend.controller.preferences;

public interface IPreferencesController {
    void setPreferences(String value, Object object);
    Object getPreferences(String value);
}
