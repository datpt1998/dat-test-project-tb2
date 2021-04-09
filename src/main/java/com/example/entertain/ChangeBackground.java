package com.example.entertain;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class ChangeBackground extends JFrame {
    //from MSDN article
    int SPI_SETDESKWALLPAPER = 20;
    int SPIF_UPDATEINIFILE = 0x01;
    int SPIF_SENDWININICHANGE = 0x02;

    private JButton browseButton;

    public ChangeBackground() throws HeadlessException {
        this.setLayout(new FlowLayout());
        browseButton = new JButton("Browse");
        this.add(browseButton);
        browseButton.addActionListener(e -> clickBrowse(e));
    }

    private void clickBrowse(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(this);
        File file = fileChooser.getSelectedFile();
        if(file != null) {
            SPI.INSTANCE.SystemParametersInfo(SPI_SETDESKWALLPAPER, 0 , file.getAbsolutePath(), SPIF_UPDATEINIFILE | SPIF_SENDWININICHANGE);
//            Changer changer = new Changer();
//            changer.change(file.getAbsolutePath());
        }
    }

    public static void main(String[] args) {
        ChangeBackground frame = new ChangeBackground();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(400, 200));
        frame.pack();
        frame.setVisible(true);
    }


}
