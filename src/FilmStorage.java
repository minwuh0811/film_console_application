import java.util.ArrayList;

public class FilmStorage {
    private film film;
    private ArrayList<film> filmlist=new ArrayList<>();

    public void addFilm(film film) {
        Main main=new Main();
        filmlist.add(film);
    }
}
