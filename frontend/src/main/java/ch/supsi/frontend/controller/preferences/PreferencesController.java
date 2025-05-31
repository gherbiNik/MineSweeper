package ch.supsi.frontend.controller.preferences;

import ch.supsi.frontend.model.preferences.IPreferencesModel;

public class PreferencesController implements IPreferencesController {

    private static PreferencesController myself;
    private IPreferencesModel iPreferencesModel;

    private PreferencesController() {
    }
    public static PreferencesController getInstance(IPreferencesModel iPreferencesModel) {
        if (myself == null) {
            myself = new PreferencesController();
            myself.initialize(iPreferencesModel);
        }
        return myself;
    }

    public void initialize(IPreferencesModel iPreferencesModel) {
        this.iPreferencesModel = iPreferencesModel;
    }

    @Override
    public void setPreferences(String value, Object object) {
        this.iPreferencesModel.setPreferences(value, object);
    }

    @Override
    public Object getPreferences(String value) {
        return this.iPreferencesModel.getPreferences(value);
    }
}
