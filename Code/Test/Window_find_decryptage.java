package Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

public class Window_find_decryptage extends JFrame{

    //button
    private JFileChooser select = new JFileChooser(System.getProperty("user.home") + "\\Desktop\\Cryptimage\\Images_cryptées");
    private String path_dossier_decrypt;

    public Window_find_decryptage(String path_dossier_decrypt)
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
        this.path_dossier_decrypt = path_dossier_decrypt;

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
                Window_decryptage_1 window_decryptage_1 = new Window_decryptage_1(path, path_dossier_decrypt);
                window_decryptage_1.setVisible(true);
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
