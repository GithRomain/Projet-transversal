package Projet;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Joueurs_ok
{
    private List<Joueur_ok> joueurs;

    //Constructeurs
    public Joueurs_ok()
    {
        joueurs = new ArrayList<>();
        int ind = 0;
        int numero = 100;
        while (joueurs.size() != 20)
        {
            char nom = (char) (ind + 'A');
            int score = 0;
            String etat = "en attente";

            Joueur_ok joueur = new Joueur_ok(numero, nom, score, etat);
            joueurs.add(joueur);

            ind += 1;
            numero += 10;
        }
    }

    //Getters
    public List<Joueur_ok> getJoueurs() {
        return joueurs;
    }

    //Setters
    public void setJoueurs(List<Joueur_ok> joueurs) {
        this.joueurs = joueurs;
    }

    //Methodes
    public int selection_aleatoire_joueur()
    {
        int ind_alea = new Random().nextInt(joueurs.size());
        if(joueurs.get(ind_alea).getEtat().equals("éliminé"))
        {
            return ind_alea;
        }
        else {
            joueurs.get(ind_alea).setEtat("sélectionné");
            return ind_alea;
        }
    }

    public void generer_ensemble_participants(int n)
    {
        List<Joueur_ok> new_joueurs = new ArrayList<>();

        while (new_joueurs.size() != n)
        {
            int ind = selection_aleatoire_joueur();
            if(joueurs.get(ind).getEtat().equals("éliminé"))
            {
                joueurs.remove(ind);
            }
            else {
                new_joueurs.add(joueurs.get(ind));
                joueurs.remove(ind);
            }
        }
        setJoueurs(new_joueurs);
    }

    @Override
    public String toString() {
        String str = "\nListe des joueurs :";

        for(Joueur_ok joueur : joueurs)
        {
            str += "\n" + joueur;
        }
        return str;
    }
}
