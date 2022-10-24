package monapplication;

import com.formdev.flatlaf.FlatLightLaf;
import magasin.*;
import magasin.exceptions.ClientDejaEnregistreException;
import mesproduits.*;
import mesproduits.dofus.Dofus;
import mesproduits.dofus.DofusFactory;
import view.GUI;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class MonApplication {

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
                GUI app = new GUI(magasin, client1);
                app.setVisible(true);
            }
        });
    }
}
