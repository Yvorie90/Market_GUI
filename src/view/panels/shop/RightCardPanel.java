package view.panels.shop;

import magasin.Magasin;
import magasin.exceptions.ArticleHorsStockException;
import magasin.exceptions.ClientInconnuException;
import magasin.exceptions.QuantiteEnStockInsuffisanteException;
import magasin.exceptions.QuantiteNegativeOuNulleException;
import magasin.iArticle;
import monapplication.Client;
import view.GUI;
import view.components.MyJButton;
import view.components.MyJPanel;
import view.components.MyJSpinner;
import view.panels.MainPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class RightCardPanel extends MyJPanel {

    MyJSpinner spinner;


    public RightCardPanel(Magasin magasin, iArticle article, Client client, GUI gui) {
        super(new BorderLayout());

        setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 20));


        MyJButton btn_ajout_panier = new MyJButton("Ajouter");


        try {
            spinner = (new MyJSpinner(new SpinnerNumberModel(0,0,magasin.consulterQuantiteEnStock(article),1)));
        } catch (ArticleHorsStockException e) {
            spinner.setEnabled(false);
            spinner = (new MyJSpinner(new SpinnerNumberModel(0,0,0,1)));
            btn_ajout_panier.setEnabled(false);
        }


        //((SpinnerNumberModel) ((JSpinner) ((ArrayList<?>) card_dofus_map.get(d.reference())).get(0)).getModel()).setMaximum(magasin.consulterQuantiteEnStock(d));



        btn_ajout_panier.addActionListener(e->{
            //int en_stock_int = Integer.parseInt(((JLabel)((Container)((JButton) e.getSource()).getParent().getParent().getComponent(0)).getComponent(1)).getText());
            //System.out.println(en_stock_int);
            //magasin.ajouterAuPanier(magasin.listerLesClientsParId().get(0),magasin.st);

            try {
                magasin.ajouterAuPanier(client,article,(Integer)spinner.getModel().getValue());

            } catch (ClientInconnuException ex) {
                ex.printStackTrace();
            } catch (QuantiteNegativeOuNulleException ignore) {
            } catch (ArticleHorsStockException ex) {
                ex.printStackTrace();
            } catch (QuantiteEnStockInsuffisanteException ex) {
                ex.printStackTrace();
            }

            System.out.println(getParent());
            //getParent().repaint();
            gui.reload(magasin,client);


        });

        add(btn_ajout_panier,BorderLayout.SOUTH);
        add(spinner,BorderLayout.NORTH);
    }
}