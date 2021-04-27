package com.example.Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class ImageToText {
    private static String toText(BufferedImage image) {
        StringBuilder text = new StringBuilder();
        for(int height = 0; height < image.getHeight(); height++) {
            for (int width = 0; width < image.getWidth(); width++) {
                Color currentColor = new Color(image.getRGB(width, height));
                int red = currentColor.getRed();
                int green = currentColor.getGreen();
                int blue = currentColor.getBlue();

                //10 area
//                if(red > 225 || green > 225 || blue > 225) {
//                    text.append(".");
//                } else if(red > 200 || green > 200 || blue > 200) {
//                    text.append("-");
//                } else if(red > 175 || green > 175 || blue > 175) {
//                    text.append("+");
//                } else if(red > 150 || green > 150 || blue > 150) {
//                    text.append("*");
//                } else if(red > 125 || green > 125 || blue > 125) {
//                    text.append("O");
//                } else if(red > 100 || green > 100 || blue > 100) {
//                    text.append("B");
//                } else if(red > 75 || green > 75 || blue > 75) {
//                    text.append("@");
//                } else if(red > 50 || green > 50 || blue > 50) {
//                    text.append("$");
//                } else if(red > 25 || green > 25 || blue > 25) {
//                    text.append("░");
//                } else {
//                    text.append("█");
//                }

                //5 area
                if(red > 200 || green > 200 || blue > 200) {
                    text.append("-");
                } else if(red > 150 || green > 150 || blue > 150) {
                    text.append("+");
                } else if(red > 100 || green > 100 || blue > 100) {
                    text.append("O");
                } else if(red > 50 || green > 50 || blue > 50) {
                    text.append("@");
                } else {
                    text.append("█");
                }
            }
            text.append("\n");
        }
        return text.toString();
    }

    private static BufferedImage importImage (String path) throws IOException {
        FileInputStream inputStream = new FileInputStream(path);
        BufferedImage image = ImageIO.read(inputStream);
        return image;
    }

    public static void main(String[] args) throws IOException {
//        URL url = ImageToText.class.getClassLoader().getResource("me2.jpg");
//        BufferedImage image = importImage(url.getPath());
//        System.out.println(toText(image));
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("image", "JPG", "PNG", "BMP");
        System.out.println(Arrays.asList(filter.getExtensions()));
        fileChooser.setFileFilter(filter);
        fileChooser.showOpenDialog(new JFrame());
        if(fileChooser.getSelectedFile() != null) {
            File selectedFile = fileChooser.getSelectedFile();
            BufferedImage image = importImage(selectedFile.getAbsolutePath());
            FileOutputStream outputStream = new FileOutputStream("imgtxt/text-" + selectedFile.getName()+".txt");
            String converted = toText(image);
            outputStream.write(converted.getBytes());
        }
        System.exit(0);
    }
}
