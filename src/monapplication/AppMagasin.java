package monapplication;

import com.formdev.flatlaf.FlatLightLaf;
import magasin.Magasin;
import magasin.exceptions.ArticleHorsStockException;
import magasin.exceptions.ClientDejaEnregistreException;
import magasin.iArticle;
import mesproduits.dofus.Dofus;
import mesproduits.dofus.DofusFactory;
import view.panels.MainPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class AppMagasin extends JFrame {

    Map card_panier_map = new HashMap<Integer, ArrayList<JComponent>>();





    public AppMagasin(Magasin magasin) {




        // FILLING SHOP
        for (iArticle a : magasin.listerArticlesEnStockParNom()){

            ////////////////////////:
            //create_panier_card(d);
        }



    }


    /*
    public void create_panier_card(Dofus d){
        card_panier_map.put(d.reference(),new ArrayList<JComponent>());


        JButton btn_annuler;
        if (TRASHCAN_IMG ==null)
            btn_annuler = new JButton("annuler");
        else
            btn_annuler = new JButton(TRASHCAN_IMG);
        btn_annuler.setBackground(BACKGROUND_COLOR_2);
        JPanel contain_btn_annuler = new JPanel(new GridLayout());
        contain_btn_annuler.setPreferredSize(new Dimension(60,30));
        contain_btn_annuler.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        contain_btn_annuler.add(btn_annuler);
        contain_btn_annuler.setBackground(BACKGROUND_COLOR);
        container.add(contain_btn_annuler,BorderLayout.EAST);
        // BTN POUBELLE
        btn_annuler.addActionListener(e ->{
            ((JButton) e.getSource()).getParent().getParent().getParent().remove(((JButton) e.getSource()).getParent().getParent());
            panierPanel.repaint();
        });



        panierPanel.add(container);
    }

     */








}
