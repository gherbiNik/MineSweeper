package ch.supsi.frontend.view;

import ch.supsi.backend.application.l10n.TranslationsApplicationInterface;
import ch.supsi.backend.business.model.AbstractModel;
import ch.supsi.frontend.model.game.GameModelInterface;

public interface UncontrolledView extends DataView {

    void initialize(GameModelInterface model, TranslationsApplicationInterface translationsApplicationInterface);

}
