import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class Repository_ClassTest {
    private String path = System.getProperty("user.home")
            + java.io.File.separator + "IdeaProjects"
            + java.io.File.separator + "film_konsollapplikation"
            + java.io.File.separator + "test"
            + java.io.File.separator + "Resources";
    
    ticket.Repository mock=mock(ticket.Repository.class);
    @Test
    void saveTofile() {
        String string="WriteTest.txt";
        path=path + java.io.File.separator + string;
        java.io.File file = new java.io.File(path);
        try (FileWriter filewriter = new FileWriter(file)) {
            Main.WriteTextFile(mock,"the expected text");
            verify(mock).write( "the expected text");
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}