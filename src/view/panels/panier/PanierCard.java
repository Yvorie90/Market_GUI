package view.panels.panier;

import mesproduits.Article;
import view.components.MyJButton;
import view.components.MyJLabel;
import view.components.MyJPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

import static view.GUI.BACKGROUND_COLOR;
import static view.GUI.TRASHCAN_IMG;

public class PanierCard extends MyJPanel {

    public PanierCard(Article article) {
        super(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        setPreferredSize(new Dimension(400,100));
        setBackground(BACKGROUND_COLOR);
        setOpaque(true);


        try {
            add(new MyJLabel(new ImageIcon(ImageIO.read(new URL(article.image_url())))));
        } catch (IOException e) {
            add(new MyJLabel("image \n indisponible"),BorderLayout.WEST);
        }


        MyJButton btn_annuler = new MyJButton(TRASHCAN_IMG);



    }
}
