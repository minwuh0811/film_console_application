import java.util.ArrayList;

public class AdministratorStorage {
    private Administrator administrator;
    private ArrayList<Administrator> administratorlist= new ArrayList<>();

    public ArrayList<Administrator> getAdministratorlist() {
        return administratorlist;
    }

    public void addAdministrator(Administrator administrator) {
        administratorlist.add(administrator);
    }
}
