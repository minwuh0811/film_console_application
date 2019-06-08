import java.io.*;
import java.util.ArrayList;

public class AdministratorStorage extends Storage implements AdministratorInterface  {
    private Administrator administrator;
    private ArrayList<Administrator> list=new ArrayList<>();

    public AdministratorStorage() {

    }

    public AdministratorStorage(String file_name, String path,String string) {
            super(file_name,path,string);
            LoadAdminFile(file_name,path);
        }
    public ArrayList<String> getIDlist() {
        return IDlist;
    }



    public ArrayList<Administrator> getList() {
        return list;
    }


    private void saveToAdminFile(String file_name, String path) {
        String file1=file_name+".bin";
        String path_bin = path + java.io.File.separator + file1;
        java.io.File file_Customer = new java.io.File(path_bin);
        String file2=file_name+".txt";
        String path_txt = path + java.io.File.separator + file2;
        java.io.File file = new java.io.File(path_txt);
        int length=list.size();
        String text="";
        for (int i=0; i<length; i++) {
            text+="" + list.get(i).getAdministratorID() + ' '
                    + list.get(i).getFirstname() + ' '
                    + list.get(i).getLastname() +' '
                    + list.get(i).getAdministratorName() + ' '
                    + list.get(i).getPassword() +'\n';        }

        Repository_Class repository= new Repository_Class();
        repository.SaveTofile(file,text);

        try (ObjectOutputStream out =
                     new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file_Customer)))) {
            out.writeObject(list);
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
            list = (ArrayList<Administrator>) in.readObject();
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
        list.add(administrator);
        IDlist.add(administrator.getAdministratorID());
    }

}


