package ch.supsi.backend.application.preferences;

//queste interfacce vengono gestite come model nel controller perchè nel model modell implementano quest'interfaccia.
public interface PreferencesBusinessInterface {
    String getCurrentLanguage();

    Object getPreference(String key);
}
