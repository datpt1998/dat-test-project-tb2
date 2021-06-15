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

public class ExchangeSpaceImageConvertor {
    private static BufferedImage importImage (String path) throws IOException {
        FileInputStream inputStream = new FileInputStream(path);
        BufferedImage image = ImageIO.read(inputStream);
        return image;
    }

    private static BufferedImage exchangeSpaceConvert (BufferedImage image) {
        for(int height = 0; height < image.getHeight(); height++) {
            for (int width = 0; width < image.getWidth(); width++) {
                Color currentColor = new Color(image.getRGB(width, height));
                int red = currentColor.getRed();
                int green = currentColor.getGreen();
                int blue = currentColor.getBlue();
                float[] hsv = Color.RGBtoHSB(red, green, blue, null);
                //b -> h, h -> s, s -> b
//                float h = hsv[2];
//                float s = hsv[0];
//                float b = hsv[1];
                //s -> h, h -> b, b -> s
                float h = hsv[1];
                float s = hsv[2];
                float b = hsv[0];
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
            ImageIO.write(exchangeSpaceConvert(image), "png", new File("imgex/ex-"+ selectedFile.getName() + ".png"));
        }
        System.exit(0);
    }
}
