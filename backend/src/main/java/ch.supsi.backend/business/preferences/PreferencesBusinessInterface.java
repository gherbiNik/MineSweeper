package ch.supsi.backend.business.preferences;

import java.nio.file.Path;

//queste interfacce vengono gestite come model nel controller perchè nel model modell implementano quest'interfaccia.
public interface PreferencesBusinessInterface {
    String getCurrentLanguage();

    Object getPreference(String key);

    void setPreference(String key, Object value);
    Path getUserPreferencesDirectoryPath();
}
