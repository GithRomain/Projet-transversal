package Projet;

abstract class Question_ok {
    private static int N = 0;
    private String T;
    private int D;
    private String Q;
    private String R;

    //Constructeurs
    public Question_ok(String T, int D, String Q, String R) {
        this.T = T;
        this.D = D;
        this.Q = Q;
        this.R = R;
        N += 1; //incr N
    }

    public Question_ok() {
        N += 1; //incr N
    }

    //Getters
    public int getN() {
        return N;
    }

    public String getT() {
        return T;
    }

    public int getD() {
        return D;
    }

    public String getD_String() {
        switch (D) {
            case 1: {
                return "Niveau facile";
            }
            case 2: {
                return "Niveau moyen";
            }
            case 3: {
                return "Niveau difficile";
            }
            default:
                return "Le niveau de difficulté rentré n'existe pas";
        }
    }

    public String getQ() {
        return Q;
    }

    public String getR() {
        return R;
    }

    //Setters
    public void setT(String t) {
        T = t;
    }

    public void setD(int d) {
        D = d;
    }

    public void setQ(String q) {
        Q = q;
    }

    public void setR(String r) {
        R = r;
    }

    //Méthodes
    public abstract void saisie_question();

    public abstract String toString();
}
