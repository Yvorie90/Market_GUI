package mesproduits;

import magasin.iArticle;

public class Article implements iArticle {
    @Override
    public int reference() {
        return 0;
    }

    @Override
    public String nom() {
        return null;
    }

    @Override
    public double prix() {
        return 0;
    }
}
