import java.io.IOException;

public class Main extends Annexe{
    public static void main(String[] args) throws IOException {
        int num = menu();

        String path_ouverture_cryptage = "Images\\4k.jpg";
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
    }
}
