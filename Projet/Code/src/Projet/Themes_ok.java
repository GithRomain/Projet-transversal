package Projet;

import java.util.*;

class Themes_ok
{
    private List<String> themeList;
    private int ind_theme;

    //Constructeur
    public Themes_ok(List<String> themeList)
    {
        this.themeList = themeList;
        ind_theme = new Random().nextInt(this.themeList.size());
    }

    //Getters
    public List<String> getThemeList() {
        return themeList;
    }

    public int getInd_theme() {
        return ind_theme;
    }

    //Setters
    public void setInd_theme(int ind_theme) {
        this.ind_theme = ind_theme;
    }

    //Methode
    public int selection_theme(String new_theme) //gerer exception, theme exist pas dans la liste
    {
        for(String theme : themeList)
        {
            if(theme.equals(new_theme))
            {
                if(themeList.indexOf(theme) != ind_theme)
                {
                    setInd_theme(themeList.indexOf(theme));
                }
            }
        }
        return ind_theme;
    }

    public void selection_theme_saisie(List<String> stringList_select) //à mettre dans questions plutot
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nSaisir un indice parmis les thèmes :\n" + string(stringList_select));

        int ind = scanner.nextInt();
        setInd_theme(selection_theme(stringList_select.get(ind)));
    }

    public List<String> selection_n_theme(int n) //à mettre dans questions plutot
    {
        List<String> themesList_select = new ArrayList<>();
        while(themesList_select.size() != n)
        {
            int ind = new Random().nextInt(themeList.size());
            if(!themesList_select.contains(themeList.get(ind)))
            {
                themesList_select.add(themeList.get(ind));
            }
        }
        return themesList_select;
    }

    public void suppr(String theme)
    {
        themeList.remove(selection_theme(theme));
    }

    public String string(List<String> stringList)
    {
        String str = "";
        for(String theme : stringList)
        {
            str += theme + ", Indice : " + stringList.indexOf(theme)
                    + "\n";
        }
        return str;
    }

    @Override
    public String toString()
    {
        String str = "\nListe des thèmes :\n";
        for(String theme : themeList)
        {
            str += theme + ", Indice : " + themeList.indexOf(theme)
                    + "\n";
        }
        return str;
    }
}
