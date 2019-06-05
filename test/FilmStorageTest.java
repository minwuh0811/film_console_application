import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FilmStorageTest {
    private String path = System.getProperty("user.home")
            + java.io.File.separator + "IdeaProjects"
            + java.io.File.separator + "film_konsollapplikation"
            + java.io.File.separator + "test"
            + java.io.File.separator + "Resources";

    @Test
    void addFilmDBSQL() {
        FilmStorage filmStorage=new FilmStorage("Film", path);
        film film=new film();
        filmStorage.addFilmDBSQL(film);
        assertEquals(2,filmStorage.getFilmlist().size());
        }

    @Test
    void removefilm() {
        FilmStorage filmStorage=new FilmStorage("Film",path);
        film film=new film();
        filmStorage.removefilm(film);
        assertEquals(0,filmStorage.getFilmlist().size());
    }



    @Test
    void searchFilm() {
    }
}