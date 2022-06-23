package com.example.entertain;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

public class BinaryConvertor extends JFrame {
  private JButton btBrowse;
  private JSlider slDarkPercent;
  private JPanel pnResult;
  private JButton btExport;

  private BufferedImage image;
  private String name;

  private final int MAX_WIDTH = 700;

  public BinaryConvertor() throws HeadlessException {
    image = null;
    name = "default";
    //UI
    this.setLayout(new FlowLayout(FlowLayout.LEFT));
    btBrowse = new JButton("Browse");
    this.add(btBrowse);
    slDarkPercent = new JSlider(JSlider.HORIZONTAL, 0, 1000, 500);
    this.add(slDarkPercent);
    btExport = new JButton("Export");
    this.add(btExport);
    pnResult = new JPanel();
    this.add(pnResult);

    //
    slDarkPercent.setMajorTickSpacing(10);
    slDarkPercent.setMinorTickSpacing(1);
    slDarkPercent.setPaintTicks(true);
    slDarkPercent.setPaintLabels(true);
    slDarkPercent.setPaintTrack(true);

    pnResult.setBackground(Color.WHITE);

    //
    btBrowse.addActionListener(e -> {
      JFileChooser fileChooser = new JFileChooser();
      FileNameExtensionFilter filter = new FileNameExtensionFilter("image", "JPG", "PNG", "BMP");
      System.out.println(Arrays.asList(filter.getExtensions()));
      fileChooser.setFileFilter(filter);
      fileChooser.showOpenDialog(new JFrame());
      if(fileChooser.getSelectedFile() != null) {
        try {
          File selectedFile = fileChooser.getSelectedFile();
          name = selectedFile.getName();
          image = importImage(selectedFile.getAbsolutePath());
          BufferedImage scaledImage = scaledImage(image);
          pnResult.setSize(scaledImage.getWidth(), scaledImage.getHeight());
          System.out.println(slDarkPercent.getValue());
          pnResult
              .getGraphics()
              .drawImage(binaryConvert(scaledImage,
                  (float) slDarkPercent.getValue()/(float) slDarkPercent.getMaximum()), 0, 0, pnResult);
        } catch (IOException ioException) {
          ioException.printStackTrace();
        }
      }
    });

    slDarkPercent.addChangeListener(e -> {
      if(image == null) {
        return;
      }
      System.out.println(slDarkPercent.getValue());
      BufferedImage scaledImage = scaledImage(image);
      pnResult
          .getGraphics()
          .drawImage(binaryConvert(scaledImage,
              (float) slDarkPercent.getValue()/(float) slDarkPercent.getMaximum()), 0, 0, pnResult);
    });

    btExport.addActionListener(e -> {
      if(image == null) {
        return;
      }
      try {
        ImageIO
            .write(binaryConvert(image,
                (float) slDarkPercent.getValue()/(float) slDarkPercent.getMaximum()),
                "png", new File("imgbin/bin-"+ name + ".png"));
      } catch (IOException ioException) {
        ioException.printStackTrace();
      }
    });
  }

  private BufferedImage importImage (String path) throws IOException {
    FileInputStream inputStream = new FileInputStream(path);
    BufferedImage image = ImageIO.read(inputStream);
    return image;
  }

  private BufferedImage scaledImage (BufferedImage image) {
    if(image.getWidth() > MAX_WIDTH) {
      double scaleFactor = ((double) MAX_WIDTH) / ((double) image.getWidth());
      int newHeight = (int)(((double)image.getHeight()) * scaleFactor);
      BufferedImage scaledImage = new BufferedImage(MAX_WIDTH, newHeight, BufferedImage.TYPE_INT_ARGB);
      Graphics2D scaledImageGraphic = scaledImage.createGraphics();
      scaledImageGraphic.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING,
          RenderingHints.VALUE_RENDER_QUALITY));
      scaledImageGraphic.drawImage(image, 0, 0, MAX_WIDTH, newHeight, null);
      return scaledImage;
    }
    return image;
  }

  private static BufferedImage binaryConvert (BufferedImage image, float percent) {
    for(int height = 0; height < image.getHeight(); height++) {
      for (int width = 0; width < image.getWidth(); width++) {
        Color currentColor = new Color(image.getRGB(width, height));
        int red = currentColor.getRed();
        int green = currentColor.getGreen();
        int blue = currentColor.getBlue();
        float[] hsv = Color.RGBtoHSB(red, green, blue, null);
        float h = hsv[0];
        float s = 0;
        float b = hsv[2] <= percent ? 0 : (float) 0.9;
        image.setRGB(width, height, Color.HSBtoRGB(h, s, b));
      }
    }
    return image;
  }

  public static void main(String[] args) {
    BinaryConvertor frame = new BinaryConvertor();
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.setPreferredSize(new Dimension(1000, 1000));
    frame.pack();
    frame.setVisible(true);
  }
}
