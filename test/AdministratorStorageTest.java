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

    }
