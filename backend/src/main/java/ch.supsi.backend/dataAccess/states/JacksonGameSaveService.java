package ch.supsi.backend.dataAccess.states;

import ch.supsi.backend.business.dto.GameStateBusiness;
import ch.supsi.backend.business.dto.IGameStateBusiness;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JacksonGameSaveService implements GameSaveData {
    private final ObjectMapper objectMapper;
    private final String saveDirectory;

    public JacksonGameSaveService(String saveDirectory) {
        this.saveDirectory = saveDirectory;
        this.objectMapper = new ObjectMapper();
        this.objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        // Crea la directory di salvataggio se non esiste
        createSaveDirectoryIfNotExists();
    }

    private void createSaveDirectoryIfNotExists() {
        try {
            Path path = Paths.get(saveDirectory);
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }
        } catch (IOException e) {
            throw new RuntimeException("Impossibile creare la directory di salvataggio", e);
        }
    }

    @Override
    public void saveGame(IGameStateBusiness gameState, String fileName){
        try {
            File saveFile = new File(saveDirectory, fileName + ".json");
            objectMapper.writeValue(saveFile, gameState);
        } catch (IOException e) {
            System.out.println("Errore durante il salvataggio del gioco: " + fileName);
        }
    }

    @Override
    public GameStateBusiness loadGame(String fileName) {
        try {
            File saveFile = new File(saveDirectory, fileName + ".json");
            if (!saveFile.exists()) {
                System.out.println("File di salvataggio non trovato: " + fileName);
            }
            return objectMapper.readValue(saveFile, GameStateBusiness.class);
        } catch (IOException e) {
            System.out.println("Errore durante il caricamento del gioco: " + fileName);
        }
        return null;
    }

    @Override
    public boolean saveExists(String fileName) {
        File saveFile = new File(saveDirectory, fileName + ".json");
        return saveFile.exists();
    }

    @Override
    public void deleteSave(String fileName) {
        try {
            File saveFile = new File(saveDirectory, fileName + ".json");
            if (saveFile.exists() && !saveFile.delete()) {
                System.out.println("Impossibile eliminare il file di salvataggio: " + fileName);
            }
        } catch (Exception e) {
            System.out.println("Errore durante l'eliminazione del salvataggio: " + fileName);
        }
    }
}
