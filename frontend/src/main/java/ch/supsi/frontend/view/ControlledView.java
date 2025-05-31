package ch.supsi.frontend.view;

import ch.supsi.backend.application.l10n.TranslationsApplicationInterface;
import ch.supsi.frontend.controller.EventHandler;
import ch.supsi.backend.business.model.AbstractModel;
import ch.supsi.frontend.controller.gameMapperController.IGameMapperController;
import ch.supsi.frontend.controller.gameMapperController.IInfoController;
import ch.supsi.frontend.controller.preferences.IPreferencesController;
import ch.supsi.frontend.model.game.GameBoardModelInterface;
import ch.supsi.frontend.model.game.GameModelInterface;

public interface ControlledView extends DataView {

   default void initialize(EventHandler eventHandler, GameModelInterface model, IGameMapperController gameMapperController){}
   default void initialize(EventHandler eventHandler, GameModelInterface model, IGameMapperController gameMapperController, IInfoController infoController, ShowView preferenceView, TranslationsApplicationInterface translationsApplicationInterface){}
   default void initialize(EventHandler eventHandler, GameModelInterface model, IGameMapperController gameMapperController, ShowView view, TranslationsApplicationInterface translationsApplicationInterface, ExitView exitView){}
   default void initialize(IPreferencesController preferencesController, TranslationsApplicationInterface translationsApplication, ExitView exitView){}
   default void initialize(EventHandler eventHandler, GameModelInterface model, IGameMapperController gameController,GameBoardModelInterface gameBoardModelInterface){}
   default  void initialize(IPreferencesController preferencesController, TranslationsApplicationInterface translationsApplication){}

}
