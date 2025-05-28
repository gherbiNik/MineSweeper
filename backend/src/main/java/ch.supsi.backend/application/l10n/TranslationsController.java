package ch.supsi.backend.application.l10n;

import ch.supsi.backend.application.preferences.PreferencesBusinessInterface;
import ch.supsi.backend.business.l10n.TranslationsModel;
import ch.supsi.backend.business.preferences.PreferencesModel;

public class TranslationsController {
    private static TranslationsController myself;
    //viene creato cosi però l'interfaccia coi metodi viene implementata nel translationsModel.
    private final TranslationsBusinessInterface translationsModel;

    private final PreferencesBusinessInterface preferencesModel;

    protected TranslationsController() {
        this.preferencesModel = PreferencesModel.getInstance();
        this.translationsModel = TranslationsModel.getInstance();

        String currentLanguage = preferencesModel.getCurrentLanguage();
        this.translationsModel.changeLanguage(currentLanguage);
    }

    public static TranslationsController getInstance() {
        if (myself == null) {
            myself = new TranslationsController();
        }

        return myself;
    }

    /**
     * Translate the given key
     *
     * @param key
     * @return String
     */
    //returna un translations mode
    public String translate(String key) {
        return this.translationsModel.translate(key);
    }

}
