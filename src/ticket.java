import java.io.Serializable;

public class ticket implements Serializable {
    private static int ID;
    private String ticketID;
    private film film;
    private double price;
    private int numberOfTicket;
    static int TotalofTicket;
    public static final long serialVersionUID = 3L;

    public ticket(film film, int numberOfTicket){
        this.film=film;
        TotalofTicket-=numberOfTicket;
    }

    public ticket(film film, double price, int totalofTicket) {
        ID+=1;
        this.ticketID="TK"+ID;
        this.film=film;
        this.price=price;
        TotalofTicket=totalofTicket;
    }

    public ticket(String ticketID, film film, double price, int totalofTicket) {
        this.ticketID=ticketID;
        this.film=film;
        this.price=price;
        TotalofTicket=totalofTicket;
    }

    public String getTicketID() {
        return ticketID;
    }

    public void setTicketID(String ticketID) {
        this.ticketID = ticketID;
    }

    public film getFilm() {
        return film;
    }

    public void setFilm(film film) {
        this.film = film;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getNumberOfTicket() {
        return numberOfTicket;
    }

    public void setNumberOfTicket(int numberOfTicket) {
        this.numberOfTicket = numberOfTicket;
    }

}
