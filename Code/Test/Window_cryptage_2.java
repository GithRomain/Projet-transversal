package Test;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Window_cryptage_2 extends JFrame {

    //button
    private JButton btn_clip = new JButton("Copy key");
    private JButton btn_home = new JButton("Home");
    private JButton btn_return = new JButton("Return");

    private String path;
    private String path_dossier_crypt;
    private String key;

    public Window_cryptage_2(String path, String path_dossier_crypt, String key) throws IOException {
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
        this.key = key;

        //image de base
        BufferedImage img_crypte = ImageIO.read(new File(path_dossier_crypt + "\\Cryptimage_crypte.bmp"));
        JLabel pic_crypte = new JLabel(new ImageIcon(img_crypte));

        //Containter
        Container contentPane = this.getContentPane();
        JPanel boutonPane = new JPanel();
        JPanel imagePane = new JPanel();

        //Ajouter aux containers
        boutonPane.add(btn_return);
        boutonPane.add(btn_home);
        boutonPane.add(btn_clip);

        imagePane.add(pic_crypte);

        //Stratégie de position

        //ajouter à l'interface
        contentPane.add(boutonPane, BorderLayout.NORTH);
        contentPane.add(imagePane, BorderLayout.CENTER);

        //actions
        btn_clip.addActionListener(this::copy);
        btn_home.addActionListener(this::home);
        btn_return.addActionListener(this::retourner);
    }

    private void retourner(ActionEvent e) {
        this.dispose();
        try {
            Window_cryptage_1 window_cryptage_1 = new Window_cryptage_1(path, path_dossier_crypt);
            window_cryptage_1.setVisible(true);
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

    private Clipboard getSystemClipboard()
    {
        Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
        return defaultToolkit.getSystemClipboard();
    }

    public void copy(ActionEvent e)
    {
        Clipboard clipboard = getSystemClipboard();
        clipboard.setContents(new StringSelection(key), null);
    }

}
