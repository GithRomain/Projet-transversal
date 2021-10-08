public class testHouseholder
{
    public static void main(String [] args)
    {
        long debut = System.currentTimeMillis();
        Householder householder = new Householder(8);

        /*int [] cle = householder.completerCleComplete();*/

        /*System.out.println(UtilMath.sommeCarreTab(cle,householder.getN() - 1) % householder.getN());
        System.out.println(UtilMath.sommeCarreTab(cle,householder.getN()) % householder.getN());
        System.out.println(UtilMath.sommeCarreTab(cle,householder.getN()));*/

        /*double [][] matriceIdentite = UtilMath.genererMatriceIdentite(householder.getN());
        UtilMath.afficherMatriceDouble(matriceIdentite);*/

        /*double [][] matriceHouseholder = householder.genererHouseholder(cle);
        UtilMath.afficherMatriceDouble(matriceHouseholder);*/

        double [][] matriceImage ={    {120,80,150,20,240,190,220,80},
                                    {50,30,55,95,35,230,250,255},
                                    {20,30,50,245,205,195,110,125},
                                    {10,250,165,140,180,225,145,180},
                                    {55,75,135,15,5,0,30,155},
                                    {250,115,125,255,100,120,80,85},
                                    {55,75,125,175,215,220,45,50},
                                    {0,120,240,150,30,75,170,180}   };

        int [][] matriceD = householder.genererMatriceD(matriceImage);
        UtilMath.afficherMatriceInt(matriceD);

        System.out.println(System.currentTimeMillis() - debut);

    }
}
