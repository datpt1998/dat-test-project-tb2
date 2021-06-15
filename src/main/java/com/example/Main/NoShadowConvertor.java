package com.example.Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class NoShadowConvertor {
    private static BufferedImage importImage (String path) throws IOException {
        FileInputStream inputStream = new FileInputStream(path);
        BufferedImage image = ImageIO.read(inputStream);
        return image;
    }

    private static BufferedImage noShadowConvert (BufferedImage image) {
        for(int height = 0; height < image.getHeight(); height++) {
            for (int width = 0; width < image.getWidth(); width++) {
                Color currentColor = new Color(image.getRGB(width, height));
                int red = currentColor.getRed();
                int green = currentColor.getGreen();
                int blue = currentColor.getBlue();
                float[] hsv = Color.RGBtoHSB(red, green, blue, null);
                float h = hsv[0];
                float s = hsv[1] == 0 ? 0 : 1;
                float b = hsv[2] == 0 ? 0 : 1;
                image.setRGB(width, height, Color.HSBtoRGB(h, s, b));
            }
        }
        return image;
    }

    public static void main(String[] args) throws IOException {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("image", "JPG", "PNG", "BMP");
        System.out.println(Arrays.asList(filter.getExtensions()));
        fileChooser.setFileFilter(filter);
        fileChooser.showOpenDialog(new JFrame());
        if(fileChooser.getSelectedFile() != null) {
            File selectedFile = fileChooser.getSelectedFile();
            BufferedImage image = importImage(selectedFile.getAbsolutePath());
            ImageIO.write(noShadowConvert(image), "png", new File("imgnoshadow/noshadow-"+ selectedFile.getName() + ".png"));
        }
        System.exit(0);
    }
}
