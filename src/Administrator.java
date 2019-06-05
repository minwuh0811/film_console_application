import java.io.Serializable;

public class Administrator implements Serializable {
    private String Firstname;
    private String Lastname;
    private String AdministratorName;
    private String password;
    private String AdministratorID;
    public static final long serialVersionUID = 1L;
    private static int ID;

    public Administrator(){}

    public Administrator(String Firstname,String Lastname, String AdministratorName, String password) {
        this.AdministratorID="AD"+ ID++;
        this.Firstname=Firstname;
        this.Lastname=Lastname;
        this.AdministratorName=AdministratorName;
        this.password=password;
    }

    public Administrator(String AdministratorID, String Firstname,String Lastname, String AdministratorName, String password) {
        this.AdministratorID=AdministratorID;
        this.Firstname=Firstname;
        this.Lastname=Lastname;
        this.AdministratorName=AdministratorName;
        this.password=password;
    }


    public String getAdministratorID() {
        return AdministratorID;
    }

    public String getFirstname() {
        return Firstname;
    }

    public void setFirstname(String firstname) {
        Firstname = firstname;
    }

    public String getLastname() {
        return Lastname;
    }

    public void setLastname(String lastname) {
        Lastname = lastname;
    }

    public String getAdministratorName() {
        return AdministratorName;
    }

    public void setAdministratorName(String administratorName) {
        AdministratorName = administratorName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static int getID() {
        return ID;
    }

    public static void setID(int ID) {
        Administrator.ID = ID;
    }
}
