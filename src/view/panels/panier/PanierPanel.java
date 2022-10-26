package view.panels.panier;

import magasin.Magasin;
import mesproduits.Article;
import mesproduits.dofus.Dofus;
import monapplication.Client;
import view.GUI;
import view.components.MyJPanel;

import javax.swing.*;
import java.awt.*;

import static view.GUI.BACKGROUND_COLOR_2;

public class PanierPanel extends MyJPanel {


    public PanierPanel() {
        super(new GridLayout(0,1,0,10));

        setBackground(BACKGROUND_COLOR_2);
        setOpaque(true);
        setBorder(BorderFactory.createEmptyBorder(10,5,10,5));





    }

    public void create_panier_card(Magasin magasin, int quantite, Article art, Client client){

        PanierCard panierCard = new PanierCard(magasin, art, quantite, client);

        add(panierCard);
        revalidate();
        repaint();


    }



}
