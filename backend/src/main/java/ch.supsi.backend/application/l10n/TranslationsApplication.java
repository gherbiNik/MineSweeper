package ch.supsi.backend.application.l10n;

import ch.supsi.backend.business.l10n.TranslationsBusinessInterface;
import ch.supsi.backend.business.preferences.PreferencesBusinessInterface;
import ch.supsi.backend.business.l10n.TranslationsBusiness;
import ch.supsi.backend.business.preferences.PreferencesBusiness;

public class TranslationsApplication implements TranslationsApplicationInterface {
    private static TranslationsApplication myself;
    //viene creato cosi però l'interfaccia coi metodi viene implementata nel translationsModel.
    private  TranslationsBusinessInterface translationsModel;

    private  PreferencesBusinessInterface preferencesModel;

    private TranslationsApplication() {




    }

    public static TranslationsApplication getInstance(PreferencesBusinessInterface preferencesBusiness, TranslationsBusinessInterface translationsModel) {
        if (myself == null) {
            myself = new TranslationsApplication();
            myself.initialize(preferencesBusiness, translationsModel);
        }

        return myself;
    }

    private void initialize(PreferencesBusinessInterface preferencesBusiness, TranslationsBusinessInterface translationsModel) {
        this.translationsModel = translationsModel;
        this.preferencesModel=preferencesBusiness;
        String currentLanguage = preferencesModel.getCurrentLanguage();
        this.translationsModel.changeLanguage(currentLanguage);
    }

    /**
     * Translate the given key
     *
     * @return String
     */
    //La usiamo nella view
    @Override
    public String translate(String key) {
        return this.translationsModel.translate(key);
    }

}
