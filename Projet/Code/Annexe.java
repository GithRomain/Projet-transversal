import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Annexe
{
    public static double[] RGB(int color)
    {
        double blue = color & 0xff;
        double green = (color & 0xff00) >> 8;
        double red = (color & 0xff0000) >> 16;

        double[] tab = {red, green, blue};

        return tab;
    }

    public static int fonction_affine(int m, int k, int p, int size)
    {
        return (m * k + p) % (size);
    }

    public static int menu()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter : 0, 1 or 2 / Cryptage : 1   Decryptage : 2 / Stop : 0");
        int num = scanner.nextInt();
        return num;
    }

    /*
    public static double[] rotation(double[] T, int size , int a)
    {
        double[] L = new double[T.length];

        for (int i = 0; i < T.length; i++)
        {
            L[i] = T[i];
        }
        for (int i = 0; i < size; i++)
        {
            L[(i + Math.abs(a)) % size] = T[i];
        }
        return L;
    }
     */

    public static String convertFromBaseToBase(String str, int fromBase, int toBase)//long = 10, base = 36
    {
        return Integer.toString(Integer.parseInt(str, fromBase), toBase);
    }

    public static double [][][] img_to_tab_rgb(BufferedImage image)
    {
        int size_i = image.getHeight();
        int size_j = image.getWidth();

        double [][][] tab = new double[3][size_i][size_j];

        for(int i = 0; i < size_i; i ++)
        {
            for(int j = 0; j < size_j; j++)
            {
                for (int k = 0; k < 3; k++)
                {
                    int color = image.getRGB(j, i);
                    tab[k][i][j] = RGB(color)[k];
                }
            }
        }
        return tab;
    }

    public static double [][][] genererMatriceRGB (int n)
    {
        double [][][] nouvelleMatriceRGB = new double[3][n][n];
        Random random = new Random();

        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                for (int k = 0; k < 3; k++)
                {
                    if (i <= n/2 && j <= n/2)
                    {
                        nouvelleMatriceRGB[k][i][j] = 30;
                    }
                    else if (i > n/2 && j <= n/2)
                    {
                        nouvelleMatriceRGB[k][i][j] = 240;
                    }
                    else if (i <= n/2 && j > n/2)
                    {
                        nouvelleMatriceRGB[k][i][j] = 100;
                    }
                    else
                    {
                        nouvelleMatriceRGB[k][i][j] = 180;
                    }
                    /*nouvelleMatriceRGB[k][i][j] = random.nextInt(255);*/
                }
            }
        }
        return nouvelleMatriceRGB;
    }

    public static BufferedImage tab_to_img_rgb(double[][][] tab) throws IOException
    {
        int size_i = tab[0].length;
        int size_j = tab[0][0].length;

        BufferedImage image = new BufferedImage(size_j, size_i, BufferedImage.TYPE_INT_RGB);

        int size_i2 = image.getWidth();
        int size_j2 = image.getHeight();

        for(int i = 0; i < size_i2; i ++)
        {
            for(int j = 0; j < size_j2; j++)
            {
                for(int k = 0; k < 3; k++)
                {
                    Color color = new Color((int) tab[0][j][i], (int) tab[1][j][i], (int) tab[2][j][i]);
                    image.setRGB(i, j, color.getRGB());
                }
            }
        }
        return image;
    }

    /*public static double [][][] formatageRGB(double [][][] matriceRGB)
    {
        int n = matriceRGB.length;
        double [][][] nouvelleMatriceRGB = new double[3][n][n];

        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                f
            }
        }
    }*/
}
