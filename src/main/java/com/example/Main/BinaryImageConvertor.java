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

public class BinaryImageConvertor {
  private static BufferedImage importImage (String path) throws IOException {
    FileInputStream inputStream = new FileInputStream(path);
    BufferedImage image = ImageIO.read(inputStream);
    return image;
  }

  private static BufferedImage binaryConvert (BufferedImage image) {
    for(int height = 0; height < image.getHeight(); height++) {
      for (int width = 0; width < image.getWidth(); width++) {
        Color currentColor = new Color(image.getRGB(width, height));
        int red = currentColor.getRed();
        int green = currentColor.getGreen();
        int blue = currentColor.getBlue();
        float[] hsv = Color.RGBtoHSB(red, green, blue, null);
        float h = hsv[0];
        float s = 0;
        float b = hsv[2] <= 0.66132022 ? 0 : (float) 0.9;
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
      ImageIO.write(binaryConvert(image), "png", new File("imgbin/bin-"+ selectedFile.getName() + ".png"));
    }
    System.exit(0);
  }
}
