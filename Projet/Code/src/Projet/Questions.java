package Projet;

import java.util.*;

class Questions { //faire tableaux en fct du theme
    private Themes_ok themes;
    private List<LinkedList<Question_ok>> mat_questions; //exception taille entre 5 et 10 à faire
    private int ind_question;

    //Constructeur
    public Questions(Themes_ok themes) {
        this.themes = themes;
        mat_questions = new ArrayList<LinkedList<Question_ok>>();
        for (String theme : themes.getThemeList()) {
            mat_questions.add(new LinkedList<>());
        }
        //ind_question = new Random().nextInt(mat_questions.get(this.themes.getInd_theme()).size()); deja init dans presentation
    }

    //Getters
    public List<LinkedList<Question_ok>> getMat_questions() {
        return mat_questions;
    }

    //Setters
    public void setInd_question(int ind_question) {
        this.ind_question = ind_question;
    }

    //Methodes
    public void ajout_question(Question_ok question) {
        mat_questions.get(themes.selection_theme(question.getT())).add(question);
    }

    public void suppr_question(String theme, int i) {
        mat_questions.get(themes.selection_theme(theme)).remove(i);
    }

    public void suppr_theme(String theme) {
        mat_questions.remove(themes.selection_theme(theme));
        themes.suppr(theme);
    }

    public void selection_n_theme_saisie(int n) {
        List<Integer> integerList = new LinkedList<>();
        List<Integer> integerList2 = new LinkedList<>();
        List<String> stringList = new LinkedList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nSaisir 3 indices differents parmis :" + themes);

        for (int i = 0; i < n; i++) {
            int ind = scanner.nextInt();
            integerList.add(ind);
        }
        for (int i = 0; i < themes.getThemeList().size(); i++) {
            if (!integerList.contains(i)) {
                integerList2.add(i);
            }
        }
        for (int i : integerList2) {
            stringList.add(themes.getThemeList().get(i));
        }

        for (int i = 0; i < integerList2.size(); i++) {
            suppr_theme(stringList.get(i));
        }
    }

    public Question_ok selection(int phase) {
        Question_ok question = null;
        switch (phase) {
            case 1: {
                while (question == null) {
                    if (phase == mat_questions.get(themes.getInd_theme()).get(ind_question).getD()) {
                        question = mat_questions.get(themes.getInd_theme()).get(ind_question);
                        setInd_question((ind_question + 1) % mat_questions.get(themes.getInd_theme()).size());
                    } else {
                        setInd_question((ind_question + 1) % mat_questions.get(themes.getInd_theme()).size());
                    }
                }
                break;
            }
            case 2: {
                List<String> stringList_select = themes.selection_n_theme(5);
                themes.selection_theme_saisie(stringList_select);
                setInd_question(new Random().nextInt(mat_questions.get(themes.getInd_theme()).size()));

                while (question == null) {
                    if (phase == mat_questions.get(themes.getInd_theme()).get(ind_question).getD()) {
                        question = mat_questions.get(themes.getInd_theme()).get(ind_question);
                        suppr_theme(themes.getThemeList().get(themes.getInd_theme()));
                    } else {
                        setInd_question((ind_question + 1) % mat_questions.get(themes.getInd_theme()).size());
                    }
                }
                break;
            }
            case 3: {
                while (question == null) {
                    if (phase == mat_questions.get(themes.getInd_theme()).get(ind_question).getD()) {
                        question = mat_questions.get(themes.getInd_theme()).get(ind_question);
                        setInd_question((ind_question + 1) % mat_questions.get(themes.getInd_theme()).size());
                    } else {
                        setInd_question((ind_question + 1) % mat_questions.get(themes.getInd_theme()).size());
                    }
                }
                break;
            }
            default: //pass
        }
        return question;
    }

    @Override
    public String toString() {
        String str = "Listes des questions par thèmes :";

        for (LinkedList<Question_ok> liste_question : mat_questions) {
            str += "\n" + themes.getThemeList().get(mat_questions.indexOf(liste_question)) + " :";
            for (Question_ok question : liste_question) {
                str += "\n  - " + question;
            }
            str += "\n";
        }
        return str;
    }
}
