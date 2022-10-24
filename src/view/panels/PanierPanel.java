package view.panels;

import magasin.Magasin;
import view.components.MyJPanel;

import javax.swing.*;
import java.awt.*;

import static view.GUI.BACKGROUND_COLOR_2;

public class PanierPanel extends MyJPanel {

    Magasin magasin;

    public PanierPanel(Magasin magasin) {
        super(new GridLayout(0,1,0,10));
        this.magasin = magasin;

        setBackground(BACKGROUND_COLOR_2);
        setBorder(BorderFactory.createEmptyBorder(10,5,10,5));







    }
}
