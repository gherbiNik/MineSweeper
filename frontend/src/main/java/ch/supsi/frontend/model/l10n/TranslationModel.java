package ch.supsi.frontend.model.l10n;

import ch.supsi.backend.application.l10n.TranslationsApplicationInterface;

public class TranslationModel implements TranslationModelInterface {
    private static TranslationModel instance;
    private TranslationsApplicationInterface translationsApplicationInterface;

    private TranslationModel() {}

    public static TranslationModel getInstance(TranslationsApplicationInterface translationsApplicationInterface) {
        if (instance == null) {
            instance = new TranslationModel();
            instance.initialize(translationsApplicationInterface);

        }
        return instance;
    }

    private void initialize(TranslationsApplicationInterface translationsApplicationInterface){
        this.translationsApplicationInterface = translationsApplicationInterface;
    }



    @Override
    public String translate(String key) {
        return translationsApplicationInterface.translate(key);
    }
}
