package ch.supsi.backend.dataAccess.l10n;

import java.util.List;
import java.util.Locale;
import java.util.Properties;

public interface TranslationsDataAccessInterface {
    List<String> getSupportedLanguageTags();

    Properties getTranslations(Locale locale);
}
