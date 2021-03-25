package model;

public class Ville {
    int id;
    String nom;
    int province;

    public Ville(String nom, int province) {
        setNom(nom);
        setProvince(province);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getProvince() {
        return province;
    }

    public void setProvince(int province) {
        this.province = province;
    }
}
