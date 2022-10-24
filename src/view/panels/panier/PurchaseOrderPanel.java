package view.panels.panier;

import view.components.MyJButton;
import view.components.MyJLabel;
import view.components.MyJPanel;

import javax.swing.*;
import java.awt.*;

import static view.GUI.BACKGROUND_COLOR_2;

public class PurchaseOrderPanel extends MyJPanel {

    public PurchaseOrderPanel(String montant) {
        super(new BorderLayout());

        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        MyJLabel montant_total_lbl = new MyJLabel("Montant total :   " + montant);
        montant_total_lbl.setPreferredSize(new Dimension(350,40));
        add(montant_total_lbl, BorderLayout.CENTER);

        MyJButton btn_achat = new MyJButton("Achat");
        btn_achat.setBackground(BACKGROUND_COLOR_2);
        add(btn_achat,BorderLayout.EAST);

    }


}
