import java.awt.image.BufferedImage;
import java.io.IOException;

class Conversion
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

        BufferedImage image = new BufferedImage(size_j, size_i, BufferedImage.TYPE_INT_RGB);

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
