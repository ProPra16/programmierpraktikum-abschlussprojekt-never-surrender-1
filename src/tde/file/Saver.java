package tde.file;

import java.io.BufferedWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;

public abstract class Saver {
    public static void saveToFile(String[] lines, Path filePath){
        Charset charset = Charset.forName("US-ASCII");
        try(BufferedWriter writer = Files.newBufferedWriter(filePath, charset)) {
            for(String s: lines){
                writer.write(s);
                writer.newLine();
            }
            writer.close();
        } catch (Exception ex) {}
    }
}
