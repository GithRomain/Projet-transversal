package Projet;

public class Main {
    public static void main(String[]args)
    {
        //Phase
        Phase presentation = new Presentation();
        presentation.deroulement_phase();

        Phase phase1 = new Phase1(presentation);
        phase1.deroulement_phase();

        Phase phase2 = new Phase2(phase1);
        phase2.deroulement_phase();

        Phase phase3 = new Phase3(phase2);
        Joueur_ok joueur_gagnant = phase3.deroulement_phase();

        System.out.println("\nLe gagnant du jeu est :\n" + joueur_gagnant);
    }
}
