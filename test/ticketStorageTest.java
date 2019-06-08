import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ticketStorageTest {
    static String path = System.getProperty("user.home")
            + java.io.File.separator + "IdeaProjects"
            + java.io.File.separator + "film_konsollapplikation"
            + java.io.File.separator + "test"
            + java.io.File.separator + "Resources";

    @Test
    void searchTicketByTicketID() {
            TicketStorage ticketStorage=new TicketStorage("Ticket",path,"TK");
            ticketStorage.searchTicket("FM3", ticketStorage.getList());
            assertEquals("TK3", ticketStorage.getTicket().getTicketID());
        }


    @Test
    void close() {
    }

    @Test
    void addTicketToBinAndTxt() {
        TicketStorage ticketStorage=new TicketStorage("ticketAddBinAndTxt", path,"TK");
        Ticket ticket=new Ticket();
        System.out.println(ticketStorage.getList().size());
        ticketStorage.addTicketDBSQL(ticket);
        assertEquals(2,ticketStorage.getList().size());
    }

    @Test
    void removeTicket() {
        TicketStorage ticketStorage=new TicketStorage("ticketRemoveTest", path,"TK");
        ticketStorage.removeTicket(ticketStorage.getList().get(0));
        assertEquals(0,ticketStorage.getList().size());
    }

    @Test
    void searchTicketTK3() {
        TicketStorage ticketStorage=new TicketStorage("Ticket", path,"TK");
        ticketStorage.searchTicket("FM3", ticketStorage.getList());
        assertEquals("TK3",ticketStorage.getTicket().getTicketID());
    }

    @Test
    void returnFirstMissingIDForTicket() {
        TicketStorage ticketStorage= new TicketStorage("Ticket", path,"TK");
        int ID=ticketStorage.returnID();
        assertEquals(2, ID);
    }



}