import java.util.ArrayList;

public class Storage {
    protected ArrayList<String> IDlist=new ArrayList<>();
    protected ArrayList list=new ArrayList<>();
    private String file_name;
    private String path;
    protected String string;

    public Storage(){}

    public Storage(String file_name, String path, String string ) {
        this.file_name=file_name;
        this.path=path;
        this.string=string;
    }

    public ArrayList getList() {
        return list;
    }

    public void setList(ArrayList list) {
        this.list = list;
    }

    public void setIDlist(ArrayList<String> IDlist) {
        this.IDlist = IDlist;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int returnID() {
        int length = IDlist.size();
        int Result = -1;
        for (int n = 1; n <= length; n++) {
            String loop = string + n;
            if (!IDlist.get(n-1).equals(loop)) {
                Result = n;
                break;
            }
        }
        return Result;
    }


}
