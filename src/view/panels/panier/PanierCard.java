package view.panels.panier;

import magasin.Magasin;
import magasin.exceptions.*;
import magasin.iArticle;
import mesproduits.Article;
import monapplication.Client;
import view.GUI;
import view.components.MyJButton;
import view.components.MyJLabel;
import view.components.MyJPanel;
import view.panels.MainPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

import static view.GUI.*;

public class PanierCard extends MyJPanel {

    Article article;
    int quantite;

    public PanierCard(Magasin magasin, Article article, int quantite, Client client) {
        super(new BorderLayout());
        this.article = article;
        this.quantite = quantite;
        setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        setPreferredSize(new Dimension(300,100));
        setBackground(BACKGROUND_COLOR);
        setOpaque(true);



        MyJPanel info_panel = new MyJPanel( new GridLayout(0,1,0,0));
        info_panel.add(new MyJLabel(article.nom()));
        info_panel.add(new MyJLabel("quantité : " + quantite));
        add(info_panel,BorderLayout.CENTER);




        try {
            add(new MyJLabel(new ImageIcon(ImageIO.read(new URL(article.image_url())))),BorderLayout.WEST);
        } catch (IOException e) {
            add(new MyJLabel("image \n indisponible"),BorderLayout.WEST);
        }


        MyJButton btn_annuler = new MyJButton(TRASHCAN_IMG);
        btn_annuler.setBackground(BACKGROUND_COLOR_2);
        btn_annuler.addActionListener(e -> {

            try {

                // control model
                magasin.supprimerDuPanier(client,quantite,article);


                //System.out.println(getParent().getParent().getParent().getParent());
                ((PurchaseOrderPanel)(getParent().getParent().getParent().getParent()).getComponent(2)).reload_montant(magasin,client);
                ((MainPanel)getParent().getParent().getParent().getParent().getParent()).reload_shop(magasin);


                ((PanierPanel) getParent()).delete_panier_card(this);


                // view




            } catch (ClientInconnuException ex) {
                ex.printStackTrace();
            } catch (QuantiteNegativeOuNulleException ex) {
                ex.printStackTrace();
            } catch (QuantiteSuppPanierException ex) {
                ex.printStackTrace();
            } catch (ArticleHorsPanierException ex) {
                ex.printStackTrace();
            } catch (ArticleHorsStockException ex) {
                ex.printStackTrace();
            }


        });


        MyJPanel contain_btn_annuler = new MyJPanel(new GridLayout());
        contain_btn_annuler.setPreferredSize(new Dimension(60,30));
        contain_btn_annuler.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        contain_btn_annuler.add(btn_annuler);
        add(contain_btn_annuler,BorderLayout.EAST);


    }



}
