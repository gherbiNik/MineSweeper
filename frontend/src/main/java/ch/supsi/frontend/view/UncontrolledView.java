package ch.supsi.frontend.view;

import ch.supsi.frontend.controller.l10n.TranslationControllerInterface;
import ch.supsi.frontend.model.game.GameModelInterface;

public interface UncontrolledView extends DataView {

    void initialize(GameModelInterface model, TranslationControllerInterface translationController);

}
