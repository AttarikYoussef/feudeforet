package com.feudeforet.infrastructure.affichage;

import com.feudeforet.domaine.modele.Foret;

public class AfficheurConsole {
	
	public void afficher(Foret foret, int etape) {

        System.out.println("Étape " + etape);

        for (int i = 0; i < foret.getHauteur(); i++) {
            for (int j = 0; j < foret.getLargeur(); j++) {

                switch (foret.get(i, j)) {
                    case ARBRE -> System.out.print(".");
                    case EN_FEU -> System.out.print("F");
                    case CENDRE -> System.out.print("X");
                }
            }
            System.out.println();
        }

        System.out.println();
    }
}
