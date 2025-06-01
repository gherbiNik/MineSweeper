package ch.supsi.frontend.view;

import ch.supsi.frontend.controller.gameMapperController.IGameMapperController;
import ch.supsi.frontend.model.preferences.IPreferencesModel;
import javafx.stage.FileChooser;

import java.io.File;

public class SaveAsView implements ShowView{
    public static SaveAsView instance;
    private IGameMapperController gameMapperController;
    private IPreferencesModel preferencesModel;

    private SaveAsView() {}
    public static SaveAsView getInstance(IGameMapperController gameMapperController,  IPreferencesModel preferencesModel) {
        if (instance == null) {
            instance = new SaveAsView();
            instance.initialize(gameMapperController, preferencesModel);
        }
        return instance;
    }

    private void initialize(IGameMapperController gameMapperController,  IPreferencesModel preferencesModel){
        this.gameMapperController = gameMapperController;
        this.preferencesModel = preferencesModel;
    }

    @Override
    public void showView() {
        FileChooser fileChooser = new FileChooser();

        String userPreferencesPath = preferencesModel.getUserPreferencesDirectoryPath().toString();
        File initialDirectory = new File(userPreferencesPath);
        if (initialDirectory.exists() && initialDirectory.isDirectory()) {
            fileChooser.setInitialDirectory(initialDirectory);
        }

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JSON Files", "*.json"),
                new FileChooser.ExtensionFilter("All Files", "*.*")
        );


        File selectedfile = fileChooser.showSaveDialog(null);

        if (selectedfile != null) {
            gameMapperController.saveAs(selectedfile);
        } else {
            System.out.println("Nessun file selezionato");
            gameMapperController.save();
        }
    }
}
