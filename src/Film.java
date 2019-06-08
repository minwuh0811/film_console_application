import java.io.Serializable;

public class Film implements Serializable {
    private static int ID;
    private String filmID;
    private String name;
    private int limitofage;
    public static final long serialVersionUID = 2L;

    public Film() {
    }

    public Film(String name, int limitofage) {
        this.filmID="FM"+ID;
        this.name=name;
        this.limitofage=limitofage;
    }

    public Film(String filmID, String name, int limitofage) {
        this.filmID=filmID;
        this.name=name;
        this.limitofage=limitofage;
    }

    public static int getID() {
        return ID;
    }

    public static void setID(int ID) {
        Film.ID = ID;
    }

    public String getFilmID() {
        return filmID;
    }

    public void setFilmID(String filmID) {
        this.filmID = filmID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLimitofage() {
        return limitofage;
    }

    public void setLimitofage(int limitofage) {
        this.limitofage = limitofage;
    }
}
