package com.feudeforet.domaine.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import com.feudeforet.domaine.FauxGenerateurAleatoire;
import com.feudeforet.domaine.modele.EtatCase;
import com.feudeforet.domaine.modele.Foret;

public class ServicePropagationFeuTest {
	
	@Nested
    @DisplayName("Règles métier")
    class ReglesMetier {

        @Test
        @DisplayName("Une case en feu devient une case cendre à l'étape suivante")
        void uneCaseEnFeuDevientCendre() {
            Foret foret = new Foret(3, 3);
            foret.set(1, 1, EtatCase.EN_FEU);

            ServicePropagationFeu service =
                new ServicePropagationFeu(0.0, new FauxGenerateurAleatoire(0.0));

            Foret suivante = service.etapeSuivante(foret);

            assertEquals(EtatCase.CENDRE, suivante.get(1, 1));
        }

        @Test
        @DisplayName("Une case cendre ne peut plus brûler")
        void uneCaseCendreNeBrulePas() {
            Foret foret = new Foret(3, 3);
            foret.set(0, 1, EtatCase.CENDRE);
            foret.set(1, 1, EtatCase.EN_FEU);

            ServicePropagationFeu service =
                new ServicePropagationFeu(1.0, new FauxGenerateurAleatoire(0.0));

            Foret suivante = service.etapeSuivante(foret);

            assertEquals(EtatCase.CENDRE, suivante.get(0, 1));
        }
    }
	
	@Nested
    @DisplayName("Propagation du feu")
    class Propagation {

        @Test
        @DisplayName("Le feu ne se propage pas si la probabilité est nulle")
        void neDoitPasPropagerSiProbabiliteZero() {
            Foret foret = new Foret(3, 3);
            foret.set(1, 1, EtatCase.EN_FEU);

            ServicePropagationFeu service =
                new ServicePropagationFeu(0.0, new FauxGenerateurAleatoire(0.0));

            Foret suivante = service.etapeSuivante(foret);

            assertEquals(EtatCase.ARBRE, suivante.get(0, 1));
            assertEquals(EtatCase.ARBRE, suivante.get(2, 1));
            assertEquals(EtatCase.ARBRE, suivante.get(1, 0));
            assertEquals(EtatCase.ARBRE, suivante.get(1, 2));
        }

        @Test
        @DisplayName("Le feu se propage à tous les voisins si la probabilité est égale à 1")
        void doitPropagerSiProbabiliteUn() {
            Foret foret = new Foret(3, 3);
            foret.set(1, 1, EtatCase.EN_FEU);

            ServicePropagationFeu service =
                new ServicePropagationFeu(1.0, new FauxGenerateurAleatoire(0.0));

            Foret suivante = service.etapeSuivante(foret);

            assertEquals(EtatCase.EN_FEU, suivante.get(0, 1));
            assertEquals(EtatCase.EN_FEU, suivante.get(2, 1));
            assertEquals(EtatCase.EN_FEU, suivante.get(1, 0));
            assertEquals(EtatCase.EN_FEU, suivante.get(1, 2));
        }

        @Test
        @DisplayName("Le feu ne se propage que sur les cases de type arbre")
        void neDoitPasPropagerSurCendreOuFeu() {
            Foret foret = new Foret(3, 3);
            foret.set(1, 1, EtatCase.EN_FEU);
            foret.set(0, 1, EtatCase.CENDRE);
            foret.set(1, 0, EtatCase.EN_FEU);

            ServicePropagationFeu service =
                new ServicePropagationFeu(1.0, new FauxGenerateurAleatoire(0.0));

            Foret suivante = service.etapeSuivante(foret);

            assertEquals(EtatCase.CENDRE, suivante.get(0, 1));
            assertEquals(EtatCase.CENDRE, suivante.get(1, 0));
        }
    }
	
	@Nested
    @DisplayName("Gestion des limites de la grille")
    class Limites {

        @Test
        @DisplayName("La propagation ne sort pas des limites de la grille")
        void neDoitPasSortirDeLaGrille() {
            Foret foret = new Foret(3, 3);
            foret.set(0, 0, EtatCase.EN_FEU);

            ServicePropagationFeu service =
                new ServicePropagationFeu(1.0, new FauxGenerateurAleatoire(0.0));

            Foret suivante = service.etapeSuivante(foret);

            assertEquals(EtatCase.EN_FEU, suivante.get(0, 1));
            assertEquals(EtatCase.EN_FEU, suivante.get(1, 0));
        }
    }

}
