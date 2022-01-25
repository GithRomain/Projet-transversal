package Code;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class Cryptage
{
    private String key;

    public Cryptage(String path, String path_dossier_crypte) throws IOException {
        System.out.println("Code.Cryptage start");

        long debut = System.currentTimeMillis();

        //Ouvrir l'image
        BufferedImage image = Fichier.read_img(path);
        double[][] tab_image = Conversion.img_to_tab(image);
        double[][] tab_image_dim = Ajout.RedimensionnerImage.changer_crypt(tab_image);

        //Générer clef
        new Clef(tab_image.length, tab_image[0].length);
        String key = Clef.get_clef();
        this.key = key;
        System.out.println("\n" + "Code.Clef : " + key + "\n");

        //Affine
        double[][] tab_crypte_affine = Cryptage.Affine.cryptage_tab(tab_image_dim, key.substring(0, 16));
        BufferedImage image_crypte_affine = Conversion.tab_to_img(tab_crypte_affine);

        //Creer l'image et la sauvegarder
        String file_crypte = path_dossier_crypte + "\\image_crypte.bmp";
        Fichier.write_img(image_crypte_affine, file_crypte);

        System.out.println("\n" + "Code.Cryptage complete" + "\n");

        System.out.println("Temps : " + ((System.currentTimeMillis() - debut) / 1000) % 60 + " secondes");
    }

    public String get_clef()
    {
        return key;
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
                    new_i = Annexe.fonction_affine(a, i, b, size_i);
                    new_j = Annexe.fonction_affine(c, j, d, size_j);

                    tab_crypte[new_i][new_j] = tab[i][j];
                }
            }
            return tab_crypte;
        }
    }
}
