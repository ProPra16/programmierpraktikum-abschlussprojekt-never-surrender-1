package tde.core;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TDEDataStore {
    private File workspace;
    private String projectName;
    private File projectFolder;
    private File activFile;

    public static final String separator = System.getProperty("file.separator");

    public void setWorkspace(File workspace){
        this.workspace = workspace;
    }

    public File getWorkspace() {
        return workspace;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setProjectFolder(File folder){
        this.projectFolder = folder;
    }

    public File getProjectFolder() {
        return projectFolder;
    }

    public File getAktivFile() {
        return activFile;
    }

    /**
     *
     * @return returns the absolute path to the current active file
     */
    public Path getAbsoluteActiveFilePath(){
        return Paths.get(workspace.getAbsolutePath(), projectFolder.getName(), activFile.getName());
    }

    public String getFilePathAsString(){
        return String.valueOf(getAbsoluteActiveFilePath());
    }

    // MIT .xml !!!
    public void setAktivFile(File aktivFile) {
        this.activFile = aktivFile;
    }

    public Path getAbsoluteProjectPath() {
        return Paths.get(workspace.getName(), projectName);
    }
}
