package com.company;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class fichier
{
    public static BufferedImage read_img(String path) throws IOException {
        BufferedImage image = ImageIO.read(new File(path));
        System.out.println("reading complete");

        return image;
    }

    public static void write_img(BufferedImage image, String file) throws IOException
    {
        ImageIO.write(image, "jpg", new File(file));
        System.out.println("writing complete");
    }
}

class conversion
{
    public static int[][] img_to_tab(BufferedImage image)
    {
        int size_i = image.getHeight();
        int size_j = image.getWidth();

        int[][] tab = new int[size_i][size_j];

        for(int i = 0; i < size_i; i ++)
        {
            for(int j = 0; j < size_j; j++)
            {
                tab[i][j] = image.getRGB(j, i);
            }
        }
        return tab;
    }

    public static BufferedImage tab_to_img(int[][] tab) throws IOException {
        int size_i = tab.length;
        int size_j = tab[0].length;

        BufferedImage image = new BufferedImage(size_j, size_i, BufferedImage.TYPE_INT_RGB);

        int size_i2 = image.getWidth();
        int size_j2 = image.getHeight();

        for(int i = 0; i < size_i2; i ++)
        {
            for(int j = 0; j < size_j2; j++)
            {
                image.setRGB(i, j, tab[j][i]);
            }
        }
        return image;
    }
}

class clef
{
    class affine
    {

    }
}

class cryptage
{
    class affine extends annexe
    {
        public static int[][] cryptage_tab(int[][] tab, int a, int b, int c, int d)
       {
            int size_i = tab.length;
            int size_j = tab[0].length;

            int[][] tab_crypte = new int[size_i][size_j];

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
        public static int[][] code(int[][] tab_image, int a, int b, int c, int d, String file) throws IOException {
            int [][] tab_crypte = cryptage.affine.cryptage_tab(tab_image, a, b, c, d);
            BufferedImage image_crypte = conversion.tab_to_img(tab_crypte);
            fichier.write_img(image_crypte, file);
            return tab_crypte;
        }
    }

}

class decryptage
{
    class affine extends annexe
    {
        public static int[][] décryptage_tab(int[][] tab_crypte, int a, int b, int c, int d)
        {
            int size_i = tab_crypte.length;
            int size_j = tab_crypte[0].length;

            int[][] tab_decrypte = new int[size_i][size_j];

            int new_i;
            int new_j;

            for(int i = 0; i < size_i; i ++)
            {
                for(int j = 0; j < size_j; j++)
                {
                    new_i = fonction_affine(a, i, b, size_i);
                    new_j = fonction_affine(c, j, d, size_j);

                    tab_decrypte[i][j] = tab_crypte[new_i][new_j];
                }
            }
            return tab_decrypte;
        }
        public static int[][] code(int[][] tab_crypte, int a, int b, int c, int d, String file) throws IOException {
            int[][] tab_decrypte = decryptage.affine.décryptage_tab(tab_crypte, a, b, c, d);
            BufferedImage image_decrypte = conversion.tab_to_img(tab_decrypte);
            fichier.write_img(image_decrypte, file);
            return tab_decrypte;
        }
    }

}

class annexe
{
    public static int fonction_affine(int m, int k, int p, int size)
    {
        return (m * k + p) % (size);
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        int a = 4;
        int b = 1;
        int c = 2;
        int d = 3;

        String path = "Images\\image.jpg";
        BufferedImage image = fichier.read_img(path);
        int[][] tab_image = conversion.img_to_tab(image);

        String file_crypte = "Images_cryptées\\image_crypte.jpg";
        int [][] tab_crypte = cryptage.affine.code(tab_image, a, b, c, d, file_crypte);

        String file_decrypte = "Images_décryptées\\image_decrypte.jpg";
        int [][] tab_decrypte = decryptage.affine.code(tab_crypte, a, b, c, d, file_decrypte);

        System.out.println("end");
    }
}
