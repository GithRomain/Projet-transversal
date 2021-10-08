package com.company;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

class fichier
{
    public static BufferedImage read_img(String path) throws IOException {
        BufferedImage image = ImageIO.read(new File(path));
        System.out.println("reading complete");
        
        return image;
    }
    public static void write_img(BufferedImage image, String file) throws IOException
    {
        ImageIO.write(image, "bmp", new File(file));
        System.out.println("writing complete");
    }
}

class conversion
{
    public static double[][] img_to_tab(BufferedImage image)
    {
        int size_i = image.getHeight();
        int size_j = image.getWidth();

        double[][] tab = new double[size_i][size_j];

        for(int i = 0; i < size_i; i ++)
        {
            for(int j = 0; j < size_j; j++)
            {
                tab[i][j] = image.getRGB(j, i);
            }
        }
        return tab;
    }
    public static BufferedImage tab_to_img(double[][] tab) throws IOException {
        int size_i = tab.length;
        int size_j = tab[0].length;

        BufferedImage image = new BufferedImage(size_j, size_i, BufferedImage.TYPE_INT_ARGB);

        int size_i2 = image.getWidth();
        int size_j2 = image.getHeight();

        for(int i = 0; i < size_i2; i ++)
        {
            for(int j = 0; j < size_j2; j++)
            {
                image.setRGB(i, j, (int) tab[j][i]);
            }
        }
        return image;
    }
}

class clef extends annexe
{
    class affine
    {
        public static String create_clef_16B()
        {
            String clef = "0000000000000000";

            int max = 9;
            int min = 0;

            for(int i = 0; i < clef.length(); i++)
            {
                Random rand = new Random();
                int val = rand.nextInt(max - min + 1) + min;
                clef = clef.replaceFirst("0", val + "");
            }
            return clef;
        }
        public static int[] find_a_b_c_d(String clef, double[][] tab_image)
        {
            coef
            int[] coef = new int[4];
            int a = Integer.parseInt(clef.substring(0, 2));
            if(a % tab_image.length == 0)
            {
                System.out.println("Problème dans la création de la clef");
                System.exit(0);
            }
            int b = Integer.parseInt(clef.substring(2, 4));
            int c = Integer.parseInt(clef.substring(4, 6));
            if(c % tab_image[0].length == 0)
            {
                System.out.println("Problème dans la création de la clef");
                System.exit(0);
            }
            int d = Integer.parseInt(clef.substring(6, 8));

            coef[0] = a;
            coef[1] = b;
            coef[2] = c;
            coef[3] = d;

            return coef;
        }
    }
}

class cryptage extends annexe
{
    class affine
    {
        public static double[][] cryptage_tab(double[][] tab, int a, int b, int c, int d)
       {
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
        public static double[][] code(double[][] tab_image, String key, String file) throws IOException {
            int[] a_b_c_d = clef.affine.find_a_b_c_d(key, tab_image);
            int a = a_b_c_d[0];
            int b = a_b_c_d[1];
            int c = a_b_c_d[2];
            int d = a_b_c_d[3];

            System.out.println(a + " " + b + " "+ c + " " + d+ " ");

            double [][] tab_crypte = cryptage.affine.cryptage_tab(tab_image, a, b, c, d);
            BufferedImage image_crypte = conversion.tab_to_img(tab_crypte);
            fichier.write_img(image_crypte, file);
            System.out.println("Cryptage complete");
            return tab_crypte;
        }
    }
}

class decryptage extends annexe
{
    class affine extends annexe
    {
        public static double[][] décryptage_tab(double[][] tab_crypte, int a, int b, int c, int d)
        {
            int size_i = tab_crypte.length;
            int size_j = tab_crypte[0].length;

            double[][] tab_decrypte = new double[size_i][size_j];

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
        public static double[][] code(double[][] tab_crypte, String key, String file) throws IOException {
            int[] a_b_c_d = clef.affine.find_a_b_c_d(key, tab_crypte);
            int a = a_b_c_d[0];
            int b = a_b_c_d[1];
            int c = a_b_c_d[2];
            int d = a_b_c_d[3];

            double[][] tab_decrypte = decryptage.affine.décryptage_tab(tab_crypte, a, b, c, d);
            BufferedImage image_decrypte = conversion.tab_to_img(tab_decrypte);
            fichier.write_img(image_decrypte, file);
            System.out.println("Deryptage complete");
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
    public static int choix()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter : 1 or 2 or 0 / Cryptage : 1   Decryptage : 2 // Stop : 0");
        int num = scanner.nextInt();
        return num;
    }
}

class menu
{
    public static double[][] image(String path) throws IOException {
        BufferedImage image = fichier.read_img(path);
        double[][] tab_image = conversion.img_to_tab(image);
        return tab_image;
    }
    public static String write_clef()
    {
        String key_crypte = clef.affine.create_clef_16B();
        System.out.println("Clef : " + key_crypte);
        return key_crypte;
    }
    public static String read_clef()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Clef : ");
        String clef = scanner.next();
        return clef;
    }
}

public class Main extends annexe {
    public static void main(String[] args) throws IOException {
        int num = choix();

        if (num == 1)//Chiffrement
        {
            String path = "Images\\image.jpg";
            String file_crypte = "Images_cryptées\\image_crypte";
            cryptage.affine.code(menu.image(path), menu.write_clef(), file_crypte);
            main(args);
        }
        if (num == 2)//Dechiffrement
        {
            String path = "Images_cryptées\\image_crypte.bmp";
            String file_decrypte = "Images_décryptées\\image_decrypte";
            decryptage.affine.code(menu.image(path), menu.read_clef(), file_decrypte);
            main(args);
        }
        {
            System.exit(0);
        }
    }
}
