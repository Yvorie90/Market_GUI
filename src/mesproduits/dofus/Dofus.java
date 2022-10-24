package mesproduits.dofus;

import magasin.iArticle;

public class Dofus  implements iArticle{

    private static int ID = 1;
    int id;
    String nom;
    double prix;
    String image_url;


    public Dofus(String nom, double prix, String URL_Img) {
        this.id = ID++;
        this.nom = nom;
        this.prix = prix;
        this.image_url = URL_Img;
    }

    @Override
    public int reference() {
        return id;
    }

    @Override
    public String nom() {
        return nom;
    }

    @Override
    public double prix() {
        return prix;
    }

    public String image_url(){
        return image_url;
    }

    @Override
    public String toString() {
        return "Dofus{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prix=" + prix +
                ", URL_Img='" + image_url + '\'' +
                '}';
    }
}
