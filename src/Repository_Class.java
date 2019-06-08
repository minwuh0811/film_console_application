import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class Repository_Class {
    private Writer fw;

    public Writer getFw() {
        return fw;
    }

    public void setFw(Writer fw) {
        this.fw = fw;
    }

    public Repository_Class(){

    }

    public Repository_Class(Writer fw) {
        this.fw = fw;
    }

    public void write(String text) {
        try {
            fw.write(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void SaveTofile(java.io.File file, String text) {
        try (
                FileWriter filewriter = new FileWriter(file)) {
            Repository_Class repository = new Repository_Class(filewriter);
            Main.WriteTextFile(repository, text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
