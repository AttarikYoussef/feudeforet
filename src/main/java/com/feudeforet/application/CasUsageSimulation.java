package com.feudeforet.application;

import com.feudeforet.domaine.modele.EtatCase;
import com.feudeforet.domaine.modele.Foret;
import com.feudeforet.domaine.service.ServicePropagationFeu;
import com.feudeforet.infrastructure.affichage.AfficheurConsole;

public class CasUsageSimulation {
	
	private final ServicePropagationFeu service;
    private final AfficheurConsole afficheur;

    public CasUsageSimulation(ServicePropagationFeu service, AfficheurConsole afficheur) {
        this.service = service;
        this.afficheur = afficheur;
    }
    
    public void executer(Foret foret) {

        int etape = 0;

        while (contientFeu(foret)) {
            afficheur.afficher(foret, etape++);
            foret = service.etapeSuivante(foret);
        }

        afficheur.afficher(foret, etape);
    }
    
    private boolean contientFeu(Foret foret) {
        for (int i = 0; i < foret.getHauteur(); i++) {
            for (int j = 0; j < foret.getLargeur(); j++) {
                if (foret.get(i, j) == EtatCase.EN_FEU) {
                    return true;
                }
            }
        }
        return false;
    }
}
