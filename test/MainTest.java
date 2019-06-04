import org.junit.jupiter.api.Test;

import java.io.File;


public class MainTest {
    String DBURL="jdbc:mysql://192.168.99.100:3306/film_Test";
    String DBUser="root";
    String DBPassword="password";
    static String path = System.getProperty("user.home")
            + java.io.File.separator + "IdeaProjects"
            + java.io.File.separator + "film_konsollapplikation"
            + java.io.File.separator + "test"
            + java.io.File.separator + "Resources";

    @Test
    void localDataSize(){
        Main.localData(DBURL,DBUser,DBPassword,path);

    }

}
