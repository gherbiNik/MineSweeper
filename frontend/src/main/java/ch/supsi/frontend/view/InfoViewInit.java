package ch.supsi.frontend.view;

import ch.supsi.frontend.controller.IInfoController;
import ch.supsi.frontend.controller.EventHandler;
import ch.supsi.frontend.controller.gameMapperController.IGameMapperController;
import ch.supsi.frontend.controller.l10n.TranslationControllerInterface;
import ch.supsi.frontend.model.game.GameModelInterface;

public interface InfoViewInit {
    void initialize(IInfoController infoController);
    void initialize(EventHandler eventHandler, GameModelInterface model, IGameMapperController gameMapperController, ShowView preferenceView, TranslationControllerInterface translationControllerInterface, ExitView exitView, IInfoController infoController, OpenGameView openGameView, SaveAsView saveAsView);
}
