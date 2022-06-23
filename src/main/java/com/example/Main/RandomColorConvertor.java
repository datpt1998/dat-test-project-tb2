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
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class RandomColorConvertor {
  private static BufferedImage importImage (String path) throws IOException {
    FileInputStream inputStream = new FileInputStream(path);
    BufferedImage image = ImageIO.read(inputStream);
    return image;
  }

  private static BufferedImage randomConvert(BufferedImage image){
    Random random = new Random();
    Map<Color, Color> colorMap = new HashMap<>();
    for(int height = 0; height < image.getHeight(); height++) {
      for (int width = 0; width < image.getWidth(); width++) {
        Color currentColor = new Color(image.getRGB(width, height));
        Color alterColor = colorMap.get(currentColor);
        if(alterColor == null) {
          alterColor = new Color(random.nextInt(256),random.nextInt(256),random.nextInt(256));
          colorMap.put(currentColor, alterColor);
        }
        image.setRGB(width, height, alterColor.getRGB());
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
      ImageIO.write(randomConvert(image), "png", new File("imgrandom/random-"+ selectedFile.getName() + ".png"));
    }
    System.exit(0);
  }
}
