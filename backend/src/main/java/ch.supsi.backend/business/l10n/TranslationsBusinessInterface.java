package ch.supsi.backend.business.l10n;

public interface TranslationsBusinessInterface {


    boolean changeLanguage(String languageTag);

    String translate(String key);
}
