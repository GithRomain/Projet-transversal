import java.util.Scanner;

public class Annexe
{
    public static int fonction_affine(int m, int k, int p, int size)
    {
        return (m * k + p) % (size);
    }
    public static int menu()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter : 0, 1 or 2 / Cryptage : 1   Decryptage : 2 / Stop : 0");
        int num = scanner.nextInt();
        return num;
    }
}