package tde.file;

import java.io.BufferedReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Loader {
    public String[] readFromFile(Path filePath){
        Charset charset = Charset.forName("US-ASCII");
        List<String> lines = new ArrayList<>();
        try(BufferedReader reader = Files.newBufferedReader(filePath, charset)){
            String line;
            while((line = reader.readLine()) != null)
                lines.add(line);
            reader.close();
        } catch (Exception ex) {}
        return lines.toArray(new String[lines.size()]);
    }
}
