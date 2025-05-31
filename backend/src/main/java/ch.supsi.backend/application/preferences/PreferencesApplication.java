package ch.supsi.backend.application.preferences;

import ch.supsi.backend.business.preferences.PreferencesBusinessInterface;

public class PreferencesApplication implements PreferencesApplicationInterface{

    private static PreferencesApplication myself;

    private  PreferencesBusinessInterface preferencesModel;

    protected PreferencesApplication() {
    }

    public static PreferencesApplication getInstance(PreferencesBusinessInterface preferencesModel) {
        if (myself == null) {
            myself = new PreferencesApplication();
            myself.initialize(preferencesModel);

        }

        return myself;
    }

    private void initialize(PreferencesBusinessInterface preferencesModel) {
        this.preferencesModel = preferencesModel;
    }

    /**
     * Return the value for the given key
     *
     * @param key
     * @return String
     */
    @Override
    public Object getPreference(String key) {
        return this.preferencesModel.getPreference(key);
    }

    @Override
    public void setPreference(String key, Object value) {
        this.preferencesModel.setPreference(key, value);
    }


}

