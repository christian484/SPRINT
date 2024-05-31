package initiale;

public class Mapping {
    String nom_class;
    String nom_method;

    public void add(String nom1, String nom2) {
        this.nom_class = nom1;
        this.nom_method = nom2;
    }

    public String getValue() {
        return nom_method;
    }

    public String getKey() {
        return nom_class;
    }
}
