package ch.supsi.backend.business;

import java.io.*;
import java.util.Properties;

public class PropertiesController {


    public static String[] readFileProperties() throws IOException {
        Properties prop = new Properties();
        String propFilePath = System.getProperty("user.home") + File.separator + ".properties";
        File proprFile = new File(propFilePath);
        if (!proprFile.exists()) {
            proprFile.createNewFile();
            try (FileOutputStream fos = new FileOutputStream(proprFile)) {
                prop.setProperty("bombNumber", "20");
                prop.setProperty("language", "IT");
                prop.store(fos, "File di configurazione creato automaticamente");

            }

        }
        try (FileInputStream fis = new FileInputStream(proprFile)) {
            prop.load(fis);
        }
        String[] content = new String[2];
        content[0] = prop.getProperty("bombNumber");
        content[1] = prop.getProperty("language");
        return content;
    }
}
