package Householder;

//import BigFraction;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

public class Householder
{
    private int n;                                      //taille de la matrice
    private long debut = System.currentTimeMillis();
    private double [][] matriceImage;
    private int [] cle;
    private int [] cleArrondi;
    private int [] cleComplete;
    private double [][] matriceI;

    private double [][] matriceHouseholder; //Utilisée en cryptage et décryptage
    //private BigFraction [][] matriceHouseholderBig;
    private int [][] matriceD;
    private int [][] matriceDBig;
    private int [][] matriceE;
    private String [][] matriceEBinaire;
    private String [][] matriceHBinaire;
    private double [][] matriceQ;

    private String [][] matriceQBinaire; //Utilisée en décryptage uniquement

    public Householder(double [][] matriceImage)       //constructeur pour crypter une image
    {
        if (matriceImage.length > 0)
        {
            this.matriceImage = matriceImage;
            n = matriceImage.length;

            cle = completerCle();
            matriceI = UtilMath.genererMatriceIdentite(n);
            matriceHouseholder = genererHouseholder();
            //matriceHouseholderBig = genererHouseholderBig();
            matriceD = UtilMath.conversionMatriceDoubleEnInt(matriceImage);
            //matriceDBig = genererMatriceDCryptageBig();
            matriceE = genererMatriceECryptage();
            System.out.println("Le temps pour générer la matrice E est :" + (System.currentTimeMillis()-debut));
            debut = System.currentTimeMillis();
            matriceEBinaire = genererMatriceEBinaire();
            System.out.println("Le temps pour générer la matrice E Binaire est :" + (System.currentTimeMillis()-debut));
            debut = System.currentTimeMillis();
            matriceHBinaire = genererMatriceHBinaire();
            System.out.println("Le temps pour générer la matrice H Binaire est :" + (System.currentTimeMillis()-debut));
            debut = System.currentTimeMillis();
            matriceQ = genererMatriceQ();
        }
    }

