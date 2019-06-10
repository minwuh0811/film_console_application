import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FilmStorageTest {
    static String path = System.getProperty("user.home")
            + java.io.File.separator + "IdeaProjects"
            + java.io.File.separator + "film_konsollapplikation"
            + java.io.File.separator + "test"
            + java.io.File.separator + "Resources";

    @Test
    void addFilmToBinAndTxt() {
        FilmStorage filmStorage=new FilmStorage("filmAddBinAndTxt", path,"FM");
        Film film=new Film();
        filmStorage.addFilmDBSQL(film);
        assertEquals(2,filmStorage.getList().size());
    }


    @Test
    void searchFilmByFilmID() {
        FilmStorage filmStorage=new FilmStorage("Film",path,"FM");
        filmStorage.searchFilm("FM3", filmStorage.getList());
        assertEquals("FM3", filmStorage.getFilm().getFilmID());
    }

    @Test
    void returnFirstMissingIDForFilm() {
        FilmStorage filmStorage=new FilmStorage("Film",path,"FM");
        int ID=filmStorage.returnID();
        assertEquals(2, ID);
    }


    }
