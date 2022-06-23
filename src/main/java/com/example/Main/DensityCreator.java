package com.example.Main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class DensityCreator {
  public static void main(String[] args) throws IOException {
    BufferedImage image = new BufferedImage(2560, 100, BufferedImage.TYPE_INT_ARGB);
    int b = 0;
    int period = 0;
    for(int i = 0; i < image.getWidth(); i++) {
      for(int j = 0; j < image.getHeight(); j++) {
        Color color = new Color(Color.HSBtoRGB(0, 0, ((float)b)/255));
        image.setRGB(i, j, color.getRGB());
      }
      period++;
      if(period == 10) {
        b++;
        period = 0;
      }
    }
    ImageIO.write(image, "png", new FileOutputStream("testStrip.png"));
  }
}
