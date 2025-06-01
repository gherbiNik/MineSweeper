package ch.supsi.frontend.controller;

import ch.supsi.frontend.MainFx;
import javafx.stage.Stage;

public class ExitController {

    private static ExitController myself;

    private ExitController() {
    }

    public static ExitController getInstance() {
        if (myself == null) {
            myself = new ExitController();
        }
        return myself;
    }

    public void quit() {
        Stage primaryStage = MainFx.getStageToClose();
        if (primaryStage != null) {
            primaryStage.close();
        }


        System.out.println("wfjwoijfwoiwjw");
    }

}
