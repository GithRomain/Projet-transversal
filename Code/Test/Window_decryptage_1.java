package Test;

import Code.Annexe;
import Code.Decryptage;
import Householder.Fichier;
import Householder.HouseholderRGB;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Window_decryptage_1 extends JFrame {

    //button
    private JButton btn_return = new JButton("Return");
    private JButton btn_paste = new JButton("Paste key");
    private JButton btn_decrypt = new JButton("Decryptage");

    private String key = null;
    private String path;
    private String path_dossier_decrypt;

    public Window_decryptage_1(String path, String path_dossier_decrypt) throws IOException {
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
        BufferedImage img = ImageIO.read(new File(path));
        JLabel pic = new JLabel(new ImageIcon(img));

        //Containter
        Container contentPane = this.getContentPane();
        JPanel boutonPane = new JPanel();
        JPanel imagePane = new JPanel();

        //Ajouter aux containers
        boutonPane.add(btn_return);
        boutonPane.add(btn_paste);
        boutonPane.add(btn_decrypt);

        imagePane.add(pic);

        //Stratégie de position

        //ajouter à l'interface
        contentPane.add(boutonPane, BorderLayout.NORTH);
        contentPane.add(imagePane, BorderLayout.CENTER);

        //actions
        btn_decrypt.addActionListener(this::decryptage);
        btn_paste.addActionListener(this::get);
        btn_return.addActionListener(this::retourner);
    }

    private Clipboard getSystemClipboard()
    {
        Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
        return defaultToolkit.getSystemClipboard();
    }

    private void get(ActionEvent event)
    {
        try
        {
        Clipboard systemClipboard = getSystemClipboard();
        DataFlavor dataFlavor = DataFlavor.stringFlavor;

        if (systemClipboard.isDataFlavorAvailable(dataFlavor))
        {
            Object text = systemClipboard.getData(dataFlavor);
            key = (String) text;
        }
        else
        {
            key = null;
        }

        }
        catch (IOException e)
        {
            this.dispose();
            e.printStackTrace();
        }
        catch (UnsupportedFlavorException e)
        {
            this.dispose();
            e.printStackTrace();
        }
    }

    private void decryptage(ActionEvent e){
        try
        {
            if(key == null)
            {
                JOptionPane.showMessageDialog(this, "You forgot to paste the key");
            }
            else
            {
            App app = new App();
            String clef = app.lit();
            int[][] mat_test = Annexe.stringToMatrice(clef);

            //DECRYPTAGE
            HouseholderRGB householderRGBDecryptage = new HouseholderRGB(Fichier.read_img(path), mat_test); //cle du même format que celle récupéré
            BufferedImage imageDecryptee = householderRGBDecryptage.getImageDecrypteeRGB(); //on récupère
            Fichier.write_img(imageDecryptee,path_dossier_decrypt + "\\image_decrypte_hous.bmp"); //on enregistre


            //decryptage
            new Decryptage(path_dossier_decrypt + "\\image_decrypte_hous.bmp", path_dossier_decrypt, key);
            Annexe.stringToMatrice(clef);

            File close = new File(path_dossier_decrypt + "\\image_decrypte_hous.bmp");
            close.delete();
            File file = new File(app.path_texte);
            file.delete();

            Window_decryptage_2 window_decryptage_2 = new Window_decryptage_2(path, path_dossier_decrypt);
            window_decryptage_2.setVisible(true);
            this.dispose();
            }
        }
        catch (IOException ioException)
        {
            this.dispose();
        }
    }

    private void retourner(ActionEvent e)
    {
        this.dispose();
    }

}

