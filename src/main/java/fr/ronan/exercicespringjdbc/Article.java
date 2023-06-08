package fr.ronan.exercicespringjdbc;

import java.io.Serializable;

public class Article implements Serializable {

    private String denomination;

    private Float coutUnitaireHt;

    private Float tauxTva;

    public String getDenomination() {
        return denomination;
    }

    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }

    public Float getCoutUnitaireHt() {
        return coutUnitaireHt;
    }

    public void setCoutUnitaireHt(Float coutUnitaireHt) {
        this.coutUnitaireHt = coutUnitaireHt;
    }

    public Float getTauxTva() {
        return tauxTva;
    }

    public void setTauxTva(Float tauxTva) {
        this.tauxTva = tauxTva;
    }
}
