package monapplication;

import com.formdev.flatlaf.FlatLightLaf;
import magasin.Magasin;
import magasin.exceptions.ArticleHorsStockException;
import magasin.exceptions.ClientDejaEnregistreException;
import magasin.iArticle;
import mesproduits.dofus.Dofus;
import mesproduits.dofus.DofusFactory;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AppMagasin extends JFrame {

    Map card_dofus_map = new HashMap<Integer, ArrayList<JComponent>>();
    //list : stock , spinner , button
    Map card_panier_map = new HashMap<Integer, ArrayList<JComponent>>();

    // VARIABLES GLOBALES
    public static Color BACKGROUND_COLOR = Color.decode("#292921");
    public static Color BACKGROUND_COLOR_2 = Color.decode("#35382f");
    public static Color FONT_COLOR = Color.LIGHT_GRAY;
    public static ImageIcon KAMA_IMG;
    static {
        try {KAMA_IMG = new ImageIcon(ImageIO.read(new URL ("https://media.discordapp.net/attachments/987243695231340554/1032283517352169532/unknown.png?"))
                .getScaledInstance(20,20,Image.SCALE_DEFAULT));
        } catch (IOException e) {KAMA_IMG = null;}
    }
    public static ImageIcon TRASHCAN_IMG;
    static {
        try {TRASHCAN_IMG = new ImageIcon(ImageIO.read(new URL ("https://media.discordapp.net/attachments/987243695231340554/1032535776803700756/clipart1120803.png"))
                .getScaledInstance(20,20,Image.SCALE_DEFAULT));
        } catch (IOException e) {TRASHCAN_IMG= null;}
    }


    // PANELS
    private JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
    private JPanel shopPanel = new JPanel(new GridLayout(0, 3, 15, 15));
    private JPanel panierPanel = new JPanel(new GridLayout(0,1,0,10));


    // COMPONENTS
    private JLabel acceuil_label = new JLabel("Bienvenue à L'Échoppe de Goultard Le Barbare",SwingConstants.CENTER);
    private JLabel panier_label  = new JLabel("PANIER",SwingConstants.CENTER);


    // INIT
    public AppMagasin(Magasin magasin) {


        // setup Frame
        super("L'Échoppe de Goultard Le Barbare ");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(1450, 900));
        setResizable(false);
        setIconImage(KAMA_IMG.getImage());


        // TOP LABEL
        acceuil_label.setForeground(FONT_COLOR);

        // SHOP PANEL
        shopPanel.setBackground(BACKGROUND_COLOR);
        shopPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


        // FILLING SHOP
        for (iArticle a : magasin.listerArticlesEnStockParNom()){
            Dofus d = (Dofus) a;
            JPanel card = new JPanel(new BorderLayout());

            card_dofus_map.put(d.reference(), new ArrayList<JComponent>());
            ArrayList list = (ArrayList) card_dofus_map.get(d.reference());

            card.setPreferredSize(new Dimension(300,150));
            card.setBackground(BACKGROUND_COLOR_2);

            // CARD_LEFT
            JPanel card_left = new JPanel(new BorderLayout());
            card_left.setBorder(BorderFactory.createEmptyBorder(30, 20, 30, 10));
            card_left.setBackground(BACKGROUND_COLOR_2);
            JLabel en_stock = new JLabel("En stock :",SwingConstants.CENTER);
            en_stock.setForeground(FONT_COLOR);
            JLabel stock = null;
            try {
                stock = new JLabel(String.valueOf(magasin.consulterQuantiteEnStock(d)), SwingConstants.CENTER);
            } catch (ArticleHorsStockException e) {
                stock = new JLabel("0", SwingConstants.CENTER);
            }
            stock.setForeground(FONT_COLOR);
            card_left.add(en_stock,BorderLayout.NORTH);
            card_left.add(stock,BorderLayout.SOUTH);
            card.add(card_left,BorderLayout.WEST);

            // CARD_RIGHT
            JPanel card_right = new JPanel(new BorderLayout());
            card_right.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 20));
            card_right.setBackground(BACKGROUND_COLOR_2);
            JSpinner spinn_item = new JSpinner(new SpinnerNumberModel(1,1,20,1));
            spinn_item.setBackground(BACKGROUND_COLOR);
            spinn_item.setForeground(FONT_COLOR);
            list.add(spinn_item);
            try {
                ((SpinnerNumberModel) ((JSpinner) ((ArrayList<?>) card_dofus_map.get(d.reference())).get(0)).getModel()).setMaximum(magasin.consulterQuantiteEnStock(d));
            } catch (ArticleHorsStockException e) {
                spinn_item.setEnabled(false);
            }
            JButton btn_ajout_panier = new JButton("Ajout");
            btn_ajout_panier.setBackground(BACKGROUND_COLOR);
            btn_ajout_panier.setForeground(FONT_COLOR);
            btn_ajout_panier.addActionListener(e->{
                int en_stock_int = Integer.parseInt(((JLabel)((Container)((JButton) e.getSource()).getParent().getParent().getComponent(0)).getComponent(1)).getText());
                System.out.println(en_stock_int);

                //magasin.ajouterAuPanier(magasin.listerLesClientsParId().get(0),magasin.st);
            });
            card_right.add(btn_ajout_panier,BorderLayout.SOUTH);
            card_right.add(spinn_item,BorderLayout.NORTH);
            card.add(card_right,BorderLayout.EAST);

            // CENTER
            JLabel img_item = null;
            try {
                img_item = new JLabel(new ImageIcon(ImageIO.read(new URL(d.image_url()))));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            JLabel info_txt = new JLabel(d.nom() +"  |  "+(d.prix()));
                info_txt.setForeground(FONT_COLOR);
            JLabel kama_img;
            if (KAMA_IMG==null)
                kama_img = new JLabel("K");
            else
                kama_img = new JLabel(KAMA_IMG);
            JPanel item_info = new JPanel();
                item_info.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                item_info.setBackground(BACKGROUND_COLOR_2);
                item_info.add(info_txt);
                item_info.add(kama_img);

            card.add(img_item,BorderLayout.CENTER);
            card.add(item_info,BorderLayout.SOUTH);

            shopPanel.add(card);

            ////////////////////////:
            create_panier_card(d);
        }

        // PANIER PANEL
        JPanel containeur = new JPanel(new BorderLayout());
        containeur.setPreferredSize(new Dimension(400,400));
        panierPanel.setBackground(BACKGROUND_COLOR_2);
        panierPanel.setBorder(BorderFactory.createEmptyBorder(10,5,10,5));
        panier_label.setPreferredSize(new Dimension(400,40));
        panier_label.setForeground(FONT_COLOR);
        containeur.setBackground(BACKGROUND_COLOR);
        containeur.add(panier_label,BorderLayout.NORTH);
        JScrollPane scrollPanierPanel = new JScrollPane(panierPanel);
        scrollPanierPanel.getVerticalScrollBar().setUnitIncrement(16);
        containeur.add(scrollPanierPanel);

        JPanel finalize = new JPanel(new BorderLayout());
        finalize.setBackground(BACKGROUND_COLOR);
        finalize.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        JLabel montant_tot = new JLabel("Montant total :   " + "");
        montant_tot.setPreferredSize(new Dimension(350,40));
        montant_tot.setForeground(FONT_COLOR);
        finalize.add(montant_tot,BorderLayout.WEST);
        JButton achat = new JButton("Achat");
        achat.setBackground(BACKGROUND_COLOR_2);
        achat.setForeground(FONT_COLOR);
        finalize.add(achat, BorderLayout.EAST);
        containeur.add(finalize,BorderLayout.SOUTH);

        // MAIN PANEL
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.setBackground(BACKGROUND_COLOR);


        JScrollPane scrollShopPanel = new JScrollPane(shopPanel);
        scrollShopPanel.getVerticalScrollBar().setUnitIncrement(16);
        mainPanel.add(scrollShopPanel, BorderLayout.CENTER);
        mainPanel.add(acceuil_label, BorderLayout.NORTH);

        mainPanel.add(containeur,BorderLayout.EAST);

        // ADD MAIN
        add(mainPanel);

    }


    public void create_panier_card(Dofus d){
        card_panier_map.put(d.reference(),new ArrayList<JComponent>());
        JPanel container = new JPanel(new BorderLayout());
        container.setBackground(BACKGROUND_COLOR);
        container.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        JLabel img;
        try {
            img = new JLabel(new ImageIcon(ImageIO.read(new URL(d.image_url()))));
        } catch (IOException e) {
            img = new JLabel("img");
        }
        container.add(img,BorderLayout.WEST);

        JButton btn_annuler;
        if (TRASHCAN_IMG ==null)
            btn_annuler = new JButton("annuler");
        else
            btn_annuler = new JButton(TRASHCAN_IMG);
        btn_annuler.setBackground(BACKGROUND_COLOR_2);
        btn_annuler.setForeground(FONT_COLOR);
        JPanel contain_btn_annuler = new JPanel(new GridLayout());
        contain_btn_annuler.setPreferredSize(new Dimension(60,30));
        contain_btn_annuler.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        contain_btn_annuler.add(btn_annuler);
        contain_btn_annuler.setBackground(BACKGROUND_COLOR);
        container.add(contain_btn_annuler,BorderLayout.EAST);
        // BTN POUBELLE
        btn_annuler.addActionListener(e ->{
            ((JButton) e.getSource()).getParent().getParent().getParent().remove(((JButton) e.getSource()).getParent().getParent());
            panierPanel.repaint();
        });



        panierPanel.add(container);
    }






    public static void main(String[] args) {

        //setting magasin
        Magasin magasin = new Magasin();

        for(Dofus d : DofusFactory.getListDofus()){
            try {
                magasin.referencerAuStock(d,(int)(Math.random()*10));
            } catch (Exception ignore){}
        }

        Client client1 = new Client("Pipoune","Pinpin");

        try {
            magasin.enregistrerNouveauClient(client1);
        } catch (ClientDejaEnregistreException ignore) {}





        // set swing theme
        try {
            UIManager.setLookAndFeel( new FlatLightLaf() );
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }

        //run swing app
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                AppMagasin app = new AppMagasin(magasin);
                app.setVisible(true);
            }
        });
    }

}
