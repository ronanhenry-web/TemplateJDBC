package fr.ronan.exercicespringjdbc;

import java.io.Serializable;

public class CommandeArticle implements Serializable {

    private Long numeroCommande;

    private String denominationArticle;

    private Integer quantite;

    public Long getNumeroCommande() {
        return numeroCommande;
    }

    public void setNumeroCommande(Long numeroCommande) {
        this.numeroCommande = numeroCommande;
    }

    public String getDenominationArticle() {
        return denominationArticle;
    }

    public void setDenominationArticle(String denominationArticle) {
        this.denominationArticle = denominationArticle;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }
}
