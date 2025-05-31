package ch.supsi.frontend.controller.gameMapperController;

import ch.supsi.frontend.controller.GameController;
import ch.supsi.frontend.model.GameModel;
import ch.supsi.frontend.view.InfoView;
import ch.supsi.frontend.view.UncontrolledFxView;

public class InfoController implements IInfoController{

    private static InfoController myself;
    private InfoView view;

    private InfoController(InfoView view)
    {
        this.view = view;
    }

    public static InfoController getInstance(InfoView view) {
        if (myself == null) {
            myself = new InfoController(view);
        }
        return myself;
    }

    @Override
    public void display(String infos) {
        view.display(infos);
    }

}
