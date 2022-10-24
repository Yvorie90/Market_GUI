package view.panels;

import view.GUI;
import view.components.MyJLabel;
import view.components.MyJPanel;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends MyJPanel {

    public MainPanel() {
        super(new BorderLayout(10, 10));

        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setBackground(GUI.BACKGROUND_COLOR);

        add(new MyJLabel("Bienvenue à L'Échoppe de Goultard Le Barbare"),BorderLayout.NORTH);
        


    }


}
