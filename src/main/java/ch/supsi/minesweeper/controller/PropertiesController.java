package ch.supsi.minesweeper.controller;

import java.io.*;
import java.util.Properties;

public class PropertiesController {
    public static String[] readFileProperties() {
        Properties prop = new Properties();

        String propFilePath = System.getProperty("user.home") + File.separator + ".properties";

        FileInputStream fis = null;
        try {
            fis = new FileInputStream(propFilePath);
            prop.load(fis);
            fis.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        String[] content = new String[2];
        content[0] = prop.getProperty("bombNumber");
        content[1] = prop.getProperty("language");

        return content;
    }
}
