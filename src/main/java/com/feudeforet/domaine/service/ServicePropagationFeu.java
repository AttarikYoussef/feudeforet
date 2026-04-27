package com.feudeforet.domaine.service;

import com.feudeforet.domaine.modele.EtatCase;
import com.feudeforet.domaine.modele.Foret;
import com.feudeforet.domaine.port.GenerateurAleatoire;

public class ServicePropagationFeu {
	
	private final double probabilite;
    private final GenerateurAleatoire aleatoire;

    public ServicePropagationFeu(double probabilite, GenerateurAleatoire aleatoire) {
        this.probabilite = probabilite;
        this.aleatoire = aleatoire;
    }
    
    public Foret etapeSuivante(Foret foret) {

        Foret suivante = foret.copier();

        for (int i = 0; i < foret.getHauteur(); i++) {
            for (int j = 0; j < foret.getLargeur(); j++) {

                if (foret.get(i, j) == EtatCase.EN_FEU) {

                    suivante.set(i, j, EtatCase.CENDRE);

                    propager(foret, suivante, i, j);
                }
            }
        }

        return suivante;
    }
    
    private void propager(Foret actuelle, Foret suivante, int x, int y) {

        int[][] directions = {
            {-1, 0}, {1, 0}, {0, -1}, {0, 1}
        };

        for (int[] d : directions) {
            int nx = x + d[0];
            int ny = y + d[1];

            if (actuelle.estDansLaGrille(nx, ny)
                && actuelle.get(nx, ny) == EtatCase.ARBRE
                && aleatoire.prochainDouble() < probabilite) {

                suivante.set(nx, ny, EtatCase.EN_FEU);
            }
        }
    }

}
