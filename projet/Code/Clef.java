import java.util.Random;

public class Clef
{
    private static String key;
    public Clef()
    {
        String clef_affine = Clef.Affine.get_clef_affine();
        key = clef_affine;//rajouter les autres clefs apres
    }
    public static String get_clef()
    {
        return key;
    }

    class Affine
    {
        private static String get_clef_affine()
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
            int[] coef = new int[4];

            int a = Integer.parseInt(clef.substring(Integer.parseInt(clef.substring(8, 9)), Integer.parseInt(clef.substring(8, 9))+1)) * Integer.parseInt(clef.substring(Integer.parseInt(clef.substring(9, 10)), Integer.parseInt(clef.substring(9, 10))+1));

            if(a % tab_image.length == 0)
            {
                System.out.println("Problème dans la création de la clef");
                System.exit(0);
            }

            int b = Integer.parseInt(clef.substring(Integer.parseInt(clef.substring(10, 11)), Integer.parseInt(clef.substring(10, 11))+1)) * Integer.parseInt(clef.substring(Integer.parseInt(clef.substring(11, 12)), Integer.parseInt(clef.substring(11, 12))+1));
            int c = Integer.parseInt(clef.substring(Integer.parseInt(clef.substring(12, 13)), Integer.parseInt(clef.substring(12, 13))+1)) * Integer.parseInt(clef.substring(Integer.parseInt(clef.substring(13, 14)), Integer.parseInt(clef.substring(13, 14))+1));

            if(c % tab_image[0].length == 0)
            {
                System.out.println("Problème dans la création de la clef");
                System.exit(0);
            }

            int d = Integer.parseInt(clef.substring(Integer.parseInt(clef.substring(14, 15)), Integer.parseInt(clef.substring(14, 15))+1)) * Integer.parseInt(clef.substring(Integer.parseInt(clef.substring(15, 16)), Integer.parseInt(clef.substring(15, 16))+1));

            coef[0] = a;
            coef[1] = b;
            coef[2] = c;
            coef[3] = d;

            return coef;
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
