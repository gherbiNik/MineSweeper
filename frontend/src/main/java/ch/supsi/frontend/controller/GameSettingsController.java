package ch.supsi.frontend.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class GameSettingsController implements Initializable {

    // Mappa delle lingue disponibili
    private static final Map<String, String> AVAILABLE_LANGUAGES = new HashMap<>();

    static {
        AVAILABLE_LANGUAGES.put("it-CH", "Italiano (Svizzera)");
        AVAILABLE_LANGUAGES.put("en-US", "English (United States)");
        AVAILABLE_LANGUAGES.put("fr-CH", "Français (Suisse)");
        AVAILABLE_LANGUAGES.put("de-CH", "Deutsch (Schweiz)");
        AVAILABLE_LANGUAGES.put("es-ES", "Español");
    }

    // Controlli FXML
    @FXML
    private Slider bombCountSlider;

    @FXML
    private Label bombCountLabel;

    @FXML
    private ComboBox<String> languageComboBox;

    @FXML
    private Button saveButton;

    @FXML
    private Button cancelButton;

    // Variabili per i valori
    private boolean settingsSaved = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupSlider();
        setupLanguageComboBox();
        loadCurrentSettings();
    }

    /**
     * Configura lo slider per il numero di bombe
     */
    private void setupSlider() {
        // Listener per aggiornare il label quando lo slider cambia
        bombCountSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            int value = newValue.intValue();
            bombCountLabel.setText(String.valueOf(value));

            // Cambia colore in base al numero di bombe (difficoltà)
            updateDifficultyColor(value);
        });

        // Imposta il valore iniziale del label
        bombCountLabel.setText(String.valueOf((int) bombCountSlider.getValue()));
        updateDifficultyColor((int) bombCountSlider.getValue());
    }

    /**
     * Aggiorna il colore del label in base alla difficoltà
     */
    private void updateDifficultyColor(int bombCount) {
        if (bombCount <= 10) {
            // Facile - Verde
            bombCountLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #4CAF50;");
        } else if (bombCount <= 25) {
            // Medio - Arancione
            bombCountLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #FF9800;");
        } else {
            // Difficile - Rosso
            bombCountLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #f44336;");
        }
    }

    /**
     * Configura la ComboBox per la selezione della lingua
     */
    private void setupLanguageComboBox() {
        ObservableList<String> languageOptions = FXCollections.observableArrayList();
        AVAILABLE_LANGUAGES.forEach((code, name) -> languageOptions.add(name));

        languageComboBox.setItems(languageOptions);

        // Seleziona la prima lingua come default se nessuna è selezionata
        if (languageComboBox.getValue() == null && !languageOptions.isEmpty()) {
            languageComboBox.setValue(languageOptions.get(0));
        }
    }

    /**
     * Carica le impostazioni correnti (da implementare con le tue preferenze)
     */
    private void loadCurrentSettings() {
        try {
            // TODO: Carica dalle preferenze effettive
            // PreferencesModel preferencesModel = PreferencesModel.getInstance();

            // Esempio di caricamento delle impostazioni
            // int savedBombCount = Integer.parseInt(preferencesModel.getPreference("bomb-count").toString());
            // bombCountSlider.setValue(savedBombCount);

            // String savedLanguage = preferencesModel.getCurrentLanguage();
            // if (AVAILABLE_LANGUAGES.containsKey(savedLanguage)) {
            //     languageComboBox.setValue(AVAILABLE_LANGUAGES.get(savedLanguage));
            // }

            System.out.println("Impostazioni caricate (placeholder)");

        } catch (Exception e) {
            System.err.println("Errore nel caricamento delle impostazioni: " + e.getMessage());
            // Usa valori di default in caso di errore
            bombCountSlider.setValue(10);
            languageComboBox.setValue(AVAILABLE_LANGUAGES.get("it-CH"));
        }
    }

    /**
     * Gestisce il click del pulsante Salva
     */
    @FXML
    private void handleSave() {
        try {
            // Ottieni i valori correnti
            int bombCount = (int) bombCountSlider.getValue();
            String selectedLanguageName = languageComboBox.getValue();

            // Trova il codice della lingua
            String languageCode = getLanguageCodeFromName(selectedLanguageName);

            // Salva le impostazioni
            saveSettings(bombCount, languageCode);

            // Mostra messaggio di successo
            showSuccessAlert("Impostazioni salvate con successo!");

            settingsSaved = true;

            // Chiudi la finestra
            closeWindow();

        } catch (Exception e) {
            System.err.println("Errore nel salvataggio: " + e.getMessage());
            showErrorAlert("Errore nel salvataggio delle impostazioni: " + e.getMessage());
        }
    }

    /**
     * Gestisce il click del pulsante Annulla
     */
    @FXML
    private void handleCancel() {
        // Chiedi conferma se ci sono modifiche non salvate
        boolean hasChanges = checkForUnsavedChanges();

        if (hasChanges) {
            Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmAlert.setTitle("Conferma");
            confirmAlert.setHeaderText("Modifiche non salvate");
            confirmAlert.setContentText("Sei sicuro di voler uscire senza salvare le modifiche?");

            ButtonType result = confirmAlert.showAndWait().orElse(ButtonType.CANCEL);

            if (result == ButtonType.OK) {
                closeWindow();
            }
        } else {
            closeWindow();
        }
    }

    /**
     * Salva le impostazioni (da implementare con il tuo sistema di preferenze)
     */
    private void saveSettings(int bombCount, String languageCode) {
        // TODO: Implementa il salvataggio effettivo
        // PreferencesModel preferencesModel = PreferencesModel.getInstance();
        // preferencesModel.setPreference("bomb-count", bombCount);
        // preferencesModel.setPreference("language-tag", languageCode);
        // preferencesModel.savePreferences();

        System.out.println("Salvando impostazioni:");
        System.out.println("- Numero bombe: " + bombCount);
        System.out.println("- Lingua: " + languageCode);

        // Simula il salvataggio
        try {
            Thread.sleep(500); // Simula operazione di salvataggio
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Trova il codice della lingua dal nome visualizzato
     */
    private String getLanguageCodeFromName(String languageName) {
        return AVAILABLE_LANGUAGES.entrySet()
                .stream()
                .filter(entry -> entry.getValue().equals(languageName))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse("it-CH"); // Default
    }

    /**
     * Controlla se ci sono modifiche non salvate
     */
    private boolean checkForUnsavedChanges() {
        // TODO: Implementa la logica per controllare se ci sono modifiche
        // Confronta i valori correnti con quelli salvati
        return !settingsSaved; // Placeholder
    }

    /**
     * Mostra un alert di successo
     */
    private void showSuccessAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Successo");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Mostra un alert di errore
     */
    private void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Errore");
        alert.setHeaderText("Si è verificato un errore");
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Chiude la finestra corrente
     */
    private void closeWindow() {
        Stage stage = (Stage) saveButton.getScene().getWindow();
        stage.close();
    }

    // Metodi getter per accedere ai valori (utili se carichi la finestra da un'altra classe)
    public int getBombCount() {
        return (int) bombCountSlider.getValue();
    }

    public String getSelectedLanguageCode() {
        String languageName = languageComboBox.getValue();
        return getLanguageCodeFromName(languageName);
    }

    public boolean wasSettingsSaved() {
        return settingsSaved;
    }

    // Metodi setter per impostare valori iniziali
    public void setBombCount(int bombCount) {
        if (bombCount >= 1 && bombCount <= 50) {
            bombCountSlider.setValue(bombCount);
        }
    }

    public void setSelectedLanguage(String languageCode) {
        if (AVAILABLE_LANGUAGES.containsKey(languageCode)) {
            languageComboBox.setValue(AVAILABLE_LANGUAGES.get(languageCode));
        }
    }
}