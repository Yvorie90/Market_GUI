package view.panels.shop;

import magasin.Magasin;
import magasin.exceptions.ArticleHorsStockException;
import magasin.iArticle;
import view.components.MyJLabel;
import view.components.MyJPanel;

import javax.swing.*;
import java.awt.*;

public class LeftCardPanel extends MyJPanel {

    public LeftCardPanel(iArticle art, Magasin magasin) {
        super(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(30, 20, 30, 10));

        MyJLabel en_stock = new MyJLabel("En stock :");
        add(en_stock,BorderLayout.NORTH);


        MyJLabel stock;
        try {
            stock = new MyJLabel(String.valueOf(magasin.consulterQuantiteEnStock(art)));
        } catch (ArticleHorsStockException e) {
            stock = new MyJLabel("X");
        }
        add(stock,BorderLayout.SOUTH);

    }
}
