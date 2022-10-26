package view.panels.shop;

import magasin.Magasin;
import magasin.exceptions.ArticleHorsStockException;
import magasin.iArticle;
import mesproduits.Article;
import view.components.MyJLabel;
import view.components.MyJPanel;

import javax.swing.*;
import java.awt.*;

public class LeftCardPanel extends MyJPanel {

    MyJLabel stock;


    public LeftCardPanel(iArticle art, Magasin magasin) {
        super(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(30, 20, 30, 10));

        MyJLabel en_stock = new MyJLabel("En stock :");
        add(en_stock,BorderLayout.NORTH);


        try {
            int stck = magasin.consulterQuantiteEnStock(art);
            if (stck==0)
                stock = new MyJLabel("- article hors stock -");
            else
                stock = new MyJLabel(String.valueOf(stck));
        } catch (ArticleHorsStockException e) {
            stock = new MyJLabel("X");
        }
        add(stock,BorderLayout.SOUTH);

    }

    public void reload_stockLabel(Magasin magasin, iArticle article){
        try {
            int stck = magasin.consulterQuantiteEnStock(article);
            if (stck==0)
                stock.setText("- article hors stock -");
            else
                stock.setText(String.valueOf(stck));
        } catch (ArticleHorsStockException e) {
            stock.setText("X");
        }
    }

}
