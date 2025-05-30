package ch.supsi.backend.dataAccess.preferences;

import ch.supsi.backend.business.preferences.PreferencesDataAccessInterface;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class PreferencesPropertiesDataAccess implements PreferencesDataAccessInterface {
    private static final String defaultPreferencesPath = "/default-user-preferences.properties";
    private static final String userHomeDirectory = System.getProperty("user.home");
    private static final String preferencesDirectory = ".mineSweaper";
    private static final String preferencesFile = "preferences.properties";

    public static PreferencesPropertiesDataAccess dao;
    private static Properties userPreferences;

    // protected default constructor to avoid a new instance being requested from clients
    protected PreferencesPropertiesDataAccess() {
    }

    // singleton instantiation of this data access object
    // guarantees only a single instance exists in the life of the application
    public static PreferencesPropertiesDataAccess getInstance() {
        if (dao == null) {
            dao = new PreferencesPropertiesDataAccess();
        }
        return dao;
    }

    //questo metodo serve per unire i due path userHomeDirectory e preferences directory
    private Path getUserPreferencesDirectoryPath() {
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
        try {
            // CORREZIONE: usa defaultPreferencesPath invece di getUserPreferencesDirectoryPath()
            InputStream defaultPreferencesStream = this.getClass().getResourceAsStream(defaultPreferencesPath);
            if (defaultPreferencesStream != null) {
                defaultPreferences.load(defaultPreferencesStream);
                defaultPreferencesStream.close();
            } else {
                // Se non trova il file di default, crea preferenze di base
                System.out.println("File di preferenze di default non trovato, uso valori predefiniti");
                defaultPreferences.setProperty("language-tag", "it-IT"); // valore di default
                defaultPreferences.setProperty("bomb-number", "20");
            }
        } catch (IOException e) {
            System.err.println("Errore nel caricamento delle preferenze di default: " + e.getMessage());
            // Crea preferenze di base in caso di errore
            defaultPreferences.setProperty("language-tag", "it-IT");
            defaultPreferences.setProperty("bomb-number", "20");

        }

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

    //Metodo per fornire le preferenze
    @Override
    public Properties getPreferences() {
        if (userPreferences != null) {
            return userPreferences;
        }

        // se esiste il file di preferenze dell'utente, caricalo
        if (userPreferencesFileExists()) {
            userPreferences = this.loadPreferences(this.getUserPreferencesFilePath());
            if (userPreferences != null) {
                return userPreferences;
            }
        }

        // se non esiste il file o c'è stato un errore nel caricamento,
        // carica le preferenze di default
        Properties defaultPreferences = this.loadDefaultPreferences();

        // crea il file delle preferenze dell'utente con i valori di default
        if (this.createUserPreferencesFile(defaultPreferences)) {
            userPreferences = defaultPreferences;
        } else {
            // se fallisce la creazione del file, usa comunque le preferenze di default
            userPreferences = defaultPreferences;
        }

        return userPreferences;
    }
}