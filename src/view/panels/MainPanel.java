package view.panels;

import magasin.Magasin;
import monapplication.Client;
import view.GUI;
import view.components.MyJLabel;
import view.components.MyJPanel;
import view.panels.panier.ContainerPanierPanel;
import view.panels.panier.PanierPanel;
import view.panels.shop.ShopPanel;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends MyJPanel {

    ShopPanel shopPanel;

    public MainPanel(Magasin magasin, Client client) {
        super(new BorderLayout(10, 10));

        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setBackground(GUI.BACKGROUND_COLOR);
        setOpaque(true);

        add(new MyJLabel("Bienvenue à L'Échoppe de Goultard Le Barbare"),BorderLayout.NORTH);


        shopPanel = new ShopPanel(magasin,client);
        JScrollPane scrollShopPanel = new JScrollPane(shopPanel);
        scrollShopPanel.getVerticalScrollBar().setUnitIncrement(16);
        add(scrollShopPanel,BorderLayout.CENTER);

        add(new ContainerPanierPanel(magasin, client),BorderLayout.EAST);


    }

    public void reload_shop(Magasin magasin){
        shopPanel.reload_shop(magasin);
    }


}
