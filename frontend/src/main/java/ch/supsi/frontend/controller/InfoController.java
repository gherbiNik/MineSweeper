package ch.supsi.frontend.controller;

import ch.supsi.frontend.view.InfoView;

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
