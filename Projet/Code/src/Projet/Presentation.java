package Projet;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

class Presentation implements Phase
{
    private Joueurs_ok joueurs;
    private Themes_ok themes;
    private Questions questions;

    //Constructeurs
    public Presentation() {}

    //Getters
    public Joueurs_ok getJoueurs() {
        return joueurs;
    }

    public Themes_ok getThemes() {
        return themes;
    }

    public Questions getQuestions() {
        return questions;
    }

    public void selectionner_joueur() {}

    public void initialisation_jeu()
    {
        //Creer la liste des 20 joueurs
        joueurs = new Joueurs_ok();

        //4, 1, 2

        //Creer la liste des themes
        List<String> themeList = new ArrayList<>();
        themeList.add("Animaux");
        themeList.add("Sport");
        themeList.add("Voiture");
        themeList.add("Histoire");
        themeList.add("Français");
        themeList.add("Cinema");
        themeList.add("Géographie");
        themeList.add("Corps humain");
        themeList.add("Faits divers");
        themeList.add("Jeux vidéos");

        themes = new Themes_ok(themeList);

        //Creer la liste des questions
        Question_ok question0 = new VF_ok("Animaux", 1, "Les chauves-souris sont les seuls mammifères qui peuvent voler", "true");
        Question_ok question1 = new QCM_ok("Animaux", 1, "Qui est le cheval de lucky luke ?", "Wolly wopper", "Dalton", "Jolly jumper", "Jolly jumper");
        Question_ok question2 = new VF_ok("Animaux", 1, "Les rongeurs communiquent souvent à l’aide de vibrations", "true");
        Question_ok question3 = new RC_ok("Animaux", 1, "Quel mammifère marin est un cousin de l'éléphant ?", "Le lamantin");
        Question_ok question4 = new QCM_ok("Animaux", 2, "Quel est le plus grand mammifère marin de la planète ?", "Le requin", "La tortue", "La baleine", "La baleine");
        Question_ok question5 = new RC_ok("Animaux", 3, "Que mange le gorille ?", "Des plantes");
        Question_ok question6 = new VF_ok("Animaux", 3, "Le diable de Tasmanie vient d'Australie", "true");

        Question_ok question7 = new VF_ok("Sport", 1, "Tony Parker est considéré comme le meilleur joueur de basket-ball de tous les temps", "false");
        Question_ok question8 = new QCM_ok("Sport", 1, "Combien de joueurs composent une équipe de rugby ?", "5", "10", "15", "15");
        Question_ok question9 = new VF_ok("Sport", 1, "le volley-ball a était inventé au japon", "false");
        Question_ok question10 = new RC_ok("Sport", 1, "Quel mammifère marin est un cousin de l'éléphant ?", "Le lamantin");
        Question_ok question11 = new QCM_ok("Sport", 2, "Quel est le plus grand mammifère marin de la planète ?", "Le requin", "La tortue", "La baleine", "La baleine");
        Question_ok question12 = new RC_ok("Sport", 3, "Que mange le gorille ?", "Des plantes");
        Question_ok question13 = new VF_ok("Sport", 3, "Le diable de Tasmanie vient d'Australie", "true");

        Question_ok question14 = new VF_ok("Voiture", 1, "Les chauves-souris sont les seuls mammifères qui peuvent voler", "true");
        Question_ok question15 = new QCM_ok("Voiture", 1, "Qui est le cheval de lucky luke ?", "Wolly wopper", "Dalton", "Jolly jumper", "Jolly jumper");
        Question_ok question16 = new VF_ok("Voiture", 1, "Les rongeurs communiquent souvent à l’aide de vibrations", "true");
        Question_ok question17 = new RC_ok("Voiture", 1, "Quel mammifère marin est un cousin de l'éléphant ?", "Le lamantin");
        Question_ok question18 = new QCM_ok("Voiture", 2, "Quel est le plus grand mammifère marin de la planète ?", "Le requin", "La tortue", "La baleine", "La baleine");
        Question_ok question19 = new RC_ok("Voiture", 3, "Que mange le gorille ?", "Des plantes");
        Question_ok question20 = new VF_ok("Voiture", 3, "Le diable de Tasmanie vient d'Australie", "true");

        Question_ok question21 = new VF_ok("Histoire", 1, "Les chauves-souris sont les seuls mammifères qui peuvent voler", "true");
        Question_ok question22 = new QCM_ok("Histoire", 1, "Qui est le cheval de lucky luke ?", "Wolly wopper", "Dalton", "Jolly jumper", "Jolly jumper");
        Question_ok question23 = new VF_ok("Histoire", 1, "Les rongeurs communiquent souvent à l’aide de vibrations", "true");
        Question_ok question24 = new RC_ok("Histoire", 1, "Quel mammifère marin est un cousin de l'éléphant ?", "Le lamantin");
        Question_ok question25 = new QCM_ok("Histoire", 2, "Quel est le plus grand mammifère marin de la planète ?", "Le requin", "La tortue", "La baleine", "La baleine");
        Question_ok question26 = new RC_ok("Histoire", 3, "Que mange le gorille ?", "Des plantes");
        Question_ok question27 = new VF_ok("Histoire", 3, "Le diable de Tasmanie vient d'Australie", "true");

        Question_ok question28 = new VF_ok("Français", 1, "Les chauves-souris sont les seuls mammifères qui peuvent voler", "true");
        Question_ok question29 = new QCM_ok("Français", 1, "Qui est le cheval de lucky luke ?", "Wolly wopper", "Dalton", "Jolly jumper", "Jolly jumper");
        Question_ok question30 = new VF_ok("Français", 1, "Les rongeurs communiquent souvent à l’aide de vibrations", "true");
        Question_ok question31 = new RC_ok("Français", 1, "Quel mammifère marin est un cousin de l'éléphant ?", "Le lamantin");
        Question_ok question32 = new QCM_ok("Français", 2, "Quel est le plus grand mammifère marin de la planète ?", "Le requin", "La tortue", "La baleine", "La baleine");
        Question_ok question33 = new RC_ok("Français", 3, "Que mange le gorille ?", "Des plantes");
        Question_ok question34 = new VF_ok("Français", 3, "Le diable de Tasmanie vient d'Australie", "true");

        Question_ok question35 = new VF_ok("Cinema", 1, "Les chauves-souris sont les seuls mammifères qui peuvent voler", "true");
        Question_ok question36 = new QCM_ok("Cinema", 1, "Qui est le cheval de lucky luke ?", "Wolly wopper", "Dalton", "Jolly jumper", "Jolly jumper");
        Question_ok question37 = new VF_ok("Cinema", 1, "Les rongeurs communiquent souvent à l’aide de vibrations", "true");
        Question_ok question38 = new RC_ok("Cinema", 1, "Quel mammifère marin est un cousin de l'éléphant ?", "Le lamantin");
        Question_ok question39 = new QCM_ok("Cinema", 2, "Quel est le plus grand mammifère marin de la planète ?", "Le requin", "La tortue", "La baleine", "La baleine");
        Question_ok question40 = new RC_ok("Cinema", 3, "Que mange le gorille ?", "Des plantes");
        Question_ok question41 = new VF_ok("Cinema", 3, "Le diable de Tasmanie vient d'Australie", "true");

        Question_ok question42 = new VF_ok("Géographie", 1, "Les chauves-souris sont les seuls mammifères qui peuvent voler", "true");
        Question_ok question43 = new QCM_ok("Géographie", 1, "Qui est le cheval de lucky luke ?", "Wolly wopper", "Dalton", "Jolly jumper", "Jolly jumper");
        Question_ok question44 = new VF_ok("Géographie", 1, "Les rongeurs communiquent souvent à l’aide de vibrations", "true");
        Question_ok question45 = new RC_ok("Géographie", 1, "Quel mammifère marin est un cousin de l'éléphant ?", "Le lamantin");
        Question_ok question46 = new QCM_ok("Géographie", 2, "Quel est le plus grand mammifère marin de la planète ?", "Le requin", "La tortue", "La baleine", "La baleine");
        Question_ok question47 = new RC_ok("Géographie", 3, "Que mange le gorille ?", "Des plantes");
        Question_ok question48 = new VF_ok("Géographie", 3, "Le diable de Tasmanie vient d'Australie", "true");

        Question_ok question49 = new VF_ok("Corps humain", 1, "Les chauves-souris sont les seuls mammifères qui peuvent voler", "true");
        Question_ok question50 = new QCM_ok("Corps humain", 1, "Qui est le cheval de lucky luke ?", "Wolly wopper", "Dalton", "Jolly jumper", "Jolly jumper");
        Question_ok question51 = new VF_ok("Corps humain", 1, "Les rongeurs communiquent souvent à l’aide de vibrations", "true");
        Question_ok question52 = new RC_ok("Corps humain", 1, "Quel mammifère marin est un cousin de l'éléphant ?", "Le lamantin");
        Question_ok question53 = new QCM_ok("Corps humain", 2, "Quel est le plus grand mammifère marin de la planète ?", "Le requin", "La tortue", "La baleine", "La baleine");
        Question_ok question54 = new RC_ok("Corps humain", 3, "Que mange le gorille ?", "Des plantes");
        Question_ok question55 = new VF_ok("Corps humain", 3, "Le diable de Tasmanie vient d'Australie", "true");

        Question_ok question56 = new VF_ok("Faits divers", 1, "Les chauves-souris sont les seuls mammifères qui peuvent voler", "true");
        Question_ok question57 = new QCM_ok("Faits divers", 1, "Qui est le cheval de lucky luke ?", "Wolly wopper", "Dalton", "Jolly jumper", "Jolly jumper");
        Question_ok question58 = new VF_ok("Faits divers", 1, "Les rongeurs communiquent souvent à l’aide de vibrations", "true");
        Question_ok question59 = new RC_ok("Faits divers", 1, "Quel mammifère marin est un cousin de l'éléphant ?", "Le lamantin");
        Question_ok question60 = new QCM_ok("Faits divers", 2, "Quel est le plus grand mammifère marin de la planète ?", "Le requin", "La tortue", "La baleine", "La baleine");
        Question_ok question61 = new RC_ok("Faits divers", 3, "Que mange le gorille ?", "Des plantes");
        Question_ok question62 = new VF_ok("Faits divers", 3, "Le diable de Tasmanie vient d'Australie", "true");

        Question_ok question63 = new VF_ok("Jeux vidéos", 1, "Les chauves-souris sont les seuls mammifères qui peuvent voler", "true");
        Question_ok question64 = new QCM_ok("Jeux vidéos", 1, "Qui est le cheval de lucky luke ?", "Wolly wopper", "Dalton", "Jolly jumper", "Jolly jumper");
        Question_ok question65 = new VF_ok("Jeux vidéos", 1, "Les rongeurs communiquent souvent à l’aide de vibrations", "true");
        Question_ok question66 = new RC_ok("Jeux vidéos", 1, "Quel mammifère marin est un cousin de l'éléphant ?", "Le lamantin");
        Question_ok question67 = new QCM_ok("Jeux vidéos", 2, "Quel est le plus grand mammifère marin de la planète ?", "Le requin", "La tortue", "La baleine", "La baleine");
        Question_ok question68 = new RC_ok("Jeux vidéos", 3, "Que mange le gorille ?", "Des plantes");
        Question_ok question69 = new VF_ok("Jeux vidéos", 3, "Le diable de Tasmanie vient d'Australie", "true");

        /*
        Question question3 = new VF("Sport", 1, "Mazeazeaze ?", "true");
        Question question4 = new RC("Voiture", 1, "BOF", "TEST");
         */

        questions = new Questions(themes);

        questions.ajout_question(question0);
        questions.ajout_question(question1);
        questions.ajout_question(question2);
        questions.ajout_question(question3);
        questions.ajout_question(question4);
        questions.ajout_question(question5);
        questions.ajout_question(question6);
        questions.ajout_question(question7);
        questions.ajout_question(question8);
        questions.ajout_question(question9);
        questions.ajout_question(question10);
        questions.ajout_question(question11);
        questions.ajout_question(question12);
        questions.ajout_question(question13);
        questions.ajout_question(question14);
        questions.ajout_question(question15);
        questions.ajout_question(question16);
        questions.ajout_question(question17);
        questions.ajout_question(question18);
        questions.ajout_question(question19);
        questions.ajout_question(question20);
        questions.ajout_question(question21);
        questions.ajout_question(question22);
        questions.ajout_question(question23);
        questions.ajout_question(question24);
        questions.ajout_question(question25);
        questions.ajout_question(question26);
        questions.ajout_question(question27);
        questions.ajout_question(question28);
        questions.ajout_question(question29);
        questions.ajout_question(question30);
        questions.ajout_question(question31);
        questions.ajout_question(question32);
        questions.ajout_question(question33);
        questions.ajout_question(question34);
        questions.ajout_question(question35);
        questions.ajout_question(question36);
        questions.ajout_question(question37);
        questions.ajout_question(question38);
        questions.ajout_question(question39);
        questions.ajout_question(question40);
        questions.ajout_question(question41);
        questions.ajout_question(question42);
        questions.ajout_question(question43);
        questions.ajout_question(question44);
        questions.ajout_question(question45);
        questions.ajout_question(question46);
        questions.ajout_question(question47);
        questions.ajout_question(question48);
        questions.ajout_question(question49);
        questions.ajout_question(question50);
        questions.ajout_question(question51);
        questions.ajout_question(question52);
        questions.ajout_question(question53);
        questions.ajout_question(question54);
        questions.ajout_question(question55);
        questions.ajout_question(question56);
        questions.ajout_question(question57);
        questions.ajout_question(question58);
        questions.ajout_question(question59);
        questions.ajout_question(question60);
        questions.ajout_question(question61);
        questions.ajout_question(question62);
        questions.ajout_question(question63);
        questions.ajout_question(question64);
        questions.ajout_question(question65);
        questions.ajout_question(question66);
        questions.ajout_question(question67);
        questions.ajout_question(question68);
        questions.ajout_question(question69);

        questions.setInd_question(new Random().nextInt(questions.getMat_questions().get(this.themes.getInd_theme()).size()));
        /*
        questions.ajout_question(question3);
        questions.ajout_question(question4);
         */
    }

    public void ajout_nouvelles_questions()
    {
        System.out.println("\nEst ce que le maitre du jeu veux implémenter une nouvelle question ?"
                + "\n(y, n)");
        Question_ok question;
        Scanner scanner = new Scanner(System.in);
        String str = scanner.next();

        if(str.equals("y"))
        {
            System.out.println("\nQuel type de question voulez vous implémenter ?"
                    + "\n(QCM, VF, RC)");
            Scanner scanner1 = new Scanner(System.in);
            String type = scanner1.next();

            if(type.equals("QCM"))
            {
                question = new QCM_ok();
                questions.ajout_question(question);
            }
            else if(type.equals("VF"))
            {
                question = new VF_ok();
                questions.ajout_question(question);
            }
            else if(type.equals("RC"))
            {
                question = new RC_ok();
            }
            else
            {
                System.out.println("\nCe type de question n'existe pas");
                ajout_nouvelles_questions();
            }
        }
    }

    public Joueur_ok deroulement_phase()
    {
        System.out.println("Bienvenue dans notre jeu !");
        initialisation_jeu();
        ajout_nouvelles_questions();
        System.out.println(joueurs);
        System.out.println(themes);
        System.out.println(questions);
        return null;
    }
}
