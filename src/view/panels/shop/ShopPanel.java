package view.panels.shop;

import magasin.Magasin;
import magasin.iArticle;
import mesproduits.Article;
import monapplication.Client;
import view.GUI;
import view.components.MyJPanel;
import view.panels.MainPanel;
import view.panels.panier.ContainerPanierPanel;
import view.panels.panier.PanierPanel;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

import static view.GUI.BACKGROUND_COLOR;

public class ShopPanel extends MyJPanel {

    Magasin magasin;

    public ShopPanel(Magasin magasin, Client client) {
        super(new GridLayout(0, 3, 15, 15));
        this.magasin = magasin;


        setBackground(BACKGROUND_COLOR);
        setOpaque(true);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        populate(magasin.listerArticlesEnStockParNom(), client);
    }



    public void populate(List<iArticle> list, Client client){
        for (iArticle i : list){
            Article a = (Article) i;

            add(new Card(a,magasin,client));
        }
    }

    public void reload_shop(Magasin magasin){
        Arrays.stream(getComponents()).toList().forEach(i -> {
            Card card = (Card) i;
            card.reload_model_ShopCard(magasin);
        });

    }


}
