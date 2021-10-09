import java.awt.image.BufferedImage;
import java.io.IOException;

public class Cryptage
{
    public Cryptage(String path) throws IOException {
        System.out.println("Cryptage start");

        //Générer clef
        new Clef();
        String key = Clef.get_clef();
        System.out.println("\n" + "Clef : " + key + "\n");

        //Ouvrir l'image
        BufferedImage image = Fichier.read_img(path);
        double[][] tab_image = Conversion.img_to_tab(image);

        //Affine
        double[][] tab_crypte_affine = Cryptage.Affine.cryptage_tab(tab_image, key.substring(0, 16));

        //Autres(travailler sur les tableaux)

        //Creer l'image et la sauvegarder
        BufferedImage image_crypte_final = Conversion.tab_to_img(tab_crypte_affine);
        String file_crypte = "Images_cryptées\\image_crypte.bmp";
        Fichier.write_img(image_crypte_final, file_crypte);

        System.out.println("Cryptage complete");
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
    class Houselder
    {

    }
    class Recursif
    {

    }
    class Rotation_RGB
    {

    }
}
