import java.io.Serializable;
import java.util.ArrayList;

public class Ticket implements Serializable {
    private Film film;
    private int numberOfTicket;
    public static final long serialVersionUID = 3L;
    private AlterSQLData alterSQLData;

    Ticket() {

    }

    public Ticket(Film film, int numberofTicket) {
        this.film = film;
        this.numberOfTicket = numberofTicket;
        updatedTotalTicket(film,numberOfTicket);
    }

   /* public Ticket(Film film, int price, int totalofTicket) {
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
    }*/

    private boolean updatedTotalTicket(Film film, int numberOfTicket) {
        int updatedTotalTicket = film.getTotalofTicket() - numberOfTicket;
        if (updatedTotalTicket>=0)  {
            film.setTotalofTicket(updatedTotalTicket);
            return true;
        } else {
            System.out.println("The Number of the Ticket is not enough.");
            return false;
        }



    }


    public int getNumberOfTicket() {
        return numberOfTicket;
    }


    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public void setNumberOfTicket(int numberOfTicket) {
        this.numberOfTicket = numberOfTicket;
    }

}
