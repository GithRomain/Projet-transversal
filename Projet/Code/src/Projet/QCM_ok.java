package Projet;

import java.util.Scanner;

class QCM_ok extends Question_ok {
    private String C1, C2, C3;
    private int num;

    //Constructeurs
    public QCM_ok(String T, int D, String Q, String C1, String C2, String C3, String R) {
        super(T, D, Q, R); //On incrémente N
        this.C1 = C1;
        this.C2 = C2;
        this.C3 = C3;
        num = getN();
    }

    public QCM_ok() {
        super(); //On incrémente N
        num = getN();
        saisie_question();
    }

    //Getters

    //Setters
    public void setC1(String c1) {
        C1 = c1;
    }

    public void setC2(String c2) {
        C2 = c2;
    }

    public void setC3(String c3) {
        C3 = c3;
    }

    //Methodes
    public void saisie_question() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n Saisie question QCM");
        System.out.println("(String) Saisir thème : ");
        String T = scanner.next();
        System.out.println("(int) Saisir niveau de difficulté : ");
        int D = scanner.nextInt();
        System.out.println("(String) Saisir question : ");
        String Q = scanner.nextLine();
        Q = scanner.nextLine();
        System.out.println("(String) Saisir choix 1 : ");
        String C1 = scanner.next();
        System.out.println("(String) Saisir choix 2 : ");
        String C2 = scanner.next();
        System.out.println("(String) Saisir choix 3 : ");
        String C3 = scanner.next();
        System.out.println("(String) Saisir reponse : ");
        String R = scanner.next();

        setT(T);
        setD(D);
        setQ(Q);
        setC1(C1);
        setC2(C2);
        setC3(C3);
        setR(R);
    }

    @Override
    public String toString() {
        return "Type : QCM, Numéro : " + num + ", Theme : " + getT() + ", Niveau de difficulté : " + getD_String() + ", Enoncé : " + getQ() + ", Réponses proposées : " + C1 + " ou " + C2 + " ou " + C3;
    }
}
