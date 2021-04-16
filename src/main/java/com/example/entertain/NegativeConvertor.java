package com.example.entertain;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class NegativeConvertor extends JFrame {
    JPanel show;
    JFrame theFrame;
    BufferedImage imgResult;

    public NegativeConvertor() throws IOException {
        theFrame = this;
        this.setLayout(new FlowLayout());
        show = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
            }
        };
        URL imgOrgUrl = NegativeConvertor.class.getClassLoader().getResource("me2.jpg");
        BufferedImage imgOrg = ImageIO.read(imgOrgUrl);
        imgResult = new BufferedImage(imgOrg.getWidth(), imgOrg.getHeight(), imgOrg.getType());
        for(int x = 0; x < imgOrg.getWidth(); x++) {
            for(int y = 0; y < imgOrg.getHeight(); y++) {
                int colorInt = imgOrg.getRGB(x, y);
                Color currentColor = new Color(colorInt);
                int red = Math.abs(currentColor.getRed() - 255);
                int green = Math.abs(currentColor.getGreen() - 255);
                int blue = Math.abs(currentColor.getBlue() - 255);
                imgResult.setRGB(x, y, new Color(red, green, blue).getRGB());
            }
        }
//        show = new JPanel() {
//            @Override
//            protected void paintComponent(Graphics g) {
//                super.paintComponent(g);
//                g.drawImage(imgResult, 0, 0, theFrame);
//            }
//        };
//        show.setSize(new Dimension(imgOrg.getWidth(), imgOrg.getHeight()));
//        show.paintComponents(imgOrg.getGraphics());
        ImageIO.write(imgResult, "jpg", new FileOutputStream("img/result.jpg"));
//        this.add(show);
    }

    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);
        g.drawImage(imgResult, 0, 0, this);
    }

    public static void main(String[] args) throws IOException {
        NegativeConvertor frame = new NegativeConvertor();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(400, 200));
        frame.pack();
        frame.setVisible(true);
    }
}
