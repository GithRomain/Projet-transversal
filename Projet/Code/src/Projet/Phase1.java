package Projet;

import java.util.Scanner;

class Phase1 implements Phase {
    private final int num_phase = 1;
    private Joueurs_ok joueurs;
    private Themes_ok themes;
    private Questions questions;

    //Constructeurs
    public Phase1(Phase phase) {
        joueurs = phase.getJoueurs();
        themes = phase.getThemes();
        questions = phase.getQuestions();
    }

    //Getters
    public Joueurs_ok getJoueurs() {
        return joueurs;
    }

    public Themes_ok getThemes() {
        return themes;
    }

    public Questions getQuestions() {
        return questions;
    }

    //Setters

    //Methodes
    public void selectionner_joueur() {
        joueurs.generer_ensemble_participants(4); //Générer les 4 joueurs
    }

    public void poser_question(Joueur_ok joueur, Question_ok question) {
        String str = "\nJoueur : " + joueur.getNom()
                + "\nRépondez à la question suivante : \n"
                + question;
        System.out.println(str);
    }

    public String reponse_question(Question_ok question) {
        Scanner scanner = new Scanner(System.in);
        String reponse = scanner.next();

        return reponse;
    }

    public void test_reponse(Joueur_ok joueur, Question_ok question, String reponse) {
        if (question.getR().equals(reponse)) {
            joueur.mise_a_jour_score(2);
            System.out.println("Réponse juste");
        } else {
            System.out.println("Réponse fausse");
        }
    }

    public void question_reponse() {
        for (int i = 0; i < joueurs.getJoueurs().size(); i++) {
            Joueur_ok joueur = joueurs.getJoueurs().get(i);
            Question_ok question = questions.selection(num_phase);

            poser_question(joueur, question);

            String reponse = reponse_question(question);

            test_reponse(joueur, question, reponse);
            joueur.setEtat("répondu");
        }
    }

    public void determiner_gagant_et_elimine() {
        int maxIndex = 0;
        int minIndex = 0;
        for (int i = 1; i < joueurs.getJoueurs().size(); i++) {
            if (joueurs.getJoueurs().get(i).getScore() > joueurs.getJoueurs().get(maxIndex).getScore()) {
                maxIndex = i;
            }
        }
        for (int i = 1; i < joueurs.getJoueurs().size(); i++) {
            if (joueurs.getJoueurs().get(i).getScore() < joueurs.getJoueurs().get(maxIndex).getScore()) {
                minIndex = i;
            }
        }

        joueurs.getJoueurs().get(maxIndex).changement_etat("super gagnant");
        joueurs.getJoueurs().get(minIndex).changement_etat("éliminé");

        for (Joueur_ok joueur : joueurs.getJoueurs()) {
            if (joueur.getEtat().equals("répondu")) {
                joueur.setEtat("gagnant");
            }
        }

        System.out.println(joueurs);
    }

    public Joueur_ok deroulement_phase() {
        System.out.println("\nPhase 1 :");

        selectionner_joueur();
        System.out.println(joueurs);

        question_reponse();

        determiner_gagant_et_elimine();
        return null;
    }
}
