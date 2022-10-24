package view.panels;

import magasin.Magasin;
import magasin.exceptions.ClientInconnuException;
import monapplication.Client;
import view.components.MyJLabel;
import view.components.MyJPanel;

import javax.swing.*;
import java.awt.*;

public class ContainerPanierPanel extends MyJPanel {

    public ContainerPanierPanel(Magasin magasin, Client client) {
        super(new BorderLayout());

        setPreferredSize(new Dimension(400,400));



        MyJLabel panier_label = new MyJLabel("PANIER");
        panier_label.setPreferredSize(new Dimension(400,40));
        add(panier_label,BorderLayout.NORTH);

        JScrollPane scrollPanierPanel = new JScrollPane(new PanierPanel(magasin));
        scrollPanierPanel.getVerticalScrollBar().setUnitIncrement(16);
        add(scrollPanierPanel,BorderLayout.CENTER);

        try {
            add(new PurchaseOrderPanel(String.valueOf(magasin.consulterMontantPanier(client))),BorderLayout.SOUTH);
        } catch (ClientInconnuException e) {
            add(new PurchaseOrderPanel("client invalid"),BorderLayout.SOUTH);

        }

    }
}
