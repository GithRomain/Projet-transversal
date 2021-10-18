package Householder;
import java.util.Random;
import java.lang.StringBuilder;

public class UtilMath
{
    //Classe comportant des méthodes utilisées dans d'autres classes.
    //Opérations sur les matrices, affichage de matrice, etc...
    public static int sommeCarreTab(int [] tab, int size)
    {
        //Fait la somme carré des valeurs du tableau, aka norme au carré
        int somme = 0;
        for (int i = 0; i < size; i++)
        {
            somme += (int) Math.pow(tab[i],2);
        }
        return somme;
    }

    public static void afficherTab(int [] tab)
    {
        //Affiche les valeurs d'un tableau d'entiers
        for (int val : tab)
        {
            System.out.println(val);
        }
    }

    public static boolean detecterCarreParfait(int n)
    {
        //Renvoie true si l'entier est un carré parfait, false sinon
        int racine = (int) Math.sqrt(n);
        return (int) Math.pow(racine, 2) == n;
    }

    public static double [][] genererMatriceIdentite(int n)
    {
        //Renvoie une matrice identite sous la forme d'un tableau 2D
        double [][] matriceIdentite = new double[n][n];
        for (int i = 0; i<n; i++)
        {
            matriceIdentite[i][i] = 1;
        }
        return matriceIdentite;
    }

    public static void afficherMatriceInt(int [][] mat)
    {
        //Affiche les coefficients d'une matrice composée de valeurs entières
        for (int[] ints : mat) {
            for (int j = 0; j < mat[0].length; j++) {
                System.out.print(ints[j] + " ");
            }
            System.out.println();
        }
    }

    public static void afficherMatriceDouble(double [][] mat)
    {
        //Affiche les coefficients d'une matrice composée de valeurs doubles
        for (double[] doubles : mat) {
            for (int j = 0; j < mat[0].length; j++) {
                System.out.print(doubles[j] + " ");
            }
            System.out.println();
        }
    }

