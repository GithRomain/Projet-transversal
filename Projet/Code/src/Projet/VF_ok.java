package Projet;

import java.util.*;

class VF_ok extends Question_ok
{
    private int num;

    //Constructeurs
    public VF_ok(String T, int D, String Q, String R)
    {
        super(T, D, Q, R); //pour augmenter N
        num = getN();
    }

    public VF_ok() {
        super(); //pour augmenter N
        num = getN();
        saisie_question();
    }

    //Getters

    //Setters

    //Methodes
    public void saisie_question()
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nSaisie question VF");
        System.out.println("(String) Saisir thème : ");
        String T = scanner.next();
        System.out.println("(int) Saisir niveau de difficulté : ");
        int D = scanner.nextInt();
        System.out.println("(String) Saisir question : ");
        String Q = scanner.nextLine();
        Q = scanner.nextLine();
        System.out.println("(Boolean) Saisir reponse : ");
        String R = scanner.next();

        setT(T);
        setD(D);
        setQ(Q);
        setR(R);
    }

    @Override
    public String toString() {
        return "Type : VF : Numéro : " + num + ", Theme : " + getT() + ", Niveau de difficulté : " + getD_String() + ", Enoncé : " + getQ() + ", Réponses proposées : " + "Vrai ou Faux";
    }
}
