package com.feudeforet.domaine.modele;

/**
 * Représente la grille de la forêt
 */
public class Foret {
	
		private final int largeur;
	    private final int hauteur;
	    private final EtatCase[][] grille;
	
	    /**
	     * Crée une forêt avec une taille donnée
	     *
	     * @param largeur nombre de colonnes
	     * @param hauteur nombre de lignes
	     */
	    public Foret(int largeur, int hauteur) {
	        this.largeur = largeur;
	        this.hauteur = hauteur;
	        this.grille = new EtatCase[hauteur][largeur];
	
	        for (int i = 0; i < hauteur; i++) {
	            for (int j = 0; j < largeur; j++) {
	                grille[i][j] = EtatCase.ARBRE;
	            }
	        }
	    }
	
	    /**
	     * Retourne l'état d'une case
	     *
	     * @param x position x
	     * @param y position y
	     * @return l'état de la case
	     */
	    public EtatCase get(int x, int y) {
	        return grille[x][y];
	    }
	
	    /**
	     * Modifie l'état d'une case
	     *
	     * @param x position x
	     * @param y position y
	     * @param etat nouvel état de la case
	     */
	    public void set(int x, int y, EtatCase etat) {
	        grille[x][y] = etat;
	    }
	
	    /**
	     * Vérifie si une position est dans la grille
	     *
	     * @param x position x
	     * @param y position y
	     * @return true si la position est valide
	     */
	    public boolean estDansLaGrille(int x, int y) {
	        return x >= 0 && x < hauteur && y >= 0 && y < largeur;
	    }
	
	    public int getHauteur() {
	        return hauteur;
	    }
	
	    public int getLargeur() {
	        return largeur;
	    }
	
	    public Foret copier() {
	        Foret copie = new Foret(largeur, hauteur);
	
	        for (int i = 0; i < hauteur; i++) {
	            System.arraycopy(grille[i], 0, copie.grille[i], 0, largeur);
	        }
	
	        return copie;
	    }
}