    public static double [][] produitMatricielColin(int [] tab)
    {
        //Renvoie le produit matriciel d'un vecteur colonne et d'un vecteur ligne.
        //Le résultat est une matrice carré de taille n si n est la taille du vecteur.
        int n = tab.length;
        double [][] nouvelleMatrice = new double[n][n];

        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                nouvelleMatrice[i][j] = tab[i] * tab[j];
            }
        }
        return nouvelleMatrice;
    }

    public static double [][] produitDoubleMatrice(double mult, double [][] mat)
    {
        //Renvoie le résultat d'un produit entre un double et une matrice
        int n = mat.length;
        double [][] nouvelleMatrice = new double[n][n];

        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                nouvelleMatrice[i][j] = mult *mat[i][j];
            }
        }
        return nouvelleMatrice;
    }

    public static double [][] sommeMatriceDouble(double [][] mat1, double [][] mat2)
    {
        //Renvoie la somme de deux matrices composées de valeurs double
        int n = mat1.length;
        double [][] nouvelleMat = new double[n][n];
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                nouvelleMat[i][j] = mat1[i][j] + mat2[i][j];
            }
        }
        return nouvelleMat;
    }

    public static int [][] arrondiMatrice(double [][] mat)
    {
        //Arrondie les valeurs de la matrice double et renvoie la matrice en int
        int n = mat.length;
        int [][] nouvelleMatrice = new int[n][n];

        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                nouvelleMatrice[i][j] = (int) Math.round(mat[i][j]);
            }
        }
        return nouvelleMatrice;
    }

    public static double [][] produitMatricielMemeDimension(double [][] mat1, double [][] mat2)
    {
        //Renvoie le produit matriciel de mat1 et mat2 qui sont de même dimension
        int n = mat1.length;
        double [][] nouvelleMat = new double[n][n];

        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                nouvelleMat[i][j] = 0;
                for (int k = 0; k < n; k++)
                {
                    nouvelleMat[i][j] += mat1[i][k] * mat2[k][j];
                }
            }
        }
        return nouvelleMat;
    }

    public static void matriceModulo(int [][] mat)
    {
        //Modifie sur place les valeurs de la matrice modulo 256
        int n = mat.length;
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                mat[i][j] = mat[i][j] % 256;
            }
        }
    }

    public static void matriceCoeffPositif(int [][] mat)
    {
        //Modifie sur place les valeurs pour les rendre positives
        int n = mat.length;
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                mat[i][j] = Math.abs(mat[i][j]);
            }
        }
    }

    public static int [][] matriceInversionLincol(int [][] mat)
    {
        //Renvoie la matrice inversée selon les lignes et les colonnes
        int n = mat.length;
        int [][] nouvelleMatrice = new int[n][n];

        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                nouvelleMatrice[i][j] = mat[j][i];
            }
        }
        return nouvelleMatrice;
    }

    public static String conversionDecimalBinaire(int n)
    {
        //Renvoie un String qui est la représentation binaire sur 8 bits de l'int n
        //Utilise la classe Stringbuilder
        StringBuilder builder = new StringBuilder();
        while (n >= 1)
        {
            builder.append(n%2);
            n /= 2;
        }
        while (builder.length() < 8)
        {
            builder.append(0);
        }
        StringBuilder reverse = builder.reverse();
        return reverse.toString();
    }

    public static String [][] conversionDecimalBinaireMatrice(int [][] mat)
    {
        //Renvoie une matrice de String dont les valeurs sont les représentations binaires sur 8 bits des valeurs de la matrice
        int n = mat.length;
        String [][] matBinaire = new String[n][n];
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                matBinaire[i][j] = UtilMath.conversionDecimalBinaire(mat[i][j]);
            }
        }
        return matBinaire;
    }

    public static int [][] conversionHuitBinaireDecimalMatrice(String [][] mat)
    {
        int n = mat.length;
        int [][] nouvelleMatriceInt = new int [n][n];
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                nouvelleMatriceInt[i][j] = Integer.parseInt(mat[i][j],2);
            }
        }
        return nouvelleMatriceInt;
    }

    public static String porteXOR(String str1, String str2)
    {
        //Renvoie un String qui est la sortie d'une porte XOR entre deux binaires sur 8 bits
        //Utiliser la classe Stringbuilder
        int n = str1.length();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < n; i++)
        {
            if (str1.charAt(i) == str2.charAt(i))
            {
                builder.append(0);
            }
            else
            {
                builder.append(1);
            }
        }
        return builder.toString();
    }

    public static String [][] porteXORMatrice(String [][] mat1, String [][] mat2)
    {
        //Renvoie une matrice de String dont les valeurs sont les sorties d'une porte XOR
        //entre les valeurs binaires en 8 bits contenues dans les deux matrices en argument
        int n = mat1.length;
        String [][] nouvelleMatriceString = new String[n][n];
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                nouvelleMatriceString[i][j] = porteXOR(mat1[i][j],mat2[i][j]);
            }
        }
        return nouvelleMatriceString;
    }

    public static double [][] conversionMatriceIntEnDouble(int [][] mat)
    {
        //Renvoie une matrice double,copie de la matrice int
        int n = mat.length;
        double [][] nouvelleMatriceDouble = new double[n][n];
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                nouvelleMatriceDouble[i][j] = mat[i][j];
            }
        }
        return nouvelleMatriceDouble;
    }

    public static double [][] genererMatriceDouble(int n)
    {
        //Renvoie une matrice double de taille n dont les valeurs ont été générées aléatoirement entre 0 et 255

        double [][] nouvelleMatriceDouble = new double[n][n];
        Random random = new Random();
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                nouvelleMatriceDouble[i][j] = random.nextInt(255);
            }
        }
        return nouvelleMatriceDouble;

    }
}
