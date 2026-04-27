package com.feudeforet.domaine.modele;

public class Foret {
	
		private final int largeur;
	    private final int hauteur;
	    private final EtatCase[][] grille;
	
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
	
	    public EtatCase get(int x, int y) {
	        return grille[x][y];
	    }
	
	    public void set(int x, int y, EtatCase etat) {
	        grille[x][y] = etat;
	    }
	
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
