package Projet;

import java.util.Scanner;

class Joueur_ok {
    private int numero;
    private char nom;
    private int score;
    private String etat;

    //Constructeurs
    public Joueur_ok(int numero, char nom, int score, String etat) {
        this.numero = numero;
        this.nom = nom;
        this.score = score;
        this.etat = etat;
    }

    //Getters
    public int getNumero() {
        return numero;
    }

    public char getNom() {
        return nom;
    }

    public String getEtat() {
        return etat;
    }

    public int getScore() {
        return score;
    }

    //Setters
    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setNom(char nom) {
        this.nom = nom;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    //Methodes
    public void saisie_joueur() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n Saisie Joueur");
        System.out.println("(int) Saisir numéro : ");
        int numero = scanner.nextInt();
        System.out.println("(char) Saisir nom : ");
        String nom = scanner.next();
        System.out.println("(int) Saisir score : ");
        int score = scanner.nextInt();
        System.out.println("(String) Saisir état : ");
        String etat = scanner.nextLine();
        etat = scanner.nextLine();

        setNumero(numero);
        setNom(nom.charAt(0));
        setScore(score);
        setEtat(etat);
    }

    public void mise_a_jour_score(int points) {
        setScore(score + points);
    }

    public void changement_etat(String etat) {
        setEtat(etat);
    }

    @Override
    public String toString() {
        return "Joueur : " + "numero : " + numero + ", nom : " + nom + ", score : " + score + ", etat : " + etat;
    }
}
