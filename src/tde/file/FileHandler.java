package tde.file;

import java.net.URI;
import java.nio.file.*;

/**
 * Eine Klasse zum verwalten von Dateien
 */

public class FileHandler {
    private Path workspace;
    private Loader loader;
    private Saver saver;

    /**
     * Konstruktor der Klasse
     * @param workspace Das Schluesselverzeichnis des Arbeitsbereiches
     */
    public FileHandler(Path workspace){
        this.workspace = workspace;
        loader = new Loader();
        saver = new Saver();
    }

    /**
     * Erstellt eine neue Datei
     * @param fileName Dateiname und Pfad ab dem Workspace. Format: "/Ordner/Unterordner/.../Dateiname.Endung"
     */
    public void addFile(String fileName){

    }

    /**
     * Loescht einen bestehende Datei
     * @param fileName Dateiname und Pfad ab dem Workspace. Format: "/Ordner/Unterordner/.../Dateiname.Endung"
     */
    public void deleteFile(String fileName){

    }

    /**
     * Schreibt ein Stringarray von Zeilen in eine Datei
     * @param fileName Dateiname und Pfad ab dem Workspace. Format: "/Ordner/Unterordner/.../Dateiname.Endung"
     * @param lines Text als Zeilenarray
     */
    public void write(String fileName, String[] lines){
        saver.saveToFile(lines, Paths.get(URI.create(workspace.toString() + fileName)));
    }

    /**
     * Gibt ein Stringarray von Zeilen aus einern Datei zurueck
     * @param fileName Dateiname und Pfad ab dem Workspace. Format: "/Ordner/Unterordner/.../Dateiname.Endung"
     * @return Ein Zeilenarray
     */
    public String[] read(String fileName){
        return loader.readFromFile(Paths.get(URI.create(workspace.toString() + fileName)));
    }
}
