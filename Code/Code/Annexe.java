package Code;

import Test.App;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Annexe
{
    public static int[][] stringToMatrice(String s){
        String[] newString = s.split(";");
        String[] tempo = newString[0].split(",");
        int[][] matrice = new int[newString.length][tempo.length];
        for (int i = 0; i < newString.length ; i++){
            tempo = newString[i].split(",");
            for (int j = 0 ; j < tempo.length ; j++){
                matrice[i][j] = Integer.parseInt(tempo[j]);
            }
        }
        return matrice;
    }

    public static int fonction_affine(int m, int k, int p, int size)
    {
        return (m * k + p) % (size);
    }

    public static int menu()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter : 0, 1 or 2 / Code.Cryptage : 1   Code.Decryptage : 2 / Stop : 0");
        int num = scanner.nextInt();
        return num;
    }

    public static int[] key_x_y(String key)
    {
        String x = "";
        String y = "";
        int k = 0;
        for(int i = 0; i < key.length(); i++)
        {
            if((key.substring(i, i+1)).equals("_"))
            {
                k = i+1;
                break;
            }
            else
            {
                x += key.substring(i, i+1);
            }
        }

        for(int i = k; i < key.length(); i++)
        {
            y += key.substring(i, i+1);
        }

        int[] tab = {Integer.parseInt(x), Integer.parseInt(y)};
        return tab;
    }
}
