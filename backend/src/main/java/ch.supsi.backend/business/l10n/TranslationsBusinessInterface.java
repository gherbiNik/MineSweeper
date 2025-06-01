package ch.supsi.backend.business.l10n;

public interface TranslationsBusinessInterface {

    boolean isSupportedLanguageTag(String languageTag);

    boolean changeLanguage(String languageTag);

    String translate(String key);
}
