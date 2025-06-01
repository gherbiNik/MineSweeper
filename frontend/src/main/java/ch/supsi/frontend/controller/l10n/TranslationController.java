package ch.supsi.frontend.controller.l10n;

import ch.supsi.frontend.model.l10n.TranslationModelInterface;

public class TranslationController implements TranslationControllerInterface{
    private static TranslationController instance;
    private TranslationModelInterface translationModel;

    public static TranslationController getInstance(TranslationModelInterface translationModel){
        if(instance == null){
            instance = new TranslationController();
            instance.initialize(translationModel);
        }
        return instance;
    }

    private TranslationController() {

    }

    private void initialize(TranslationModelInterface translationModel) {
        this.translationModel = translationModel;
    }

    @Override
    public String translate(String text) {
        return translationModel.translate(text);
    }
}
