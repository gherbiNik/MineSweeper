package ch.supsi.backend.business.l10n;

import ch.supsi.backend.dataAccess.l10n.TranslationsDataAccessInterface;
import ch.supsi.backend.dataAccess.l10n.TranslationsPropertiesDataAccess;

import java.util.List;
import java.util.Locale;
import java.util.Properties;

public class TranslationsBusiness implements TranslationsBusinessInterface {

    private static TranslationsBusiness myself;

    private  TranslationsDataAccessInterface translationsDao;
    //lista dei tag supportati
    private  List<String> supportedLanguageTags;

    private Properties translations;

    private TranslationsBusiness() {
    }

    //da un istanza del translations model
    public static TranslationsBusiness getInstance(TranslationsDataAccessInterface translationsDao) {
        if (myself == null) {
            myself = new TranslationsBusiness();
            myself.initialize(translationsDao);
        }
        return myself;
    }

    private void initialize(TranslationsDataAccessInterface translationsDao) {
        this.translationsDao = TranslationsPropertiesDataAccess.getInstance();
        this.supportedLanguageTags = translationsDao.getSupportedLanguageTags();
    }

    //verifica che il tag sia supportato
    @Override
    public boolean isSupportedLanguageTag(String languageTag) {
        return this.supportedLanguageTags.contains(languageTag);
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
