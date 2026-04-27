package com.feudeforet.domaine;

import com.feudeforet.domaine.port.GenerateurAleatoire;

public class FauxGenerateurAleatoire implements GenerateurAleatoire {
    private final double valeur;

    public FauxGenerateurAleatoire(double valeur) {
        this.valeur = valeur;
    }

    public double prochainDouble() {
        return valeur;
    }
}
