package com.company;

public class PremierSuperieur {

    public static boolean estPremier(int n) {
        double p = n;
        boolean test = true;
        for (int i = 2 ; i < Math.sqrt(p); i++){
            if (p%i == 0) {
                test = false;
            }
        }
        return test;
    }

    public static int premierSupp(int n){
        boolean test = false;
        int pTest = n;
        while (test == false){
            test = estPremier(pTest);
            pTest++;
        }
        pTest--;
        return pTest;
    }

}
