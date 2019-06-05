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

    public ArrayList<Administrator> getAdministratorlist() {
        return administratorlist;
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

        Repository_Class repository= new Repository_Class();
        repository.SaveTofile(file,text);

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
    }

    public void close(String file_name,String path){
        saveToAdminFile(file_name,path);
//        saveToOrderSearchFile();
    }

    public void addAdministratorSQLDB(Administrator administrator) {
        administratorlist.add(administrator);
    }

}
