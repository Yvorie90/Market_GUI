package magasin;

import magasin.exceptions.*;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Magasin implements iStock, iClientele, iPanier{

    ArrayList<iClient> clientele;
    HashMap<iArticle,Integer> stock;
    HashMap<iClient,Commande> map_client_panier;
    HashMap<iClient,List<Commande>> map_client_commandes;

    public Magasin() {
        this.clientele = new ArrayList<>();
        this.stock = new HashMap<>();
        this.map_client_panier = new HashMap<>();
        this.map_client_commandes = new HashMap<>();
    }


    // iStock

    @Override
    public void referencerAuStock(iArticle nouvelArticle, int quantiteNouvelle)
            throws ArticleDejaEnStockException, QuantiteNegativeException {

        if (quantiteNouvelle < 0) throw new QuantiteNegativeException();
        if (stock.containsKey(nouvelArticle)) throw new ArticleDejaEnStockException();

        stock.put(nouvelArticle,quantiteNouvelle);
    }


    @Override
    public void reapprovisionnerStock(iArticle articleMaj, int quantiteAjoutee)
            throws ArticleHorsStockException, QuantiteNegativeOuNulleException {

        if (quantiteAjoutee <= 0) throw new QuantiteNegativeException();
        if (!stock.containsKey(articleMaj)) throw new ArticleHorsStockException();

        stock.replace(articleMaj,stock.get(articleMaj)+quantiteAjoutee);;
    }

    @Override
    public int consulterQuantiteEnStock(iArticle articleRecherche) throws ArticleHorsStockException {

        if (!stock.containsKey(articleRecherche)) throw new ArticleHorsStockException();

        return stock.get(articleRecherche);
    }

    @Override
    public void retirerDuStock(int quantiteRetiree, iArticle articleMaj)
            throws ArticleHorsStockException, QuantiteNegativeOuNulleException, QuantiteEnStockInsuffisanteException {

        if (quantiteRetiree <= 0) throw new QuantiteNegativeException();
        if (!stock.containsKey(articleMaj)) throw new ArticleHorsStockException();
        if (quantiteRetiree > stock.get(articleMaj)) throw new QuantiteEnStockInsuffisanteException();

        int quant = stock.get(articleMaj)-quantiteRetiree;
        stock.replace(articleMaj,quant);
    }


    @Override
    public List<iArticle> listerArticlesEnStockParNom() {
        return stock.keySet().stream().sorted(iArticle.COMPARATEUR_NOM).collect(Collectors.toList());
    }

    @Override
    public List<iArticle> listerArticlesEnStockParReference() {
        return stock.keySet().stream().sorted(iArticle.COMPARATEUR_REFERENCE).collect(Collectors.toList());
    }

    @Override
    public List<Map.Entry<iArticle, Integer>> listerStock() {
        return stock.entrySet().stream().sorted((o1, o2) -> iArticle.COMPARATEUR_NOM.compare(o1.getKey(), o2.getKey())).collect(Collectors.toList());
    }

    // iClientele


    @Override
    public void enregistrerNouveauClient(iClient nouveauClient) throws ClientDejaEnregistreException {

        if (clientele.contains(nouveauClient)) throw new ClientDejaEnregistreException();

        clientele.add(nouveauClient);
        map_client_panier.put(nouveauClient,new Commande());
        map_client_commandes.put(nouveauClient, new ArrayList<>());
    }

    @Override
    public List<iClient> listerLesClientsParId() {
        return clientele.stream().sorted(iClient.COMPARATEUR_ID).collect(Collectors.toList());
    }


    // iPanier

    @Override
    public Commande consulterPanier(iClient client) throws ClientInconnuException {

        if (!clientele.contains(client)) throw new ClientInconnuException();

        return map_client_panier.get(client);
    }

    @Override
    public void ajouterAuPanier(iClient client, iArticle article, int quantite)
            throws ClientInconnuException,
            QuantiteNegativeOuNulleException,
            ArticleHorsStockException, QuantiteEnStockInsuffisanteException {

        if (!clientele.contains(client))
            throw new ClientInconnuException();
        if (!stock.containsKey(article))
            throw new ArticleHorsStockException();
        if (quantite <= 0)
            throw new QuantiteNegativeOuNulleException();
        if (quantite > stock.get(article))
            throw new QuantiteEnStockInsuffisanteException();


        //System.out.println(map_client_panier);
        map_client_panier.get(client).ajout(quantite,article);
        //System.out.println(map_client_panier);
        retirerDuStock(quantite,article);
        //System.out.println(map_client_panier);
        //System.out.println(stock);
    }

    @Override
    public void supprimerDuPanier(iClient client, int quantite, iArticle article)
            throws ClientInconnuException,
            QuantiteNegativeOuNulleException,
            QuantiteSuppPanierException, ArticleHorsPanierException,
            ArticleHorsStockException {

        if (!map_client_panier.containsKey(client)) throw new ClientInconnuException();


        map_client_panier.get(client).retirer(quantite,article);
        reapprovisionnerStock(article,quantite);


    }

    @Override
    public double consulterMontantPanier(iClient client) throws ClientInconnuException {

        if(!clientele.contains(client)) throw new ClientInconnuException();

        System.out.println(map_client_panier.get(client).listerCommande());

        return map_client_panier.get(client).montant();
    }

    @Override
    public void viderPanier(iClient client) throws ClientInconnuException {

        if (!clientele.contains(client)) throw new ClientInconnuException();

        for( Map.Entry<iArticle, Integer> art : map_client_panier.get(client).listerCommande()){
            //if (stock.containsKey(art))
                stock.replace(art.getKey(),stock.get(art.getKey())+art.getValue());
            //else stock.put(art.getKey(),art.getValue());
        }

        map_client_panier.replace(client, new Commande());
    }

    @Override
    public void terminerLaCommande(iClient client) throws ClientInconnuException {

        if (!clientele.contains(client)) throw new ClientInconnuException();

        map_client_commandes.get(client).add(map_client_panier.get(client));
        map_client_panier.replace(client, new Commande());
    }

    @Override
    public List<Commande> listerCommandesTerminees(iClient client) throws ClientInconnuException {

        if (!clientele.contains(client)) throw new ClientInconnuException();

        return map_client_commandes.get(client);
    }

    @Override
    public double consulterMontantTotalCommandes(iClient client) throws ClientInconnuException {

        if (!clientele.contains(client)) throw new ClientInconnuException();

        return map_client_commandes.get(client).stream().mapToDouble(Commande::montant).sum();
    }


}