package com.feudeforet.domaine.service;

import com.feudeforet.domaine.modele.EtatCase;
import com.feudeforet.domaine.modele.Foret;
import com.feudeforet.domaine.port.GenerateurAleatoire;

/**
 * Service qui gère la propagation du feu
 */
public class ServicePropagationFeu {
	
	private final double probabilite;
    private final GenerateurAleatoire aleatoire;

    public ServicePropagationFeu(double probabilite, GenerateurAleatoire aleatoire) {
        this.probabilite = probabilite;
        this.aleatoire = aleatoire;
    }
    
    /**
     * Calcule l'état de la forêt à l'étape suivante
     *
     * @param actuelle la forêt à l'instant t
     * @return la forêt à l'instant t+1
     */
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
    
    /**
     * Tente de propager le feu aux cases voisines
     *
     * @param actuelle la forêt actuelle
     * @param suivante la forêt suivante
     * @param x position x de la case en feu
     * @param y position y de la case en feu
     */
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
