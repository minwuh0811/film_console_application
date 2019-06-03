import java.util.ArrayList;

public class ticketStorage {
    private ticket ticket;
    private ArrayList<ticket> ticketlist=new ArrayList<>();

    public ArrayList<ticket> getTicketlist() {
        return ticketlist;
    }

    public void addTicket(ticket ticket) {
        ticketlist.add(ticket);
    }
    public void removeTicket(ticket ticket) {
        ticketlist.remove(ticket);
    }
}
