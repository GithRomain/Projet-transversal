package Ajout;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class RedimensionnerImage {

    public static boolean estPremier(int n){
        for (int i = 2; i<n; i++){
            if (n%i == 0){
                return false;
            }
        }
        return true;
    }

    public static int plusGrandPremier(int n) {
        while (estPremier(n)==false){
            n+=1;
        }
        return n;
    }

    public static double[][] changer_crypt(double[][] tab){
        int p = plusGrandPremier(Math.max(tab.length, tab[0].length));
        double[][] nouvtab = new double[p][p];
        for (int g = 0 ; g < p ; g++){
            for (int h = 0 ; h < p; h++){
                if ( (g < tab.length ) && (h < tab[0].length)){
                    nouvtab[g][h] = tab[g][h];
                }
                else {
                    nouvtab[g][h] = -16777216;
                }
            }
        }
        return nouvtab;
    }

    public static double[][] changer_decrypte(double[][] tab, int x, int y){
        double[][] nouvtab = new double[x][y];
        for (int g = 0 ; g < x ; g++){
            for (int h = 0 ; h < y; h++){
                if ( (g < tab.length ) && (h < tab[0].length)){
                    nouvtab[g][h] = tab[g][h];
                }
                else {
                    nouvtab[g][h] = -16777216;
                }
            }
        }
        return nouvtab;
    }
}
