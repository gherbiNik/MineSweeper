package ch.supsi.frontend.view;

import ch.supsi.frontend.controller.gameMapperController.IGameMapperController;
import ch.supsi.frontend.model.preferences.IPreferencesModel;
import javafx.stage.FileChooser;

import java.io.File;

public class OpenGameView implements ShowView{
    private static OpenGameView myself;
    private IGameMapperController gameMapperController;
    private IPreferencesModel preferencesModel;

    private OpenGameView() {}

    public static OpenGameView getInstance(IGameMapperController gameMapperController, IPreferencesModel preferencesModel) {
        if (myself == null) {
            myself = new OpenGameView();
            myself.initialize(gameMapperController, preferencesModel);
        }
        return myself;
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

        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            gameMapperController.open(selectedFile.toString());

        } else {
            System.out.println("Nessun file selezionato");
        }
    }
}
