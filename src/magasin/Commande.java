package magasin;

import magasin.exceptions.ArticleHorsPanierException;
import magasin.exceptions.QuantiteNegativeOuNulleException;
import magasin.exceptions.QuantiteSuppPanierException;

import java.util.*;
import java.util.stream.Collectors;

/**
 * défini une commande, c'est-à-dire des articles associés à leur quantité commandée
 */

public class Commande implements Comparable<Commande> {

    int id;
    static int ID = 0;
    HashMap<iArticle,Integer> map_article_quantitee;

    public Commande() {
        this.map_article_quantitee = new HashMap<>();
        this.id = ID++;
    }

    /**
     * indique si la commande est vide
     *
     * @return
     */
    public boolean estVide() {
        return this.map_article_quantitee.isEmpty();
    }

    /**
     * ajoute la quantité indiquée de l'article considéré  à la commande
     *
     * @param quantite        quantité à ajouter
     * @param articleCommande article à considérer
     * @throws QuantiteNegativeOuNulleException si la quantité indiquée est négative ou nulle
     */
    public void ajout(int quantite, iArticle articleCommande)
            throws QuantiteNegativeOuNulleException {

        if (quantite <= 0)
            throw new QuantiteNegativeOuNulleException();

        if (map_article_quantitee.containsKey(articleCommande)) {
            map_article_quantitee.replace(articleCommande, map_article_quantitee.get(articleCommande) + quantite);
        }
        else
            this.map_article_quantitee.put(articleCommande,quantite);
    }

    /**
     * retire de la commande la quantité indiquée de l'article considéré
     *
     * @param quantite        quantité à retirer
     * @param articleCommande article à considérer
     * @throws QuantiteNegativeOuNulleException si la quantité indiquée est négative ou nulle
     * @throws QuantiteSuppPanierException      si la quantité indiquée est supp à celle dans da commande
     * @throws ArticleHorsPanierException       si l'article considéré n'est pas dans la commande
     */
    public void retirer(int quantite, iArticle articleCommande)
            throws QuantiteNegativeOuNulleException,
            QuantiteSuppPanierException, ArticleHorsPanierException {

        if (quantite <= 0)
            throw new QuantiteNegativeOuNulleException();
        if (!this.map_article_quantitee.containsKey(articleCommande))
            throw  new ArticleHorsPanierException();
        if (quantite > this.map_article_quantitee.get(articleCommande))
            throw new QuantiteSuppPanierException();


        this.map_article_quantitee.replace(articleCommande, map_article_quantitee.get(articleCommande)-quantite);

    }

    /**
     * donne une liste de tous les articles présent dans la commande
     * (trié par nom d'article)
     *
     * @return
     */
    public List<iArticle> listerArticlesParNom() {
        return this.map_article_quantitee.keySet().stream().sorted(iArticle.COMPARATEUR_NOM).collect(Collectors.toList());
    }

    /**
     * donne une liste de tous les articles présent dans la commande
     * (trié par reference)
     *
     * @return
     */
    public List<iArticle> listerArticlesParReference() {
        return this.map_article_quantitee.keySet().stream().sorted(iArticle.COMPARATEUR_REFERENCE).collect(Collectors.toList());
    }

    /**
     * donne une liste de tous les couples (articleCommande, quantiteCommandee)
     * présent dans la commande
     *
     * @return
     */
    public List<Map.Entry<iArticle, Integer>> listerCommande() {

        List list = map_article_quantitee.entrySet().stream().collect(Collectors.toList());
        Collections.reverse(list); // POURQUOI ?????????????????????
        return list ;
    }


    /**
     * donne la quantité commandée de l'article considéré
     *
     * @param article l'article à considérer
     * @return la quantité commmandée
     */
    public int quantite(iArticle article) {
        return this.map_article_quantitee.get(article);
    }

    /**
     * donne le montant actuel de la commande
     *
     * @return
     */
    public double montant() {
        double res = 0;
        for (iArticle article : map_article_quantitee.keySet())
            res += article.prix()*map_article_quantitee.get(article);
        return res;
    }

    @Override
    public int compareTo(Commande o) {
        // TODO
        this.map_article_quantitee.equals(o);
        return 0;
    }
}
