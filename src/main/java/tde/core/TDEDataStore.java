package tde.core;

public class TDEDataStore {
    private String workspace;
    private String projectName;
    private String activFile;

    private String absolutPath;

    public static final String separator = System.getProperty("file.separator");

    public void setWorkspace(String workspace){
        this.workspace = workspace;
        this.absolutPath = workspace;
    }

    public String getWorkspace() {
        return workspace;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
        absolutPath = absolutPath + separator + projectName;
    }

    public String getAktivFile() {
        return activFile;
    }

    public void setAktivFile(String aktivFile) {
        this.activFile = aktivFile;
        absolutPath = absolutPath + separator + aktivFile;
    }

    public String getAbsolutPath() {
        return absolutPath;
    }
}
