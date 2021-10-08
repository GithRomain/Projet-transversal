import java.util.Random;

public class Householder
{
    private int n;           //taille de la matrice
    private final int INF = -128;   //à choisir, borne inférieur des coeff possibles
    private final int SUP = 127;    //à choisir, borne supérieur des coeff possibles
    private int [] cle;

    public Householder(int n)       //constructeur
    {
        if (n > 0)
        {
            this.n = n;
            cle = completerCleComplete();
        }
    }

    public int getN()
    {
        return n;
    }

    public int [] getCle()
    {
        return cle;
    }

    private int [] genererCleVide()
    {
        return new int[n];
    }

    private int genererCoeff()
    {
        //Méthode pour générer un coeff compris entre INF et SUP (taille d'un short)
        Random random = new Random();
        return INF + random.nextInt( SUP - INF);
    }

    private int [] completerCleSaufDernier()
    {
        //Méthode pour compléter aléatoirement les n-1 premiers
        //coefficients du vecteur
        //renvoie le vecteur
        int [] cleVide = genererCleVide();
        for (int i = 0; i < n-1; i++)
        {
            cleVide[i] = genererCoeff();
        }
        return cleVide;
    }

    private boolean existenceDernier(int [] tab)
    {
        //Méthode permettant de savoir s'il est possible de trouver
        //le dernier coeff de la clé
        int sommeModulo = UtilMath.sommeCarreTab(tab,n) % n;
        int ecart = n - sommeModulo + 1;

        if (sommeModulo == 0 || sommeModulo == 1)
        {
            return true;
        }
        return UtilMath.detecterCarreParfait(ecart);
    }

    private int trouverDernier(int [] tab)
    {
        //Méthode permettant de renvoyer le dernier coeff de la clé
        int maxMult = (SUP/n);
        Random random = new Random();

        int sommeModulo = UtilMath.sommeCarreTab(tab,n) % n;
        int ecart = n - sommeModulo + 1;

        int dernier = (int) Math.sqrt(ecart);

        dernier += n * random.nextInt(maxMult); //randomiser le dernier coeff, non nécessaire
        return dernier;
    }

    private int [] completerCleComplete()
    {
        //Méthode pour renvoyer la clé complète
        int [] nouvelleCle = completerCleSaufDernier();     //génère clé incomplète

        while (!existenceDernier(nouvelleCle))              //tant qu'il est impossible de compléter la clé
        {                                           //regénérer une nouvelle clé incomplète
            nouvelleCle = completerCleSaufDernier();
            System.out.println(existenceDernier(nouvelleCle));
        }
        nouvelleCle[n-1] = trouverDernier(nouvelleCle);             //complète la clé
        return nouvelleCle;
    }

    private double [][] genererHouseholder()
    {
        //Méthode pour calculer et renvoyer la matrice Householder, prend en argument la clé
        double [][] matriceIdentite = UtilMath.genererMatriceIdentite(n);   //génère une matrice identité de taille n, premier terme
        double multiplieur = (double) -2/UtilMath.sommeCarreTab(cle,n);     //multiplieur -2/norme au carré

        double [][] matriceCle = UtilMath.produitMatricielColin(cle);       //génère la matrice carré issue de la clé

        double [][] secondTerme = UtilMath.produitDoubleMatrice(multiplieur,matriceCle);    //calcule le produit de multiplieur*matriceCle

        return UtilMath.sommeMatriceDouble(matriceIdentite, secondTerme);     //somme des 2 termes
    }

    public int [][] genererMatriceD(double [][] matImage)
    {
        //Méthode pour calculer et renvoyer la matrice D, première matrice cryptée
        double [][] matHouseholder = genererHouseholder();  //génère Householder

        double [][] nouvelleMatrice = UtilMath.produitMatricielMemeDimension(matHouseholder,matImage);  //produit matriciel de matHouseholder*matImage

        int [][] nouvelleMatriceInt = UtilMath.arrondiMatrice(nouvelleMatrice); //arrondi les valeurs pour pour appliquer le modulo

        UtilMath.matriceModulo(nouvelleMatriceInt); //on applique le modulo
        return nouvelleMatriceInt;
    }



}
