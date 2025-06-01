package ch.supsi.frontend.model.gameMapperModel;

import java.io.File;

public interface IGameMapperModel {
    void save();

    void open(String fileName);

    void saveAs(File file);
}
