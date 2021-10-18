package Householder;

import java.awt.image.BufferedImage;
import java.io.IOException;

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
           matriceRGB = MatriceRGB.img_to_tab_rgb(image);
           n = matriceRGB.length;
           matriceQRGB = genererMatriceQRGB();
           imageCrypteeRGB = MatriceRGB.tab_to_img_rgb(matriceQRGB);
    }

    public HouseholderRGB(double[][][] matriceRGB) throws IOException
    {
        this.matriceRGB = matriceRGB;
        n = matriceRGB.length;
        matriceQRGB = genererMatriceQRGB();
        imageCrypteeRGB = MatriceRGB.tab_to_img_rgb(matriceQRGB);
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
