package Code;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Scanner;

public class Decryptage
{
    public Decryptage(String path, String path_dossier_decrypte, String key) throws IOException {
        System.out.println("Code.Decryptage start");

        //Demander clef
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n" + "Code.Clef : ");
        System.out.println("\n" + key);

        //Ouvrir l'image
        BufferedImage image = Fichier.read_img(path);
        double[][] tab_image = Conversion.img_to_tab(image);

        //Affine
        double[][] tab_decrypte_affine = Decryptage.Affine.decryptage_tab(tab_image, key.substring(0, 16));
        String key_2 = key.substring(16);
        double[][] tab_decrypte_affine_dim = Ajout.RedimensionnerImage.changer_decrypte(tab_decrypte_affine, Code.Annexe.key_x_y(key_2)[0], Code.Annexe.key_x_y(key_2)[1]);

        //Creer l'image et la sauvegarder
        BufferedImage image_decrypte = Conversion.tab_to_img(tab_decrypte_affine_dim);
        String file_decrypte = path_dossier_decrypte + "//Cryptimage_decrypte.bmp";
        Fichier.write_img(image_decrypte, file_decrypte);

        System.out.println("\n" + "Code.Decryptage complete");
    }

    class Affine
    {
        public static double[][] decryptage_tab(double[][] tab_crypte, String clef)
        {
            int[] coef = Clef.Affine.find_a_b_c_d(clef, tab_crypte);

            int a = coef[0];
            int b = coef[1];
            int c = coef[2];
            int d = coef[3];

            int size_i = tab_crypte.length;
            int size_j = tab_crypte[0].length;

            double[][] tab_decrypte = new double[size_i][size_j];

            int new_i;
            int new_j;

            for(int i = 0; i < size_i; i ++)
            {
                for(int j = 0; j < size_j; j++)
                {
                    new_i = Annexe.fonction_affine(a, i, b, size_i);
                    new_j = Annexe.fonction_affine(c, j, d, size_j);

                    tab_decrypte[i][j] = tab_crypte[new_i][new_j];
                }
            }
            return tab_decrypte;
        }
    }
}
