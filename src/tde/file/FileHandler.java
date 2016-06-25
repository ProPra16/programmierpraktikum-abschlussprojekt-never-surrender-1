package tde.file;

import java.net.URI;
import java.nio.file.*;

public class FileHandler {
    private Path workspace;
    private Loader loader;
    private Saver saver;

    public FileHandler(Path workspace){
        this.workspace = workspace;
        loader = new Loader();
        saver = new Saver();
    }

    public void addFile(String fileName){

    }

    public void deleteFile(String fileName){

    }

    public void write(String fileName, String[] lines){
        saver.saveToFile(lines, Paths.get(URI.create(workspace.toString() + fileName)));
    }

    public String[] read(String fileName){
        return loader.readFromFile(Paths.get(URI.create(workspace.toString() + fileName)));
    }
}
