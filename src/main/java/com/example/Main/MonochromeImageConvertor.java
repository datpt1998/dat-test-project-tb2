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

public class MonochromeImageConvertor {
    private static BufferedImage importImage (String path) throws IOException {
        FileInputStream inputStream = new FileInputStream(path);
        BufferedImage image = ImageIO.read(inputStream);
        return image;
    }

    private static BufferedImage monochromeConvert (BufferedImage image, int hue) {
        for(int height = 0; height < image.getHeight(); height++) {
            for (int width = 0; width < image.getWidth(); width++) {
                Color currentColor = new Color(image.getRGB(width, height));
                int red = currentColor.getRed();
                int green = currentColor.getGreen();
                int blue = currentColor.getBlue();
                float[] hsv = Color.RGBtoHSB(red, green, blue, null);
                float h = ((float)hue)/359;
                float s = hsv[1];
                float b = hsv[2];
                image.setRGB(width, height, Color.HSBtoRGB(h, s, b));
            }
        }
        return image;
    }

    private static boolean isHue(String hue) {
        try {
            int hueInt = Integer.parseInt(hue);
            if(hueInt > 359) {
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
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
            Scanner scanner = new Scanner(System.in);
            String hue = "";
            while (!isHue(hue)) {
                System.out.println("Enter hue value: ");
                hue = scanner.nextLine();
            }
            ImageIO.write(monochromeConvert(image, Integer.parseInt(hue)), "png", new File("imgmono/mono-"+ selectedFile.getName() + ".png"));
        }
        System.exit(0);
    }
}
