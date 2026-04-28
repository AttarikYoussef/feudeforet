package com.feudeforet.infrastructure.aleatoire;

import java.util.Random;

import com.feudeforet.domaine.port.GenerateurAleatoire;

/**
 * Implémentation basée sur Random
 */
public class GenerateurAleatoireJava implements GenerateurAleatoire {

    private final Random random = new Random();

    /**
     * Retourne un nombre aléatoire entre 0 et 1
     */
    @Override
    public double prochainDouble() {
        return random.nextDouble();
    }

}
