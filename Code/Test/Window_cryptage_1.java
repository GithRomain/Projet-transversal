package Test;

import Code.Cryptage;
import Householder.*;
import Householder.HouseholderRGB;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Window_cryptage_1 extends JFrame {

    //button
    private JButton btn_return = new JButton("Return");
    private JButton btn_crypt = new JButton("Cryptage");

    private JProgressBar progress = new JProgressBar(0, 100);

    private String path;
    private String path_dossier_crypt;

    public Window_cryptage_1(String path, String path_dossier_crypt) throws IOException {
        //Nom de l'app
        super("CryptImage / Cryptage");
        //Ferme la console quand on ferme l'apli
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //Size fenetre
        this.setSize(1000, 750);
        //Position fenetre
        this.setLocationRelativeTo(null);
        //
        this.path = path;
        this.path_dossier_crypt = path_dossier_crypt;

        //
        progress.setValue(0);
        progress.setBackground(Color.lightGray);
        progress.setStringPainted(true);

        //image de base
        BufferedImage img = ImageIO.read(new File(path));
        JLabel pic = new JLabel(new ImageIcon(img));

        //Containter
        Container contentPane = this.getContentPane();
        JPanel boutonPane = new JPanel();
        JPanel imagePane = new JPanel();

        //Ajouter aux containers
        boutonPane.add(btn_return);
        boutonPane.add(btn_crypt);

        imagePane.add(pic);

        //Stratégie de position

        //ajouter à l'interface
        contentPane.add(boutonPane, BorderLayout.NORTH);
        contentPane.add(imagePane, BorderLayout.CENTER);
        contentPane.add(progress, BorderLayout.SOUTH);

        //actions
        btn_crypt.addActionListener(this::cryptage);
        btn_return.addActionListener(this::retourner);
    }

    public void setProgress(int val) {
        progress.setValue(val);
    }

    private void cryptage(ActionEvent e){
            try
            {
                //cryptage
                Cryptage cryptage = new Cryptage(path, path_dossier_crypt);

                BufferedImage image = Fichier.read_img(path_dossier_crypt + "\\image_crypte.bmp");

                HouseholderRGB householderRGBCryptage = new HouseholderRGB(image); //où image est un BufferedImage
                BufferedImage imageCryptee = householderRGBCryptage.getImageCrypteeRGB(); //on récupère l'image cryptée
                householderRGBCryptage.getCle(); //on récupère un tableau qui contient les 3 parties de clé de taille n
                Fichier.write_img(imageCryptee,path_dossier_crypt + "\\Cryptimage_crypte.bmp"); //on enregistre l'image dans un file

                String clef = householderRGBCryptage.cleToString();
                householderRGBCryptage.ecrit(clef);

                String key = cryptage.get_clef();

                File close = new File(path_dossier_crypt + "\\image_crypte.bmp");
                close.delete();

                Window_cryptage_2 window_cryptage_2 = new Window_cryptage_2(path, path_dossier_crypt, key);
                window_cryptage_2.setVisible(true);
                this.dispose();
            }
            catch (IOException exception)
            {
                this.dispose();
            }
    }

    private void retourner(ActionEvent e)
    {
        this.dispose();
    }

    }
