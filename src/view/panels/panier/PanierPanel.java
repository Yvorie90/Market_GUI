package view.panels.panier;

import magasin.Magasin;
import mesproduits.Article;
import mesproduits.dofus.Dofus;
import monapplication.Client;
import view.GUI;
import view.components.MyJPanel;

import javax.swing.*;
import java.awt.*;

import static java.awt.Frame.MAXIMIZED_HORIZ;
import static view.GUI.BACKGROUND_COLOR_2;

public class PanierPanel extends MyJPanel {

    public static int number = 0;
    public static GridBagConstraints gbc = new GridBagConstraints();
    private static final Insets DEFAULT_INSETS = new Insets(5, 5, 5, 5);


    public PanierPanel() {
        super(new GridBagLayout());

        setBackground(BACKGROUND_COLOR_2);
        setOpaque(true);
        setBorder(BorderFactory.createEmptyBorder(10,5,10,5));


        gbc.weightx = 1;
        gbc.weighty = 1;

        gbc.gridy = 9999;
        this.add(new JLabel(" "), gbc);

    }

    public void create_panier_card(Magasin magasin, int quantite, Article art, Client client){

        PanierCard panierCard = new PanierCard(magasin, art, quantite, client);

        gbc.gridx = 0;
        gbc.gridy = number++;
        gbc.anchor = GridBagConstraints.NORTHWEST;

        // default set ups
        gbc.insets = DEFAULT_INSETS;
        gbc.weightx = 1.0;
        gbc.weighty = 0.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        add(panierCard, gbc);
        revalidate();
        repaint();


    }

    public void delete_panier_card(PanierCard panierCard){
        number--;

        remove(panierCard);
        revalidate();
        repaint();
    }

}
