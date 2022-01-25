package Test;

import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class App extends JFrame{
    //path des dossiers
    private String path_dossier_crypt;
    private String path_dossier_decrypt;
    public String path_texte;

    //button
    private JButton btn_cryptage = new JButton("Cryptage");
    private JButton btn_decryptage = new JButton("Deryptage");

    public App(String path_dossier_crypt, String path_dossier_decrypt, String path_texte)
    {
        //Nom de l'app
        super("CryptImage");
        //Ferme la console quand on ferme l'apli
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //Size fenetre
        this.setSize(300, 75);
        //Position fenetre
        this.setLocationRelativeTo(null);
        //paths
        this.path_dossier_crypt = path_dossier_crypt;
        this.path_dossier_decrypt = path_dossier_decrypt;
        this.path_texte = path_texte;

        //Containter
        Container contentPane = this.getContentPane();
        //Stratégie de position
        FlowLayout flowLayout = new FlowLayout();
        contentPane.setLayout(flowLayout);

        //ajouter à l'interface
        contentPane.add(btn_cryptage);
        contentPane.add(btn_decryptage);

        //actions
        btn_cryptage.addActionListener(this::ouverture_cryptage);
        btn_decryptage.addActionListener(this::ouverture_decryptage);
    }

    public App()
    {
        this.path_texte = System.getProperty("user.home") + "\\Desktop\\Cryptimage" + "\\clef.txt";
    }

    public String getPath_texte() {
        return path_texte;
    }

    private void ouverture_cryptage(ActionEvent e)
    {
        //ouvrir la fenetre de cryptage
        Window_find_cryptage window_cryptage = new Window_find_cryptage(path_dossier_crypt);
        //l'afficher
        window_cryptage.setVisible(true);
    }

    public String lit() throws IOException {
        App app = new App();
        File file = new File(app.path_texte);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String s = bufferedReader.readLine();
        if(file.delete()) {};
        fileReader.close();
        bufferedReader.close();
        return s;
    }

    private void ouverture_decryptage(ActionEvent event)
    {
        //ouvrir la fenetre de cryptage
        Window_find_decryptage window_decryptage = new Window_find_decryptage(path_dossier_decrypt);
        //l'afficher
        window_decryptage.setVisible(true);
    }

    public static void main(String[] args) throws UnsupportedLookAndFeelException, IOException {
        FlatLightLaf.setup();
        //Style
        UIManager.setLookAndFeel(new FlatLightLaf());

        //Creer le dossier de stockage
        String path_dossier = System.getProperty("user.home") + "\\Desktop\\Cryptimage";
        String path_texte = path_dossier + "\\clef.txt";
        String path_dossier_crypt = System.getProperty("user.home") + "\\Desktop\\Cryptimage\\Images_cryptées";
        String path_dossier_decrypt = System.getProperty("user.home") + "\\Desktop\\Cryptimage\\Images_décryptées";

        File dossier = new File(path_dossier);
        File dossier_crypt = new File(path_dossier_crypt);
        File dossier_decrypt = new File(path_dossier_decrypt);

        dossier.mkdir();
        dossier_crypt.mkdir();
        dossier_decrypt.mkdir();

        //lancer la fenetre
        App app = new App(path_dossier_crypt, path_dossier_decrypt, path_texte);
        app.setVisible(true);
    }
}
