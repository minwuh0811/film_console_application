import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class AdministratorStorageTest {
    private String path = System.getProperty("user.home")
            + java.io.File.separator + "IdeaProjects"
            + java.io.File.separator + "film_konsollapplikation"
            + java.io.File.separator + "test"
            + java.io.File.separator + "Resources";

    @Test
    void addAdministratorSQLDB() {
        AdministratorStorage administratorStorage=new AdministratorStorage("adminAddBinAndTxt",path,"AD");
        int length=administratorStorage.getList().size();
        administratorStorage.addAdministratorSQLDB(new Administrator());
        assertEquals(2,administratorStorage.getList().size());
    }



    }
