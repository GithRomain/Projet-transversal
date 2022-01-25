package Test;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Window_decryptage_2 extends JFrame {

    //button
    private JButton btn_home = new JButton("Home");
    private JButton btn_return = new JButton("Return");

    private String path;
    private String path_dossier_decrypt;

    public Window_decryptage_2(String path, String path_dossier_decrypt) throws IOException {
        //Nom de l'app
        super("CryptImage / Decryptage");
        //Ferme la console quand on ferme l'apli
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //Size fenetre
        this.setSize(1000, 750);
        //Position fenetre
        this.setLocationRelativeTo(null);
        //
        this.path = path;
        this.path_dossier_decrypt = path_dossier_decrypt;

        //image de base
        BufferedImage img_decrypte = ImageIO.read(new File(path_dossier_decrypt + "\\Cryptimage_decrypte.bmp"));
        JLabel pic_decrypte = new JLabel(new ImageIcon(img_decrypte));

        //Containter
        Container contentPane = this.getContentPane();
        JPanel boutonPane = new JPanel();
        JPanel imagePane = new JPanel();

        //Ajouter aux containers
        boutonPane.add(btn_return);
        boutonPane.add(btn_home);

        imagePane.add(pic_decrypte);

        //Stratégie de position

        //ajouter à l'interface
        contentPane.add(boutonPane, BorderLayout.NORTH);
        contentPane.add(imagePane, BorderLayout.CENTER);

        //actions
        btn_home.addActionListener(this::home);
        btn_return.addActionListener(this::retourner);
    }

    private void retourner(ActionEvent e) {
        this.dispose();
        try {
            Window_decryptage_1 window_decryptage_1 = new Window_decryptage_1(path, path_dossier_decrypt);
            window_decryptage_1.setVisible(true);
        }
        catch (IOException ioException)
        {
            ioException.printStackTrace();
        }
    }

    private void home(ActionEvent event)
    {
        this.dispose();
    }
}

