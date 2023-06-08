package fr.ronan.exercicespringjdbc.repository;

import fr.ronan.exercicespringjdbc.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * <pre>
 * ajouter une commande à un Client (sans article)
 * ajouter un article (et la quantité) à une commande
 * modifier (ou retirer si quantité à 0) la quantité d'un article commandé.
 * Cette classe n'utilisera que le JdbcTemplate.
 * </pre>
 */
@Repository
public class CommandeRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     *
     * @param client
     */
    public void ajouterCommande(Client client) {
        jdbcTemplate.update(
                "insert into commande (numero, nom_client, prenom_client, date_commande) values (?, ?, ?, ?)",
                new Date().getTime(),
                client.getNom(),
                client.getPrenom(),
                new Date()
        );
    }

    /**
     *
     * @param numeroCommande
     * @param denominationArticle
     * @param quantite
     */
    public void ajouterArticle(Long numeroCommande, String denominationArticle, Integer quantite) {
        if (quantite > 0) {
            jdbcTemplate.update(
                    "insert into commande_article " +
                            "(numero_commande, denomination_article, " +
                            " quantite) values (?, ?, ?)",
                    numeroCommande,
                    denominationArticle,
                    quantite
            );
        }
    }

    /**
     *
     * @param numeroCommande
     * @param denominationArticle
     * @param quantite
     */
    public void modifierArticle(Long numeroCommande, String denominationArticle, Integer quantite) {
        if (quantite.intValue() < 1) {
            jdbcTemplate.update(
                    "delete from commande_article " +
                            " where numero_commande=? " +
                            " and denomination_article=?",
                    numeroCommande,
                    denominationArticle
            );
        } else {
            jdbcTemplate.update(
                    "update commande_article " +
                            " set quantite=? " +
                            " where numero_commande=? " +
                            " and denomination_article=?",
                    quantite,
                    numeroCommande,
                    denominationArticle
            );
        }
    }

}