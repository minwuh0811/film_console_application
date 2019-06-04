import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.mock;

class AdministratorStorageTest {
    private String path = System.getProperty("user.home")
            + java.io.File.separator + "IdeaProjects"
            + java.io.File.separator + "film_konsollapplikation"
            + java.io.File.separator + "test"
            + java.io.File.separator + "Resources";

    @Test
    void addAdministratorSQLDB() {
        AdministratorStorage administratorStorage=new AdministratorStorage("Administrator",path);
        int length=administratorStorage.getAdministratorlist().size();
        administratorStorage.addAdministratorSQLDB(new Administrator());
        assertEquals(4,administratorStorage.getAdministratorlist().size());

    }

    Repository mock=mock(Repository.class);
    @Test
    void close() {
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