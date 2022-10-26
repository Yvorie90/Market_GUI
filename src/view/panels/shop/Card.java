package view.panels.shop;

import magasin.Magasin;
import magasin.iArticle;
import mesproduits.Article;
import monapplication.Client;
import view.components.MyJLabel;
import view.components.MyJPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

import static view.GUI.BACKGROUND_COLOR_2;
import static view.GUI.KAMA_IMG;

public class Card extends MyJPanel {

    public Article article;
    LeftCardPanel leftCardPanel;
    RightCardPanel rightCardPanel;


    public Card(Article article, Magasin magasin, Client client) {
        super(new BorderLayout());
        this.article =article;
        magasin.listerStock();

        setPreferredSize(new Dimension(300,150));
        setBackground(BACKGROUND_COLOR_2);
        setOpaque(true);

        MyJLabel img_item;
        try {
            img_item = new MyJLabel(new ImageIcon(ImageIO.read(new URL(article.image_url()))));
        } catch (IOException e) {
            img_item = new MyJLabel("Image indisponible");
            System.out.println("Image shop indisponible :\n" + e.getMessage());
        }
        add(img_item,BorderLayout.CENTER);


        leftCardPanel = new LeftCardPanel(article,magasin);
        add(leftCardPanel,BorderLayout.WEST);

        rightCardPanel = new RightCardPanel(magasin,article,client);
        add(rightCardPanel, BorderLayout.EAST);



        MyJPanel item_info = new MyJPanel();
        item_info.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        MyJLabel info_txt = new MyJLabel(article.nom() +"  |  "+(article.prix()));
        item_info.add(info_txt);
        item_info.add(new MyJLabel(KAMA_IMG));



        add(item_info,BorderLayout.SOUTH);



    }

    public void reload_model_ShopCard(Magasin magasin){
        leftCardPanel.reload_stockLabel(magasin, article);
        rightCardPanel.reload_spinner(magasin, article);
    }



}
