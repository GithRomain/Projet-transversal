import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class Fichier
{
    public static BufferedImage read_img(String path) throws IOException {
        BufferedImage image = ImageIO.read(new File(path));
        return image;
    }
    public static void write_img(BufferedImage image, String file) throws IOException {
        ImageIO.write(image, "bmp", new File(file));
    }
}
