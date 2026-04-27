package com.feudeforet.infrastructure.aleatoire;

import java.util.Random;

import com.feudeforet.domaine.port.GenerateurAleatoire;

public class GenerateurAleatoireJava implements GenerateurAleatoire {

    private final Random random = new Random();

    @Override
    public double prochainDouble() {
        return random.nextDouble();
    }

}
