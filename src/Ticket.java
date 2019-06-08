import java.io.Serializable;

public class Ticket implements Serializable {
    private static int ID;
    private String ticketID;
    private Film film;
    private int price;
    private int numberOfTicket;
    static int TotalofTicket;
    public static final long serialVersionUID = 3L;

    Ticket(){

    }

    public Ticket(Film film, int numberofTicket){
        this.film=film;
        this.numberOfTicket=numberofTicket;
        TotalofTicket-=numberOfTicket;
    }

    public Ticket(Film film, int price, int totalofTicket) {
        ID++;
        this.ticketID="TK"+ ID;
        this.film=film;
        this.price=price;
        TotalofTicket=totalofTicket;
    }

    public Ticket(String ticketID, Film film, int price, int totalofTicket) {
        this.ticketID=ticketID;
        this.film=film;
        this.price=price;
        TotalofTicket=totalofTicket;
    }

    public int getNumberOfTicket() {
        return numberOfTicket;
    }

    public static int getID() {
        return ID;
    }

    public static void setID(int ID) {
        Ticket.ID = ID;
    }

    public String getTicketID() {
        return ticketID;
    }

    public void setTicketID(String ticketID) {
        this.ticketID = ticketID;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}
