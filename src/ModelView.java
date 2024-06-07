import java.util.HashMap;

public class ModelView {
    String name;
    HashMap<String,Object> list = new  HashMap<>();

    public ModelView(String name, HashMap<String, Object> list) {
        this.name = name;
        this.list = list;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<String, Object> getList() {
        return list;
    }

    public void setList(HashMap<String, Object> list) {
        this.list = list;
    }
    
    public void addObject(String getKey, Object o) {
        list.put(getKey, o);
    }
}