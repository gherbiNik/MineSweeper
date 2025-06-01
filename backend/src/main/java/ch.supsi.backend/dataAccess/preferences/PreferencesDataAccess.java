package ch.supsi.backend.dataAccess.preferences;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class PreferencesDataAccess implements PreferencesDataAccessInterface {

    private static final String userHomeDirectory = System.getProperty("user.home");
    private static final String preferencesDirectory = ".mineSweaper";
    private static final String preferencesFile = "preferences.properties";

    public static PreferencesDataAccess myself;
    private static Properties userPreferences;

    // protected default constructor to avoid a new instance being requested from clients
    private PreferencesDataAccess() {
    }

    // singleton instantiation of this data access object
    // guarantees only a single instance exists in the life of the application
    public static PreferencesDataAccess getInstance() {
        if (myself == null) {
            myself = new PreferencesDataAccess();
        }
        return myself;
    }

    //questo metodo serve per unire i due path userHomeDirectory e preferences directory
    public Path getUserPreferencesDirectoryPath() {
        return Path.of(userHomeDirectory, preferencesDirectory);
    }

    //mi dice se esiste preferences directory
    private boolean userPreferencesDirectoryExists() {
        return Files.exists(this.getUserPreferencesDirectoryPath());
    }

    // serve per creare la directory se non esiste
    private Path createUserPreferencesDirectory() {
        try {
            return Files.createDirectories(this.getUserPreferencesDirectoryPath());
        } catch (IOException e) {
            System.err.println("Errore nella creazione della directory delle preferenze: " + e.getMessage());
            return null;
        }
    }

    //restituisce il percorso del file finale
    private Path getUserPreferencesFilePath() {
        return Path.of(userHomeDirectory, preferencesDirectory, preferencesFile);
    }

    //vede se esiste il file di preferenze
    private boolean userPreferencesFileExists() {
        return Files.exists(this.getUserPreferencesFilePath());
    }

    private boolean createUserPreferencesFile(Properties defaultPreferences) {
        if (defaultPreferences == null) {
            return false;
        }

        if (!userPreferencesDirectoryExists()) {
            if (this.createUserPreferencesDirectory() == null) {
                return false;
            }
        }

        if (!userPreferencesFileExists()) {
            try {
                // create user preferences file (with default preferences)
                try (FileOutputStream outputStream = new FileOutputStream(this.getUserPreferencesFilePath().toFile())) {
                    defaultPreferences.store(outputStream, "User Preferences");
                }
                return true;
            } catch (IOException e) {
                System.err.println("Errore nella creazione del file delle preferenze: " + e.getMessage());
                return false;
            }
        }
        return true;
    }

    private Properties loadDefaultPreferences() {
        Properties defaultPreferences = new Properties();

        // Create basic default preferences
        defaultPreferences.setProperty("language-tag", "it-IT");
        defaultPreferences.setProperty("bomb-number", "20");

        return defaultPreferences;
    }

    //serve per caricare le preferenze
    private Properties loadPreferences(Path path) {
        Properties preferences = new Properties();
        try {
            try (FileInputStream fileInputStream = new FileInputStream(path.toFile())) {
                preferences.load(fileInputStream);
            }
        } catch (IOException e) {
            System.err.println("Errore nel caricamento delle preferenze: " + e.getMessage());
            return null;
        }
        return preferences;
    }

    //Metodo per fornire le preferenze - ALWAYS uses userHomeDirectory+preferencesDirectory+preferencesFile
    @Override
    public Properties getPreferences() {
        if (userPreferences != null) {
            return userPreferences;
        }

        // Always try to load from the user preferences file path first
        userPreferences = this.loadPreferences(this.getUserPreferencesFilePath());
        if (userPreferences != null) {
            return userPreferences;
        }

        // If user preferences file doesn't exist or failed to load,
        // load default preferences and create the user file
        Properties defaultPreferences = this.loadDefaultPreferences();

        // Create the user preferences file with default values
        if (this.createUserPreferencesFile(defaultPreferences)) {
            userPreferences = defaultPreferences;
        } else {
            // If file creation fails, still use the default preferences
            userPreferences = defaultPreferences;
        }

        return userPreferences;
    }

    @Override
    public void setPreference(String key, String string) {

        getPreferences().setProperty(key, string);
        
        savePreferences();
    }

    private void savePreferences() {
        if (userPreferences == null) {
            return;
        }

        try (FileOutputStream fos = new FileOutputStream(getUserPreferencesFilePath().toFile())) {
            userPreferences.store(fos, "User Preferences");
            System.out.println("Preferences saved successfully");
        } catch (IOException e) {
            System.err.println("Failed to save preferences: " + e.getMessage());
            e.printStackTrace();
        }
    }


}