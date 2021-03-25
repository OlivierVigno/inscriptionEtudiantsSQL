package model;

public class Etudiant {
    int id;
    String nom;
    String prenom;
    int noCivique;
    String rue;
    int ville;
    int province;
    String telephone;
    int programme;

    public Etudiant() {

    }

    public Etudiant(String nom, String prenom, int noCivique, String rue, int ville, int province, String telephone, int programme) {
        setNom(nom);
        setPrenom(prenom);
        setNoCivique(noCivique);
        setRue(rue);
        setVille(ville);
        setProvince(province);
        setTelephone(telephone);
        setProgramme(programme);
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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getNoCivique() {
        return noCivique;
    }

    public void setNoCivique(int noCivique) {
        this.noCivique = noCivique;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public int getVille() {
        return ville;
    }

    public void setVille(int ville) {
        this.ville = ville;
    }

    public int getProvince() {
        return province;
    }

    public void setProvince(int province) {
        this.province = province;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public int getProgramme() {
        return programme;
    }

    public void setProgramme(int programme) {
        this.programme = programme;
    }
}
