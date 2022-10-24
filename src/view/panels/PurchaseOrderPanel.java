package view.panels;

import view.components.MyJLabel;
import view.components.MyJPanel;
import view.components.PurchaseBtn;

import javax.swing.*;
import java.awt.*;

public class PurchaseOrderPanel extends MyJPanel {

    public PurchaseOrderPanel(String montant) {
        super(new BorderLayout());

        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        MyJLabel montant_total_lbl = new MyJLabel("Montant total :   " + montant);
        montant_total_lbl.setPreferredSize(new Dimension(350,40));
        add(montant_total_lbl, BorderLayout.WEST);

        add(new PurchaseBtn(),BorderLayout.EAST);

    }


}
