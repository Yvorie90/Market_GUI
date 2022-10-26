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



        // BTN POUBELLE
        btn_annuler.addActionListener(e ->{
            ((JButton) e.getSource()).getParent().getParent().getParent().remove(((JButton) e.getSource()).getParent().getParent());
            panierPanel.repaint();
        });



        panierPanel.add(container);
    }

     */








}
