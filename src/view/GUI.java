package view;

import magasin.Magasin;
import monapplication.Client;
import view.panels.MainPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class GUI extends JFrame {

    MainPanel mainPanel;


    public static ImageIcon KAMA_IMG;
    public static ImageIcon TRASHCAN_IMG;
    static {
        try {
            KAMA_IMG = new ImageIcon(ImageIO.read(new URL("https://media.discordapp.net/attachments/987243695231340554/1032283517352169532/unknown.png?"))
                .getScaledInstance(20,20, Image.SCALE_DEFAULT));
            TRASHCAN_IMG = new ImageIcon(ImageIO.read(new URL ("https://media.discordapp.net/attachments/987243695231340554/1032535776803700756/clipart1120803.png"))
                .getScaledInstance(20,20,Image.SCALE_DEFAULT));
        } catch (IOException e) {TRASHCAN_IMG= null;}
    }

    public static Color BACKGROUND_COLOR = Color.decode("#292921");
    public static Color BACKGROUND_COLOR_2 = Color.decode("#35382f");
    public static Color FONT_COLOR = Color.LIGHT_GRAY;

    public GUI(Magasin magasin, Client client){
        super("L'Échoppe de Goultard Le Barbare ");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(1450, 900));
        setResizable(false);
        setIconImage(KAMA_IMG.getImage());



        add(new MainPanel(magasin, client));
    }


}
