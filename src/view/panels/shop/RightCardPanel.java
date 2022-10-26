package view.panels.shop;

import magasin.Magasin;
import magasin.exceptions.ArticleHorsStockException;
import magasin.exceptions.ClientInconnuException;
import magasin.exceptions.QuantiteEnStockInsuffisanteException;
import magasin.exceptions.QuantiteNegativeOuNulleException;
import magasin.iArticle;
import mesproduits.Article;
import monapplication.Client;
import view.GUI;
import view.components.MyJButton;
import view.components.MyJLabel;
import view.components.MyJPanel;
import view.components.MyJSpinner;
import view.panels.MainPanel;
import view.panels.panier.ContainerPanierPanel;
import view.panels.panier.PanierPanel;
import view.panels.panier.PurchaseOrderPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class RightCardPanel extends MyJPanel {

    MyJSpinner spinner;
    MyJButton  btn_ajout_panier = new MyJButton("Ajouter");


    public RightCardPanel(Magasin magasin, iArticle article, Client client) {
        super(new BorderLayout());

        setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 20));


        try {
            spinner = (new MyJSpinner(new SpinnerNumberModel(0,0,magasin.consulterQuantiteEnStock(article),1)));
        } catch (ArticleHorsStockException ignore) {
        }


        //((SpinnerNumberModel) ((JSpinner) ((ArrayList<?>) card_dofus_map.get(d.reference())).get(0)).getModel()).setMaximum(magasin.consulterQuantiteEnStock(d));



        btn_ajout_panier.addActionListener(e->{
            //int en_stock_int = Integer.parseInt(((JLabel)((Container)((JButton) e.getSource()).getParent().getParent().getComponent(0)).getComponent(1)).getText());
            //System.out.println(en_stock_int);
            //magasin.ajouterAuPanier(magasin.listerLesClientsParId().get(0),magasin.st);

            try {
                //control model
                magasin.ajouterAuPanier(client,article,(Integer)spinner.getModel().getValue());

                //view

                //-> card -> shop -> ViewPort -> scroll -> main
                MainPanel main = (MainPanel) getParent().getParent().getParent().getParent().getParent();
                //System.out.println(getParent().getParent().getParent().getParent().getParent());
                //main.getComponent(2),getComponent(1);
                ((PanierPanel) ((JViewport) ((JScrollPane) ((ContainerPanierPanel)main.getComponent(2)).getComponent(1)).getComponent(0)).getComponent(0))
                        .create_panier_card(magasin,(int)spinner.getModel().getValue(),(Article) article,client);

                ((PurchaseOrderPanel)((ContainerPanierPanel)main.getComponent(2)).getComponent(2)).reload_montant(magasin,client);




                spinner.setModel(new SpinnerNumberModel(0,0,magasin.consulterQuantiteEnStock(article),1));
                ((LeftCardPanel)getParent().getComponent(1)).reload_stockLabel(magasin,article);

            } catch (ClientInconnuException ex) {
                ex.printStackTrace();
            } catch (QuantiteNegativeOuNulleException ignore) {
            } catch (ArticleHorsStockException ex) {
                ex.printStackTrace();
            } catch (QuantiteEnStockInsuffisanteException ex) {
                ex.printStackTrace();
            }


        });

        add(btn_ajout_panier,BorderLayout.SOUTH);
        add(spinner,BorderLayout.NORTH);
    }

    public void reload_spinner(Magasin magasin, iArticle article){
        try {
            spinner.setModel(new SpinnerNumberModel(0,0,magasin.consulterQuantiteEnStock(article),1));
        } catch (ArticleHorsStockException ignore) {
        }
    }


}
