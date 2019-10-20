package todolist.ui;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class SaveLoad {

    public static void saveToFile(String data) {
        String path = System.getProperty("user.dir") + "/save.txt";
        try {
            Files.write(Paths.get(path), data.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String> loadFile(){
        String path = System.getProperty("user.dir") + "/save.txt";
        try {
            return Files.readAllLines(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
