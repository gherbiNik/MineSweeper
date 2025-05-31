package ch.supsi.frontend.view;

import ch.supsi.backend.application.l10n.TranslationsApplicationInterface;
import ch.supsi.backend.business.model.AbstractModel;
import ch.supsi.frontend.controller.EventHandler;
import ch.supsi.frontend.controller.gameMapperController.IGameMapperController;
import ch.supsi.frontend.controller.gameMapperController.IInfoController;

public interface InfoViewInit {
    void initialize(EventHandler eventHandler, AbstractModel model, IGameMapperController gameMapperController, IInfoController infoController, ShowView preferenceView, TranslationsApplicationInterface translationsApplicationInterface);
}
