package Householder;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class Main {
    public static void main(String[] Args) throws IOException {

        String file_source = "C:\\Users\\37rom\\OneDrive - Efrei\\Bureau\\EFREI\\Semestre 5\\Projet\\Images\\carre.png";

        String file_crypte = "C:\\Users\\37rom\\OneDrive - Efrei\\Bureau\\EFREI\\Semestre 5\\Projet\\Images_cryptées\\image_hous.bmp";

        String file_decrypte = "C:\\Users\\37rom\\OneDrive - Efrei\\Bureau\\EFREI\\Semestre 5\\Projet\\Images_décryptées\\image_hous.bmp";

        //CRYPTAGE
        BufferedImage image = Fichier.read_img(file_source);

        HouseholderRGB householderRGBCryptage = new HouseholderRGB(image); //où image est un BufferedImage
        BufferedImage imageCryptee = householderRGBCryptage.getImageCrypteeRGB(); //on récupère l'image cryptée
        householderRGBCryptage.getCle(); //on récupère un tableau qui contient les 3 parties de clé de taille n
        Fichier.write_img(imageCryptee,file_crypte); //on enregistre l'image dans un file

        /*
        String clef = householderRGBCryptage.cleToString();
        int[][] mat_test = householderRGBCryptage.stringToMatrice(clef);

        //DECRYPTAGE
        HouseholderRGB householderRGBDecryptage = new HouseholderRGB(imageCryptee, mat_test); //cle du même format que celle récupéré
        BufferedImage imageDecryptee = householderRGBDecryptage.getImageDecrypteeRGB(); //on récupère
        Fichier.write_img(imageDecryptee,file_decrypte); //on enregistre

         */
    }
}
