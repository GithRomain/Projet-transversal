package Projet;

interface Phase {
    //public abstract void selectionner_joueur();

    public abstract Joueurs_ok getJoueurs();

    public abstract Themes_ok getThemes();

    public abstract Questions getQuestions();

    public abstract Joueur_ok deroulement_phase();
}
