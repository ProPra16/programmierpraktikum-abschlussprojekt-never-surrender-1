package tde.file;

import java.nio.file.Path;

public class FileHandler {
    private Path rootDirectory;

    public FileHandler(Path rootDirectory){
        this.rootDirectory = rootDirectory;
    }
}
