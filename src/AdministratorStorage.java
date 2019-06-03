import java.io.*;
import java.util.ArrayList;

public class AdministratorStorage implements AdministratorInterface {
    private Administrator administrator;
    private ArrayList<Administrator> administratorlist= new ArrayList<>();
    private String file_name;
    private String path;

    public AdministratorStorage() {

    }

    public AdministratorStorage(String file_name, String path) {
        this.file_name=file_name;
        this.path=path;
        LoadAdminFile(file_name,path);
    }


    private void saveToAdminFile(String file_name, String path) {
        String file1=file_name+".bin";
        String path_bin = path + java.io.File.separator + file1;
        java.io.File file_Customer = new java.io.File(path_bin);
        String file2=file_name+".txt";
        String path_txt = path + java.io.File.separator + file2;
        java.io.File file = new java.io.File(path_txt);
        int length=administratorlist.size();
        String text="";
        for (int i=0; i<length; i++) {
            text+="" + administratorlist.get(i).getAdministratorID() + ' '
                    + administratorlist.get(i).getFirstname() + ' '
                    + administratorlist.get(i).getLastname() +' '
                    + administratorlist.get(i).getAdministratorName() + ' '
                    + administratorlist.get(i).getPassword() +'\n';        }

        try (FileWriter filewriter = new FileWriter(file)) {
            Repository repository=new Repository_Class(filewriter);
            Main.WriteTextFile(repository,text);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (ObjectOutputStream out =
                     new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file_Customer)))) {
            out.writeObject(administratorlist);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void LoadAdminFile(String file_name, String path) {
        String file = file_name + ".bin";
        path = path + java.io.File.separator + file;
        java.io.File file_Customer = new java.io.File(path);
        try (ObjectInputStream in =
                     new ObjectInputStream(new BufferedInputStream(new FileInputStream(file_Customer)))) {
            administratorlist = (ArrayList<Administrator>) in.readObject();
        } catch (FileNotFoundException e) {

        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {

        }
        try {
            Administrator.setID(administratorlist.size());
        } catch ( java.lang.IndexOutOfBoundsException e){
            Administrator.setID(0);
        }

    }


    private void close(){
        saveToAdminFile(file_name,path);
//        saveToOrderSearchFile();
    }



    public ArrayList<Administrator> getAdministratorlist() {
        return administratorlist;
    }
    public void addAdministratorSQLDB(Administrator administrator) {
        administratorlist.add(administrator);
    }

    public void addAdministrator(Administrator administrator) {
        administratorlist.add(administrator);
        saveToAdminFile(file_name,path);
        close();
    }


}
