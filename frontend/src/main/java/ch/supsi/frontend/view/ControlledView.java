package ch.supsi.frontend.view;

import ch.supsi.backend.application.l10n.TranslationsApplicationInterface;
import ch.supsi.frontend.controller.EventHandler;
import ch.supsi.frontend.controller.ExitController;
import ch.supsi.frontend.controller.gameMapperController.IGameMapperController;
import ch.supsi.frontend.controller.IInfoController;
import ch.supsi.frontend.controller.InfoController;
import ch.supsi.frontend.controller.l10n.TranslationControllerInterface;
import ch.supsi.frontend.controller.preferences.IPreferencesController;
import ch.supsi.frontend.model.game.GameModelInterface;
import javafx.scene.transform.Translate;

public interface ControlledView extends DataView {

   default void initialize(int boardSize, EventHandler eventHandler, GameModelInterface model, IGameMapperController gameController){}
   default void initialize(EventHandler eventHandler, GameModelInterface model, IGameMapperController gameMapperController, ShowView preferenceView, TranslationControllerInterface translationControllerInterface, ExitView exitView,  IInfoController infoController, OpenGameView openGameView, SaveAsView saveAsView) {}


}
