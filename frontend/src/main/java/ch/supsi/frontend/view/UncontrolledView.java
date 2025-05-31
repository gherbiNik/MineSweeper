package ch.supsi.frontend.view;

import ch.supsi.backend.application.l10n.TranslationsApplicationInterface;
import ch.supsi.backend.business.model.AbstractModel;

public interface UncontrolledView extends DataView {

    void initialize(AbstractModel model, TranslationsApplicationInterface translationsApplicationInterface);

}
