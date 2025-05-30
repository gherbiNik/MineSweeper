package ch.supsi.backend.business.l10n;

import ch.supsi.backend.application.l10n.TranslationsBusinessInterface;
import ch.supsi.backend.dataAccess.l10n.TranslationsPropertiesDataAccess;

import java.util.List;
import java.util.Locale;
import java.util.Properties;

public class TranslationsModel implements TranslationsBusinessInterface {

    private static TranslationsModel myself;

    private final TranslationsDataAccessInterface translationsDao;
    //lista dei tag supportati
    private final List<String> supportedLanguageTags;

    private Properties translations;

    protected TranslationsModel() {
        this.translationsDao = TranslationsPropertiesDataAccess.getInstance();
        this.supportedLanguageTags = translationsDao.getSupportedLanguageTags();
    }

    //da un istanza del translations model
    public static TranslationsModel getInstance() {
        if (myself == null) {
            myself = new TranslationsModel();
        }
        return myself;
    }

    //verifica che il tag sia supportato
    @Override
    public boolean isSupportedLanguageTag(String languageTag) {
        if (!this.supportedLanguageTags.contains(languageTag)) {
            return false;
        }
        return true;
    }

    //funzione che server pa cambiare la lingua
    @Override
    public boolean changeLanguage(String languageTag) {
        //crea un oggetto locale a partire da un tag
        //TODO DA CAPIRE SE CAMBIARE O NO
        this.translations = translationsDao.getTranslations(Locale.forLanguageTag(languageTag));

        return this.translations != null;
    }

    @Override
    public String translate(String key) {
        return this.translations.getProperty(key);
    }
}
