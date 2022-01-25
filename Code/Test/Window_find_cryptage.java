package Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

public class Window_find_cryptage extends JFrame{

    //button
    private JFileChooser select = new JFileChooser(System.getProperty("user.home") + "\\Desktop");
    private String path_dossier_crypt;

    public Window_find_cryptage(String path_dossier_crypt)
    {
        //Nom de l'app
        super("CryptImage / Search_file");
        //Ferme la console quand on ferme l'apli
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //Size fenetre
        this.setSize(600, 400);
        //Position fenetre
        this.setLocationRelativeTo(null);
        //
        this.path_dossier_crypt = path_dossier_crypt;

        //Containter
        Container contentPane = this.getContentPane();
        //Stratégie de position
        contentPane.setLayout(new FlowLayout());

        //ajouter à l'interface
        contentPane.add(select);

        //actions
        select.addActionListener(this::selection);
    }

    private void selection(ActionEvent e){
        File file = select.getSelectedFile();
        if(file != null)
        {
            //path de l'image de base
            String path = file.getPath();
            try
            {
                Window_cryptage_1 window_cryptage_1 = new Window_cryptage_1(path, path_dossier_crypt);
                window_cryptage_1.setVisible(true);
                this.dispose();
            }
            catch (IOException ioException)
            {
                this.dispose();
            }
        }
        else
        {
            this.dispose();
        }
    }
}
