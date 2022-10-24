package view.panels;

import magasin.Magasin;
import mesproduits.Article;
import view.components.MyJPanel;

import java.awt.*;

public class Card extends MyJPanel {

    Article art;

    public Card(Article art, Magasin magasin) {
        super(new BorderLayout());
        this.art=art;
        magasin.listerStock();









    }
}
