package com.example.Main;

import com.example.utils.html.constant.HtmlTagName;
import com.example.utils.html.entity.CssNode;
import com.example.utils.html.entity.HtmlNode;
import com.example.utils.html.helper.MyHtmlHelper;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class ImageToText {
    private static final int MAX_WIDTH = 500;

    private static String toText(BufferedImage image) {
        StringBuilder text = new StringBuilder();
        for(int height = 0; height < image.getHeight(); height++) {
            for (int width = 0; width < image.getWidth(); width++) {
                Color currentColor = new Color(image.getRGB(width, height));
                int red = currentColor.getRed();
                int green = currentColor.getGreen();
                int blue = currentColor.getBlue();

                float[] hsv = Color.RGBtoHSB(red, green, blue, null);
                float h = hsv[0];
                float s = hsv[1];
                float b = hsv[2];

                //use hsb space color
                if(b < 0.1) {
                    text.append("▓");
                } else if (b < 0.3){
                    if(s < 0.25) {
                        text.append(";");
                    } else if (s < 0.6) {
                        if (h <= 0.83 && h >= 0.5) {
                            text.append("&");
                        } else if (h < 0.5 && h >= 0.17) {
                            text.append("#");
                        } else {
                            text.append("*");
                        }
                    } else {
                        if (h <= 0.83 && h >= 0.5) {
                            text.append("@");
                        } else if (h < 0.5 && h >= 0.17) {
                            text.append("$");
                        } else {
                            text.append("%");
                        }
                    }
                } else if (b < 0.6) {
                    if(s < 0.25) {
                        text.append(":");
                    } else if (s < 0.6) {
                        if (h <= 0.83 && h >= 0.5) {
                            text.append("z");
                        } else if (h < 0.5 && h >= 0.17) {
                            text.append("c");
                        } else {
                            text.append("v");
                        }
                    } else {
                        if (h <= 0.83 && h >= 0.5) {
                            text.append("o");
                        } else if (h < 0.5 && h >= 0.17) {
                            text.append("a");
                        } else {
                            text.append("h");
                        }
                    }
                } else {
                    if(s < 0.25) {
                        text.append(".");
                    } else if (s < 0.6) {
                        if (h <= 0.83 && h >= 0.5) {
                            text.append("+");
                        } else if (h < 0.5 && h >= 0.17) {
                            text.append("-");
                        } else {
                            text.append("~");
                        }
                    } else {
                        if (h <= 0.83 && h >= 0.5) {
                            text.append("\\");
                        } else if (h < 0.5 && h >= 0.17) {
                            text.append("/");
                        } else {
                            text.append("|");
                        }
                    }
                }

                //use b value of hsb
//                if(b > 0.8) {
//                    text.append("-");
//                } else if(b > 0.6) {
//                    text.append("+");
//                } else if(b > 0.4) {
//                    text.append("O");
//                } else if(b > 0.2) {
//                    text.append("@");
//                } else {
//                    text.append("█");
//                }

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
//                if(red > 200 || green > 200 || blue > 200) {
//                    text.append("-");
//                } else if(red > 150 || green > 150 || blue > 150) {
//                    text.append("+");
//                } else if(red > 100 || green > 100 || blue > 100) {
//                    text.append("O");
//                } else if(red > 50 || green > 50 || blue > 50) {
//                    text.append("@");
//                } else {
//                    text.append("█");
//                }
            }
            text.append("\n");
        }
        return text.toString();
    }

    private static void exportTextToHtml(String content, String fileName, int fontSize) throws IOException {
        CssNode paragraphStyle = new CssNode(HtmlTagName.DIV)
                .addAttribute("font-size", fontSize+"px")
                .addAttribute("font-family", "monospace");
        HtmlNode styleNode = new HtmlNode(HtmlTagName.STYLE).addAttribute("type","text/css").addChildren(paragraphStyle);
        HtmlNode headNode = new HtmlNode(HtmlTagName.HEAD).addChildren(styleNode);
        HtmlNode paragraphNode = new HtmlNode(HtmlTagName.DIV);
        if(content.contains("\n")) {
            String[] lines = content.split("\n");
            for(String line : lines) {
                paragraphNode.addChildren(line);
                HtmlNode breakNode = new HtmlNode(HtmlTagName.BREAK_LINE);
                paragraphNode.addChildren(breakNode);
            }
        } else {
            paragraphNode.addChildren(content);
        }
        HtmlNode bodyNode = new HtmlNode(HtmlTagName.BODY).addChildren(paragraphNode);
        MyHtmlHelper htmlHelper = new MyHtmlHelper();
        htmlHelper.addChildToRoot(headNode);
        htmlHelper.addChildToRoot(bodyNode);
        FileOutputStream outputStream = new FileOutputStream("imgtxthtml/html-"+ fileName + ".html");
        outputStream.write(htmlHelper.getContent().getBytes());
    }

    private static BufferedImage importImage (String path) throws IOException {
        FileInputStream inputStream = new FileInputStream(path);
        BufferedImage image = ImageIO.read(inputStream);
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
            exportTextToHtml(converted, selectedFile.getName(), 1);
        }
        System.exit(0);
    }
}
