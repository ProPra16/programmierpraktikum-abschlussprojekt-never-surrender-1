package tde.file;

import java.nio.file.Path;

public class FileHandler {
    private Path rootDirectory;

    public FileHandler(Path rootDirectory){
        this.rootDirectory = rootDirectory;
    }

    public void addFile(String fileName){

    }

    public void deleteFile(String fileName){

    }

    public void write(String fileName, String[] lines){

    }

    public String[] read(String fileName){
        return new String[0];
    }
}
