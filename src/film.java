public class film {
    private static int ID;
    private String filmID;
    private String name;
    private int limitofage;
    public static final long serialVersionUID = 3L;

    public film() {
    }

    public film(String name, int limitofage) {
        ID+=1;
        this.filmID="FM"+ID;
        this.name=name;
        this.limitofage=limitofage;
    }

    public film(String filmID, String name, int limitofage) {
        this.filmID=filmID;
        this.name=name;
        this.limitofage=limitofage;
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
