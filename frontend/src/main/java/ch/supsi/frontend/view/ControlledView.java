package ch.supsi.frontend.view;

import ch.supsi.backend.application.l10n.TranslationsApplicationInterface;
import ch.supsi.frontend.controller.EventHandler;
import ch.supsi.frontend.controller.ExitController;
import ch.supsi.frontend.controller.gameMapperController.IGameMapperController;
import ch.supsi.frontend.controller.IInfoController;
import ch.supsi.frontend.controller.InfoController;
import ch.supsi.frontend.controller.preferences.IPreferencesController;
import ch.supsi.frontend.model.game.GameModelInterface;

public interface ControlledView extends DataView {

   default void initialize(int boardSize, EventHandler eventHandler, GameModelInterface model, IGameMapperController gameController){}
   default  void initialize(IPreferencesController preferencesController, TranslationsApplicationInterface translationsApplication){}
   default void initialize(EventHandler eventHandler, GameModelInterface model, IGameMapperController gameMapperController, ShowView preferenceView, TranslationsApplicationInterface translationsApplicationInterface, ExitView exitView, ExitController exitController, IInfoController infoController, OpenGameView openGameView, SaveAsView saveAsView) {}


}
