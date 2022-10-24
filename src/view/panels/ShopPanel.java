package view.panels;

import magasin.Magasin;
import magasin.iArticle;
import mesproduits.Article;
import view.components.MyJPanel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import static view.GUI.BACKGROUND_COLOR;

public class ShopPanel extends MyJPanel {

    Magasin magasin;

    public ShopPanel(Magasin magasin) {
        super(new GridLayout(0, 3, 15, 15));
        this.magasin = magasin;


        setBackground(BACKGROUND_COLOR);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        populate(magasin.listerArticlesEnStockParNom());
    }

    public void populate(List<iArticle> list){
        for (iArticle i : list){
            Article a = (Article) i;

            add(new Card(a),magasin);



        }

    }

}
