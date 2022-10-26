package view.panels.panier;

import magasin.Magasin;
import magasin.exceptions.ClientInconnuException;
import monapplication.Client;
import view.components.MyJButton;
import view.components.MyJLabel;
import view.components.MyJPanel;
import view.panels.shop.Card;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

import static view.GUI.BACKGROUND_COLOR_2;

public class PurchaseOrderPanel extends MyJPanel {

    MyJLabel montant_total_lbl =new MyJLabel("");

    public PurchaseOrderPanel(Magasin magasin, Client client) {
        super(new BorderLayout());

        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        reload_montant(magasin, client);
        montant_total_lbl.setPreferredSize(new Dimension(350,40));
        add(montant_total_lbl, BorderLayout.CENTER);

        MyJButton btn_achat = new MyJButton("Achat");
        btn_achat.setBackground(BACKGROUND_COLOR_2);
        add(btn_achat,BorderLayout.EAST);

        btn_achat.addActionListener(e->{

            try {
                magasin.terminerLaCommande(client);

                reload_montant(magasin, client);

                PanierPanel panierPanel = ((PanierPanel)((JViewport)((JScrollPane)((ContainerPanierPanel) getParent()).getComponent(1)).getComponent(0)).getComponent(0));

                Arrays.stream(panierPanel.getComponents()).toList().forEach(i ->{
                    PanierCard panierCard = (PanierCard) i;
                    panierPanel.delete_panier_card(panierCard);
                });


            } catch (ClientInconnuException ex) {
                ex.printStackTrace();
            }


        });


    }


    public void reload_montant(Magasin magasin, Client client){
        try {
            montant_total_lbl.setText("Montant total :   " + magasin.consulterMontantPanier(client));
        } catch (ClientInconnuException e) {
            montant_total_lbl.setText("Montant Indisponible");
        }
    }


}
