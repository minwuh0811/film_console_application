import java.util.ArrayList;

public class Print {
    ArrayList<Film> films;
    ArrayList<Ticket> tickets;

    public Print (ArrayList<Film> films) {
        this.films=films;
        toPrintFilm();
    }


    private void toPrintFilm() {
        for (Film film:films) {
            System.out.println("FilmID: " +film.getFilmID() + " FilmName: " + film.getName()+ " FilmPrice: " + film.getPrice() + " Number of Ticket: " + film.getTotalofTicket()+ " left");
        }
    }

    public void toPrintTicket(TicketStorage ticketStorage){
        for (Ticket ticket:ticketStorage.getList()){
            System.out.println("FilmName: " + ticket.getFilm().getName());

        }
        System.out.println("Totalprice");
    }
}
