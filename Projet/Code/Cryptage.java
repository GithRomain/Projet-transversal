import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class Cryptage extends Annexe
{
    public Cryptage(String path) throws IOException {
        System.out.println("Cryptage start");

        long debut = System.currentTimeMillis();

        //Générer clef
        new Clef();
        String key = Clef.get_clef();
        System.out.println("\n" + "Clef : " + key + "\n");

        //Ouvrir l'image
        BufferedImage image = Fichier.read_img(path);
        double[][] tab_image = Conversion.img_to_tab(image);

        //Affine
        double[][] tab_crypte_affine = Cryptage.Affine.cryptage_tab(tab_image, key.substring(0, 16));
        BufferedImage image_crypte_affine = Conversion.tab_to_img(tab_crypte_affine);

        //Householder
        BufferedImage image_crypte_final = new HouseholderRGB(image_crypte_affine).getImageCrypteeRGB();

        //Creer l'image et la sauvegarder
        String file_crypte = "Images_cryptées\\image_crypte.bmp";
        Fichier.write_img(image_crypte_final, file_crypte);

        System.out.println("\n" + "Cryptage complete" + "\n");

        System.out.println("Temps : " + ((System.currentTimeMillis() - debut) / 1000) % 60 + " secondes");
    }

    class Affine
    {
        private static double[][] cryptage_tab(double[][] tab, String clef)
        {
            int[] coef = Clef.Affine.find_a_b_c_d(clef, tab);

            int a = coef[0];
            int b = coef[1];
            int c = coef[2];
            int d = coef[3];

            int size_i = tab.length;
            int size_j = tab[0].length;

            double[][] tab_crypte = new double[size_i][size_j];

            int new_i;
            int new_j;

            for(int i = 0; i < size_i; i ++)
            {
                for(int j = 0; j < size_j; j++)
                {
                    new_i = fonction_affine(a, i, b, size_i);
                    new_j = fonction_affine(c, j, d, size_j);

                    tab_crypte[new_i][new_j] = tab[i][j];
                }
            }
            return tab_crypte;
        }
    }

    public class Householder
    {
        private int n;                                      //taille de la matrice
        private long debut = System.currentTimeMillis();
        private double [][] matriceImage;
        private int [] cle;

        private double [][] matriceHouseholder;
        private int [][] matriceD;
        private int [][] matriceE;
        private String [][] matriceEBinaire;
        private String [][] matriceHBinaire;
        private double [][] matriceQ;

        public Householder(double [][] matriceImage)       //constructeur
        {
            if (matriceImage.length > 0)
            {
                this.matriceImage = matriceImage;
                n = matriceImage.length;

                cle = completerCle();
                matriceHouseholder = genererHouseholder();
                matriceD = genererMatriceD();
                matriceE = genererMatriceE();
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

        public int getN()
        {
            return n;
        }

        public int [] getCle()
        {
            return cle;
        }

        public double[][] getMatriceHouseholder()
        {
            return matriceHouseholder;
        }

        public int[][] getMatriceD()
        {
            return matriceD;
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

        private int [] genererCleVide()
        {
            return new int[n];
        }

        private int genererCoeff()
        {
            //Méthode pour générer un coeff compris entre INF et SUP (taille d'un short)
            Random random = new Random();
            //à choisir, borne inférieur des coeff possibles
            int INF = -1048576;
            //à choisir, borne supérieur des coeff possibles
            int SUP = 1048575;
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
            int maxMult = (1048575/n);
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

            double [][] matriceCle = UtilMath.produitMatricielColin(cle);       //génère la matrice carré issue de la clé

            double [][] secondTerme = UtilMath.produitDoubleMatrice(multiplieur,matriceCle);    //calcule le produit de multiplieur*matriceCle

            System.out.println("Temps pour générer Householder :" + (System.currentTimeMillis()-debut));
            debut = System.currentTimeMillis();
            return UtilMath.sommeMatriceDouble(matriceIdentite, secondTerme);     //somme des 2 termes
        }

        private int [][] genererMatriceD()
        {
            //Méthode pour calculer et renvoyer la matrice D, première matrice cryptée

            double [][] nouvelleMatrice = UtilMath.produitMatricielMemeDimension(matriceHouseholder,matriceImage);  //produit matriciel de matHouseholder*matImage

            int [][] nouvelleMatriceInt = UtilMath.arrondiMatrice(nouvelleMatrice); //arrondi les valeurs pour pour appliquer le modulo

            UtilMath.matriceCoeffPositif(nouvelleMatriceInt); //on rend tous les coeff positifs
            UtilMath.matriceModulo(nouvelleMatriceInt); //on applique le modulo
            System.out.println("Temps pour générer la matrice D :" + (System.currentTimeMillis()-debut));
            debut = System.currentTimeMillis();
            return nouvelleMatriceInt;
        }

        private int [][] genererMatriceE()
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

        private double [][] genererMatriceQ()
        {
            String [][] nouvelleMatriceString = UtilMath.porteXORMatrice(matriceEBinaire, matriceHBinaire);
            int [][] nouvelleMatriceInt = UtilMath.conversionHuitBinaireDecimalMatrice(nouvelleMatriceString);
            System.out.println("Temps pour générer la matrice Q :" + (System.currentTimeMillis()-debut));
            return UtilMath.conversionMatriceIntEnDouble(nouvelleMatriceInt);
        }

    }

    public class HouseholderRGB
    {
        private int n;

        private double [][][] matriceRGB;
        private double [][][] matriceQRGB;
        private BufferedImage imageCrypteeRGB;

        public double [][][] getMatriceQRGB()
        {
            return matriceQRGB;
        }

        public BufferedImage getImageCrypteeRGB()
        {
            return imageCrypteeRGB;
        }

        public HouseholderRGB(BufferedImage image) throws IOException
        {
            matriceRGB = Annexe.img_to_tab_rgb(image);
            n = matriceRGB.length;
            matriceQRGB = genererMatriceQRGB();
            imageCrypteeRGB = Annexe.tab_to_img_rgb(matriceQRGB);
        }

        public HouseholderRGB(double[][][] matriceRGB) throws IOException
        {
            this.matriceRGB = matriceRGB;
            n = matriceRGB.length;
            matriceQRGB = genererMatriceQRGB();
            imageCrypteeRGB = Annexe.tab_to_img_rgb(matriceQRGB);
        }

        private double [][][] genererMatriceQRGB()
        {
            double [][][] nouvelleMatriceQRGB = new double[3][n][n];

            nouvelleMatriceQRGB[0] = new Householder(matriceRGB[0]).getMatriceQ();
            nouvelleMatriceQRGB[1] = new Householder(matriceRGB[1]).getMatriceQ();
            nouvelleMatriceQRGB[2] = new Householder(matriceRGB[2]).getMatriceQ();

            return nouvelleMatriceQRGB;
        }
    }
}
