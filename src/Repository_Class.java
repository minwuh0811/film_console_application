import java.io.IOException;
import java.io.Writer;

public class Repository_Class implements Repository{

    public Repository_Class(Writer fw) {
        this.fw = fw;
    }

    private Writer fw;

    public Writer getFw() {
        return fw;
    }

    public void setFw(Writer fw) {
        this.fw = fw;
    }

    public void write(String text) {
        try {
            fw.write(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
