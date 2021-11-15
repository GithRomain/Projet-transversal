package Projet;

import java.util.Random;
import java.util.Scanner;

class Phase3 implements Phase {
    private final int num_phase = 3;
    private Joueurs_ok joueurs;
    private Themes_ok themes;
    private Questions questions;

    //Constructeurs
    public Phase3(Phase phase) {
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
        joueurs.generer_ensemble_participants(2); //Générer les 3 joueurs
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
            joueur.mise_a_jour_score(5);
            System.out.println("Réponse juste");
        } else {
            System.out.println("Réponse fausse");
        }
    }

    public void question_reponse(int nb_question_par_themes) {
        for (int k = 0; k < themes.getThemeList().size(); k++) {
            themes.setInd_theme(k);
            questions.setInd_question(new Random().nextInt(questions.getMat_questions().get(themes.getInd_theme()).size()));
            for (int j = 0; j < nb_question_par_themes; j++) {
                for (int i = 0; i < joueurs.getJoueurs().size(); i++) {
                    Joueur_ok joueur = joueurs.getJoueurs().get(i);
                    Question_ok question = questions.selection(num_phase);

                    poser_question(joueur, question);

                    String reponse = reponse_question(question);

                    test_reponse(joueur, question, reponse);
                    joueur.setEtat("répondu");
                }
            }
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
        System.out.println("\nPhase 3 :");

        selectionner_joueur();
        System.out.println(joueurs);

        System.out.println("\nMaitre du jeu : ");
        questions.selection_n_theme_saisie(3);
        System.out.println(themes);

        question_reponse(2);

        determiner_gagant_et_elimine();

        joueurs.generer_ensemble_participants(1);
        joueurs.getJoueurs().get(0).setEtat("super gagnant");

        return joueurs.getJoueurs().get(0);
    }
}
