package com.feudeforet.domaine.port;

/**
 * Interface pour générer des nombres aléatoires
 */
public interface GenerateurAleatoire {
	/**
	 * Génère un nombre aléatoire entre 0 et 1
	 *
	 * @return un nombre aléatoire
	 */
	double prochainDouble();
}