    public Householder(double [][] matriceQ, int [] cle)    //constructeur pour décrypter une image
    {
        if (matriceQ.length > 0)
        {
            this.matriceQ = matriceQ;
            n = matriceQ.length;
            this.cle = cle;

            matriceI = UtilMath.genererMatriceIdentite(n);
            matriceHouseholder = genererHouseholder();
            //matriceHouseholderBig = genererHouseholderBig();
            matriceHBinaire = genererMatriceHBinaire();
            matriceQBinaire = genererMatriceQBinaire();
            matriceE = genererMatriceEDecryptage();
            matriceD = genererMatriceDDecryptage();
            matriceImage = UtilMath.conversionMatriceIntEnDouble(matriceD);
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

    public double [][] getMatriceHouseholder()
    {
        return matriceHouseholder;
    }

    /*public BigFraction [][] getMatriceHouseholderBig()
    {
        return matriceHouseholderBig;
    }*/

    public int [][] getMatriceD()
    {
        return matriceD;
    }

    public int [][] getMatriceDBig()
    {
        return matriceDBig;
    }

    public String [][] getMatriceEBinaire()
    {
        return matriceEBinaire;
    }

    public String [][] getMatriceHBinaire()
    {
        return matriceHBinaire;
    }

    public double [][] getMatriceQ()
    {
        return matriceQ;
    }

    public double [][] getMatriceImage()
    {
        return matriceImage;
    }

    private int [] genererCleVide()
    {
        return new int[n];
    }

    private int genererCoeff()
    {
        //Méthode pour générer un coeff compris entre INF et SUP (taille d'un short)
        Random random = new Random();
        //à choisir, borne inférieur des coeff possibles
        int INF = -256001;
        //à choisir, borne supérieur des coeff possibles
        int SUP = 256000;
        return INF + random.nextInt( SUP - INF);
    }

    //Le bloc suivant n'est pas utilisé, merci à la fausse condition dans la thèse.
    //Peut être que ça servira plus tard mais pour l'instant, il n'y a aucune différence,
    //La matrice Householder est bien orthogonale symétrique.
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
        int maxMult = (1023/n);
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

    private int [] completerCle()
    {
        //Méthode pour compléter aléatoirement les n premiers
        //coefficients du vecteur
        //renvoie le vecteur
        int [] cleVide = genererCleVide();
        for (int i = 0; i < n; i++)
        {
            cleVide[i] = genererCoeff();
        }
        System.out.println("Temps pour générer la clé :" + (System.currentTimeMillis()-debut));
        debut = System.currentTimeMillis();
        return cleVide;
    }

    private double [][] genererHouseholder()
    {
        //Méthode pour calculer et renvoyer la matrice Householder, prend en argument la clé
        double [][] matriceIdentite = UtilMath.genererMatriceIdentite(n);   //génère une matrice identité de taille n, premier terme
        double multiplieur = (double) -2/UtilMath.sommeCarreTab(cle,n);     //multiplieur -2/norme au carré
        //System.out.println(multiplieur);
        //System.out.println();

        double [][] matriceCle = UtilMath.produitMatricielColin(cle);       //génère la matrice carré issue de la clé

        double [][] secondTerme = UtilMath.produitDoubleMatrice(multiplieur,matriceCle);    //calcule le produit de multiplieur*matriceCle

        System.out.println("Temps pour générer Householder :" + (System.currentTimeMillis()-debut));
        debut = System.currentTimeMillis();
        return UtilMath.sommeMatriceDouble(matriceIdentite, secondTerme);     //somme des 2 termes
    }

    /*private BigDecimal [][] genererHouseholderBig()
    {
        BigDecimal [][] matriceIdentite = TestBigDecimal.genererMatriceIdentiteBig(n);
        BigDecimal multiplieur = new BigDecimal(-2);
        multiplieur = multiplieur.divide(TestBigDecimal.normeCarre(cle),RoundingMode.HALF_EVEN);
        System.out.println(multiplieur);
        System.out.println();

        BigDecimal [][] matriceCle = TestBigDecimal.produitMatricielColinBig(cle);
        //TestBigDecimal.afficherMatriceBig(matriceCle);
        //System.out.println();
        BigDecimal [][] secondTerme = TestBigDecimal.produitDecimalMatrice(multiplieur,matriceCle);
        //TestBigDecimal.afficherMatriceBig(secondTerme);
        //System.out.println();

        System.out.println("Temps pour générer Householder Big:" + (System.currentTimeMillis()-debut));
        debut = System.currentTimeMillis();
        return TestBigDecimal.sommeMatriceBig(matriceIdentite, secondTerme);
    }*/

    /*private BigFraction [][] genererHouseholderBig()
    {
        BigFraction [][] matriceIdentite = UtilBigFraction.genererMatriceIdentiteBig(n);

        BigFraction multiplieur = UtilBigFraction.genererMultiplieur(cle);
        //System.out.println(multiplieur);
        //System.out.println();

        BigFraction [][] matriceCle = UtilBigFraction.produitMatricielColinBig(cle);
        //UtilBigFraction.afficherMatriceBig(matriceCle);
        //System.out.println();
        BigFraction [][] secondTerme = UtilBigFraction.produitFractionMatrice(multiplieur,matriceCle);
        //UtilBigFraction.afficherMatriceBig(secondTerme);
        //System.out.println();

        System.out.println("Temps pour générer Householder Big:" + (System.currentTimeMillis()-debut));
        debut = System.currentTimeMillis();
        return UtilBigFraction.sommeMatriceBig(matriceIdentite, secondTerme);
    }*/

    private int [][] genererMatriceDCryptage()
    {
        //Méthode pour calculer et renvoyer la matrice D, première matrice cryptée

        double [][] nouvelleMatrice = UtilMath.produitMatricielMemeDimension(matriceI,matriceImage);  //produit matriciel de matHouseholder*matImage

        int [][] nouvelleMatriceInt = UtilMath.arrondiMatrice(nouvelleMatrice); //arrondi les valeurs pour pour appliquer le modulo

        UtilMath.matriceCoeffPositif(nouvelleMatriceInt); //on rend tous les coeff positifs
        UtilMath.matriceModulo(nouvelleMatriceInt); //on applique le modulo
        System.out.println("Temps pour générer la matrice D :" + (System.currentTimeMillis()-debut));
        debut = System.currentTimeMillis();
        return nouvelleMatriceInt;
    }

    /*private int [][] genererMatriceDCryptageBig()
    {
        BigFraction [][] matrice = UtilBigFraction.produitMatricielBig(matriceHouseholderBig,UtilBigFraction.matriceDoubleEnBig(matriceImage));
        int [][] matriceInt = UtilBigFraction.arrondiMatriceBig(matrice);

        UtilMath.matriceCoeffPositif(matriceInt);
        UtilMath.matriceModulo(matriceInt);
        System.out.println("Temps pour générer la matrice D Big :" + (System.currentTimeMillis()-debut));
        debut = System.currentTimeMillis();
        return matriceInt;
    }*/

    private int [][] genererMatriceECryptage()
    {
        //Méthode pour calculer et renvoyer la matrice E, inversion ligne colonne de la matrice D

        return UtilMath.matriceInversionLincol(matriceD);
    }

    private String [][] genererMatriceEBinaire()
    {
        //Méthode pour renvoyer la matrice E avec les valeurs en écriture binaire 8 bits

        return UtilMath.conversionDecimalBinaireMatrice(matriceE);
    }

    private String [][] genererMatriceHBinaire()
    {
        //Méthode pour renvoyer la matrice H binaire de comparaison pour la porte XOR

        int [][] nouvelleMatriceInt = UtilMath.arrondiMatrice(UtilMath.produitDoubleMatrice(255,matriceHouseholder));
        UtilMath.matriceCoeffPositif(nouvelleMatriceInt);
        return UtilMath.conversionDecimalBinaireMatrice(nouvelleMatriceInt);
    }

    /*private String [][] genererMatriceHBinaireBig()
    {

    }*/

    private double [][] genererMatriceQ()
    {
        //Méthode pou renvoyer la matrice Q qui est la matrice cryptée finale
        String [][] nouvelleMatriceString = UtilMath.porteXORMatrice(matriceEBinaire, matriceHBinaire);
        int [][] nouvelleMatriceInt = UtilMath.conversionHuitBinaireDecimalMatrice(nouvelleMatriceString);
        System.out.println("Temps pour générer la matrice Q :" + (System.currentTimeMillis()-debut));
        return UtilMath.conversionMatriceIntEnDouble(nouvelleMatriceInt);
    }

    //A PARTIR DE LA, METHODES POUR DECRYPTER

    private String [][] genererMatriceQBinaire()
    {
        return UtilMath.conversionDecimalBinaireMatrice(UtilMath.conversionMatriceDoubleEnInt(matriceQ));
    }

    private int [][] genererMatriceEDecryptage()
    {
        String [][] nouvelleMatriceString = UtilMath.porteXORMatrice(matriceQBinaire, matriceHBinaire);
        //test
        matriceEBinaire = nouvelleMatriceString;
        //test
        return UtilMath.conversionHuitBinaireDecimalMatrice(nouvelleMatriceString);
    }

    private int [][] genererMatriceDDecryptage()
    {
        return UtilMath.matriceInversionLincol(matriceE);
    }

    private double [][] trouverMatriceImage()
    {
        double [][] nouvelleMatriceDouble = UtilMath.produitMatricielMemeDimension(matriceI, UtilMath.conversionMatriceIntEnDouble(matriceD));

        int [][] nouvelleMatriceInt = UtilMath.arrondiMatrice(nouvelleMatriceDouble);

        UtilMath.matriceCoeffPositif(nouvelleMatriceInt);
        UtilMath.matriceModulo(nouvelleMatriceInt);
        return UtilMath.conversionMatriceIntEnDouble(nouvelleMatriceInt);
    }

    /*private double [][] trouverMatriceImageBig()
    {
        BigFraction [][] matriceBig = UtilBigFraction.produitMatricielBig(matriceHouseholderBig, UtilBigFraction.matriceDoubleEnBig(UtilMath.conversionMatriceIntEnDouble(matriceD)));

        int [][] matriceInt = UtilBigFraction.arrondiMatriceBig(matriceBig);

        UtilMath.matriceCoeffPositif(matriceInt);
        UtilMath.matriceModulo(matriceInt);
        return UtilMath.conversionMatriceIntEnDouble(matriceInt);
    }*/
}
