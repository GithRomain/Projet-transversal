package Householder;

//import BigFraction.BigFraction;

import Test.App;

import java.awt.image.BufferedImage;
import java.io.*;

public class HouseholderRGB
{
    private int n;
    private int [][] cle = new int[3][n];

    //test
    private double [][] matriceHouseholder;
    //private BigFraction [][] matriceHouseholderBig;
    private String [][] matriceHBinaire;
    private String [][] matriceEBinaire;
    //test
    private double [][][] matriceRGB; //Utilisé en cryptage et décryptage
    private double [][][] matriceQRGB;

    private BufferedImage imageCrypteeRGB;//Résultat du cryptage

    private BufferedImage imageDecrypteeRGB;//Résultat du décryptage

    public double [][][] getMatriceQRGB()
    {
        return matriceQRGB;
    }

    public BufferedImage getImageCrypteeRGB()
    {
        return imageCrypteeRGB;
    }

    public BufferedImage getImageDecrypteeRGB()
    {
        return imageDecrypteeRGB;
    }

    public HouseholderRGB(BufferedImage image) throws IOException
    {
           matriceRGB = MatriceRGB.img_to_tab_rgb(image);
           n = matriceRGB[0].length;
           matriceQRGB = genererMatriceQRGB();
           imageCrypteeRGB = MatriceRGB.tab_to_img_rgb(matriceQRGB);
    }

    public HouseholderRGB(double[][][] matriceRGB) throws IOException
    {
        this.matriceRGB = matriceRGB;
        n = matriceRGB[0].length;
        matriceQRGB = genererMatriceQRGB();
        imageCrypteeRGB = MatriceRGB.tab_to_img_rgb(matriceQRGB);
    }

    public HouseholderRGB(BufferedImage imageCrypteeRGB, int [][] cle) throws IOException
    {
        matriceQRGB = MatriceRGB.img_to_tab_rgb(imageCrypteeRGB);
        n = matriceQRGB[0].length;
        this.cle = cle;
        matriceRGB = genererMatriceRGB();
        imageDecrypteeRGB = MatriceRGB.tab_to_img_rgb(matriceRGB);
    }

    public String cleToStringherm()
    {
        StringBuilder builder = new StringBuilder("{");

        for (int [] partieCle : cle)
        {
            builder.append("{");
            for(int coeff : partieCle)
            {
                builder.append(coeff).append(",");
            }
            builder.append("}");
        }
        builder.append("}");
        return builder.toString();
    }

    public void ecrit(String s) throws IOException {
        App app = new App();
        File file = new File(app.path_texte);
        if(file.createNewFile()){};
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(s);
        bufferedWriter.close();
        fileWriter.close();
    }

    public String cleToString()
    {
        StringBuilder builder = new StringBuilder();

        for (int [] partieCle : cle)
        {
            for(int coeff : partieCle)
            {
                if(coeff == partieCle[partieCle.length-1])
                {
                    builder.append(coeff).append("");
                }
                else
                {
                    builder.append(coeff).append(",");
                }
            }
            builder.append(";");
        }
        return builder.toString();
    }

    public int [][] getCle()
    {
        return cle;
    }

    public double[][] getMatriceHouseholder() {
        return matriceHouseholder;
    }

    public String[][] getMatriceHBinaire() {
        return matriceHBinaire;
    }

    /*
    public BigFraction[][] getMatriceHouseholderBig() {
        return matriceHouseholderBig;
    }
    */

    public String[][] getMatriceEBinaire() {
        return matriceEBinaire;
    }

    private double [][][] genererMatriceQRGB()
    {
        //Génère la matrice cryptée en RGB et remplit la clé.
        double [][][] nouvelleMatriceQRGB = new double[3][n][n];

        Householder householder1 = new Householder(matriceRGB[0]);
        Householder householder2 = new Householder(matriceRGB[1]);
        Householder householder3 = new Householder(matriceRGB[2]);

        nouvelleMatriceQRGB[0] = householder1.getMatriceQ();
        matriceHouseholder = householder1.getMatriceHouseholder();
        //matriceHouseholderBig = householder1.getMatriceHouseholderBig();
        matriceHBinaire = householder1.getMatriceHBinaire();
        matriceEBinaire = householder1.getMatriceEBinaire();
        //UtilBigFraction.afficherMatriceBig(householder1.getMatriceHouseholderBig());
        //System.out.println();
        //TestBigDecimal.afficherMatriceBig(householder1.getMatriceHouseholderBig());

        //UtilMath.afficherMatriceInt(householder1.getMatriceD());
        //System.out.println();
        //UtilMath.afficherMatriceInt(householder1.getMatriceDBig());

        nouvelleMatriceQRGB[1] = householder2.getMatriceQ();
        nouvelleMatriceQRGB[2] = householder3.getMatriceQ();

        cle[0] = householder1.getCle();
        cle[1] = householder2.getCle();
        cle[2] = householder3.getCle();

        return nouvelleMatriceQRGB;
    }

    private double [][][] genererMatriceRGB()
    {
        //Génère la matrice décryptée en RGB grâce à la clé
        double [][][] nouvelleMatriceRGB = new double[3][n][n];

        Householder householder1 = new Householder(matriceQRGB[0],cle[0]);
        Householder householder2 = new Householder(matriceQRGB[1],cle[1]);
        Householder householder3 = new Householder(matriceQRGB[2],cle[2]);

        nouvelleMatriceRGB[0] = householder1.getMatriceImage();
        matriceHouseholder = householder1.getMatriceHouseholder();
        //matriceHouseholderBig = householder1.getMatriceHouseholderBig();
        matriceHBinaire = householder1.getMatriceHBinaire();
        matriceEBinaire = householder1.getMatriceEBinaire();
        UtilMath.afficherMatriceInt(householder1.getMatriceD());
        //UtilBigFraction.afficherMatriceBig(UtilBigFraction.produitMatricielBig(householder1.getMatriceHouseholderBig(),householder1.getMatriceHouseholderBig()));

        nouvelleMatriceRGB[1] = householder2.getMatriceImage();
        nouvelleMatriceRGB[2] = householder3.getMatriceImage();

        return nouvelleMatriceRGB;
    }
}
