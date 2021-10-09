import java.io.IOException;

public class Main extends Annexe{
    public static void main(String[] args) throws IOException {
        int num = menu();

        String path_ouverture_cryptage = "Images\\image.jpg";
        String path_ouverture_decryptage = "Images_cryptées\\image_crypte.bmp";

        switch (num)
        {
            case 0:
            {
                System.exit(0);
            }
            case 1:
            {
                new Cryptage(path_ouverture_cryptage);
                System.exit(0);
            }
            case 2:
            {
                new Decryptage(path_ouverture_decryptage);
                System.exit(0);
            }
            default :
            {
                System.out.println("Error");
                System.exit(0);
            }
        }
        /*
        String path = "Images\\carre.png";
        String file_crypte = "Images_cryptées\\image_crypte_houselder.bmp";
        BufferedImage image = fichier.read_img(path);
        Householder householder = new Householder(512);
        double [][] matriceImage = conversion.img_to_tab(image);
        int [][] matriceD = householder.genererMatriceD(matriceImage);
        BufferedImage image_crypte = conversion.tab_to_img(matriceD);
        fichier.write_img(image_crypte, file_crypte);
        */
    }
}
