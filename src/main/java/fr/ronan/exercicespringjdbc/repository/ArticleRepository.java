package fr.ronan.exercicespringjdbc.repository;

import fr.ronan.exercicespringjdbc.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

/**
 * <pre>
 * ajouter un article
 * mettre à jour le coût unitaire et la TVA d'un article
 * supprimer un Article (en tenant compte des associations)
 * Cette classe n'utilisera que le NamedParameterJdbcTemplate.
 * </pre>
 */
@Repository
public class ArticleRepository {

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /**
     *
     * @param article
     */
    public void ajouterArticle(Article article) {
        var params = new BeanPropertySqlParameterSource(article);
        namedParameterJdbcTemplate.update(
                "insert into article (denomination, cout_hors_taxe, " +
                        "tva) values " +
                        "(:denomination, :coutUnitaireHt, :tauxTva)",
                params
        );
    }

    /**
     *
     * @param article
     */
    public void modifierArticle(Article article) {
        // var params = new BeanPropertySqlParameterSource(article);
        var params = new HashMap<String, Object>();
        params.put("coutUnitaireHt", article.getCoutUnitaireHt());
        params.put("tauxTva", article.getTauxTva());
        params.put("denomination", article.getDenomination());
        namedParameterJdbcTemplate.update(
                "update article " +
                        " set  cout_hors_taxe=:coutUnitaireHt, " +
                        " tva=:tauxTva " +
                        " where denomination=:denomination)",
                params
        );
    }

    /**
     * Attention : la méthode n'est pas transactionnelle
     * @param article
     */
    public void suppimerArticle(Article article) {
        var params = new BeanPropertySqlParameterSource(article);
        namedParameterJdbcTemplate.update(
                "delete from commande_article " +
                        " where denomination_article=:denomination",
                params
        );
        namedParameterJdbcTemplate.update(
                "delete from article " +
                        " where denomination=:denomination",
                params
        );
    }


}