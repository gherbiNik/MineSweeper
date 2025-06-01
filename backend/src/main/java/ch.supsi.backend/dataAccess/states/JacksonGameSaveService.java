package ch.supsi.backend.dataAccess.states;

import ch.supsi.backend.business.dto.GameStateBusiness;
import ch.supsi.backend.business.dto.IGameStateBusiness;
import ch.supsi.backend.dataAccess.preferences.PreferencesDataAccessInterface;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class JacksonGameSaveService implements GameSaveData {
    private static JacksonGameSaveService myself;
    private final ObjectMapper objectMapper;
    private final String saveDirectory = "saves";
    private final String saveFileName = "gameSaved";
    private PreferencesDataAccessInterface preferencesDataAccess;

    private JacksonGameSaveService() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    public static JacksonGameSaveService getInstance(PreferencesDataAccessInterface preferencesDataAccess) {
        if (myself == null) {
            myself = new JacksonGameSaveService();
            myself.preferencesDataAccess = preferencesDataAccess;
            myself.createSaveDirectoryIfNotExists();
        }
        return myself;
    }

    private void createSaveDirectoryIfNotExists() {
        try {
            Path path = Paths.get(preferencesDataAccess.getUserPreferencesDirectoryPath().toString(), saveDirectory);
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }
        } catch (IOException e) {
            throw new RuntimeException("Impossibile creare la directory di salvataggio", e);
        }
    }

    @Override
    public void saveGame(IGameStateBusiness gameState) {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        String formattedDateTime = localDateTime.format(formatter);
        String completeFileName = saveFileName + "_" + formattedDateTime + ".json";

        try {
            Path saveFilePath = Paths.get(preferencesDataAccess.getUserPreferencesDirectoryPath().toString(), saveDirectory, completeFileName);
            objectMapper.writeValue(saveFilePath.toFile(), gameState);
            System.out.println("Gioco salvato in: " + saveFilePath);
        } catch (IOException e) {
            System.out.println("Errore durante il salvataggio del gioco: " + e.getMessage());
        }
    }

    @Override
    public void saveGameAs(IGameStateBusiness gameState, File file) {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        String formattedDateTime = localDateTime.format(formatter);
        String completeFileName = saveFileName + "_" + formattedDateTime + ".json";

        try {
            Path saveFilePath = Paths.get(file.getPath(), completeFileName);
            objectMapper.writeValue(saveFilePath.toFile(), gameState);
            System.out.println("Gioco salvato in: " + saveFilePath);
        } catch (IOException e) {
            System.out.println("Errore durante il salvataggio del gioco: " + e.getMessage());
        }
    }

    @Override
    public GameStateBusiness loadGame(String fileName) {
        try {
            Path saveFilePath = Paths.get(fileName);
            if (!Files.exists(saveFilePath)) {
                System.out.println("File di salvataggio non trovato: " + fileName);
                throw new NoGameSavedEx("Nessun salvataggio presente");
            }
            return objectMapper.readValue(saveFilePath.toFile(), GameStateBusiness.class);
        } catch (IOException e) {
            System.out.println("Errore durante il caricamento del gioco: " + e.getMessage());
            return null;
        }
    }
}