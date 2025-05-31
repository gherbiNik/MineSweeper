package ch.supsi.frontend.view;

import ch.supsi.backend.application.l10n.TranslationsApplicationInterface;
import ch.supsi.frontend.controller.preferences.IPreferencesController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;


public class PreferenceView implements ShowView, ControlledView {

    private static PreferenceView myself;
    private IPreferencesController preferencesController;
    private TranslationsApplicationInterface translationsApplication;
    private String btnSave;
    private String btnCancel;
    private String languageL;
    private String preferenceL;
    private String nBombsL;



    private PreferenceView() {
    }

    public static PreferenceView getInstance() {
        if (myself == null)
            myself = new PreferenceView();
        return myself;
    }
    @Override
    public void initialize(IPreferencesController preferencesController, TranslationsApplicationInterface translationsApplication){
        this.preferencesController = preferencesController;
        this.translationsApplication = translationsApplication;
        btnSave = translationsApplication.translate("label.save");
        btnCancel = translationsApplication.translate("label.cancel");
        languageL = translationsApplication.translate("label.languageLabel");

        preferenceL = translationsApplication.translate("label.preferences");
        nBombsL = translationsApplication.translate("label.nBombsLabel");

    }


    @Override
    public void update() {

    }

    @Override
    public void newGameMessage() {

    }

    @Override
    public void flagUpdateMessage(int remainingMines) {

    }

    @Override
    public void gameOverMessage(String message) {

    }



    @Override
    public void showView() {
        try {
            // Crea una nuova finestra (Stage)
            Stage preferencesStage = new Stage();

            // Configura le proprietà della finestra
            preferencesStage.setTitle(preferenceL);
            preferencesStage.initModality(Modality.APPLICATION_MODAL);
            preferencesStage.setResizable(false);

            // Layout principale
            VBox mainLayout = new VBox(20);
            mainLayout.setPadding(new Insets(20));
            mainLayout.setAlignment(Pos.CENTER);

            // Selezione lingua
            HBox languageBox = new HBox(10);
            languageBox.setAlignment(Pos.CENTER);
            Label languageLabel = new Label(languageL);
            ComboBox<String> languageComboBox = new ComboBox<>();
            languageComboBox.getItems().addAll("it-IT", "en-EN");
            languageBox.getChildren().addAll(languageLabel, languageComboBox);

            // Slider per numero bombe
            VBox bombsBox = new VBox(10);
            bombsBox.setAlignment(Pos.CENTER);
            Label bombsLabel = new Label(nBombsL);
            Slider bombsSlider = new Slider(1, 81, 10); // min, max, default
            bombsSlider.setShowTickLabels(true);
            bombsSlider.setShowTickMarks(true);
            bombsSlider.setMajorTickUnit(20);
            bombsSlider.setMinorTickCount(4);
            bombsSlider.setBlockIncrement(1);

            // Label per mostrare il valore corrente dello slider
            Label bombsValueLabel = new Label("10");
            bombsSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
                bombsValueLabel.setText(String.valueOf(newVal.intValue()));
            });

            bombsBox.getChildren().addAll(bombsLabel, bombsSlider, bombsValueLabel);

            // Pulsanti
            HBox buttonBox = new HBox(10);
            buttonBox.setAlignment(Pos.CENTER);
            Button saveButton = new Button(btnSave);
            Button cancelButton = new Button(btnCancel);
            buttonBox.getChildren().addAll(cancelButton, saveButton);

            // Event handlers
            saveButton.setOnAction(e -> {

                savePreferences(languageComboBox.getValue(), (int) bombsSlider.getValue());
                preferencesStage.close();
            });

            cancelButton.setOnAction(e -> preferencesStage.close());

            // Carica preferenze esistenti
            loadCurrentPreferences(languageComboBox, bombsSlider, bombsValueLabel);

            // Assembla il layout
            mainLayout.getChildren().addAll(languageBox, bombsBox, buttonBox);

            // Crea la scena e mostra la finestra
            Scene scene = new Scene(mainLayout, 300, 200);
            preferencesStage.setScene(scene);
            preferencesStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Metodo per salvare le preferenze
    private void savePreferences(String language, int numberOfBombs) {
        this.preferencesController.setPreferences("language-tag", language);
        this.preferencesController.setPreferences("bomb-number", String.valueOf(numberOfBombs));
    }

    // Metodo per caricare le preferenze esistenti
    private void loadCurrentPreferences(ComboBox<String> languageComboBox,
                                        Slider bombsSlider, Label bombsValueLabel) {
        String language="it-IT";
        int numberOfBombs=10;
        try {
            Object obj1 = preferencesController.getPreferences("language-tag");
            if (obj1 != null) {
                language = obj1.toString();
            }
            Object obj2 = preferencesController.getPreferences("bomb-number");
            if (obj2 != null) {
                String valueStr = obj2.toString(); // o (String) obj se sei sicuro che sia String
                numberOfBombs = Integer.parseInt(valueStr);
            }



            languageComboBox.setValue(language);
            bombsSlider.setValue(numberOfBombs);
            bombsValueLabel.setText(String.valueOf(numberOfBombs));

        } catch (NumberFormatException e) {
            // Usa valori di default se il file non esiste o c'è un errore
            languageComboBox.setValue("it-IT");
            bombsSlider.setValue(10);
            bombsValueLabel.setText("10");
        }
    }
}