public class ticket {
    private static int ID;
    private String ticketID;
    private film film;
    private double price;
    private int numberOfTicket;
    private static int TotalofTicket;

    public ticket(String ticketID, film film, double price, int numberOfTicket) {
        ID+=1;
        this.ticketID="TK"+ID;
        this.film=film;
        this.price=price;
        this.TotalofTicket-=numberOfTicket;
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

    public static int getTotalofTicket() {
        return TotalofTicket;
    }

    public static void setTotalofTicket(int totalofTicket) {
        ticket.TotalofTicket = totalofTicket;
    }

}
