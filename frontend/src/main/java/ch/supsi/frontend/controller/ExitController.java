package ch.supsi.frontend.controller;

import ch.supsi.backend.application.l10n.TranslationsApplication;
import ch.supsi.backend.business.l10n.TranslationsBusinessInterface;
import ch.supsi.backend.business.preferences.PreferencesBusinessInterface;
import ch.supsi.frontend.model.game.GameModel;
import ch.supsi.frontend.view.DataView;
import javafx.stage.Stage;
import java.util.List;

public class ExitController {

    private Stage primaryStage;
    private static ExitController myself;

    private ExitController(Stage primaryStage) {
        this.primaryStage = primaryStage;

    }

    public static ExitController getInstance(Stage primaryStage) {
        if (myself == null) {
            myself = new ExitController(primaryStage);
        }
        return myself;
    }

    public void quit() {
        if (primaryStage != null) {
            primaryStage.close();
        }


        System.out.println("wfjwoijfwoiwjw");
    }

}
