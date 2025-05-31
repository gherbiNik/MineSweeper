package ch.supsi.backend.business.preferences;

import ch.supsi.backend.dataAccess.preferences.PreferencesDataAccessInterface;
import ch.supsi.backend.dataAccess.preferences.PreferencesDataAccess;

import java.util.Properties;

public class PreferencesBusiness implements PreferencesBusinessInterface {

    private static PreferencesBusiness myself;

    private  PreferencesDataAccessInterface preferencesDao;

    private  Properties userPreferences;

    protected PreferencesBusiness() {

    }

    public static PreferencesBusiness getInstance(PreferencesDataAccessInterface preferencesDao) {
        if (myself == null) {
            myself = new PreferencesBusiness();
            myself.initialize(preferencesDao);
        }

        return myself;
    }

    private void initialize( PreferencesDataAccessInterface preferencesDao ){
        this.preferencesDao = preferencesDao;
        this.userPreferences = preferencesDao.getPreferences();
    }

    @Override
    public String getCurrentLanguage() {

        //con getProperty in base al tag capis cosa deve restituire
        return userPreferences.getProperty("language-tag");
    }

    @Override
    public Object getPreference(String key) {
        if (key == null || key.isEmpty()) {
            return null;
        }

        if (userPreferences == null) {
            return null;
        }

        return userPreferences.get(key);
    }

    @Override
    public void setPreference(String key, Object value) {
        this.preferencesDao.setPreference(key, value.toString());
    }

}
