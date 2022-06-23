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
import java.io.ObjectInputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ImageToText {
    private static final int MAX_WIDTH = 1000;
    private static final String[] EXCEPT_IMAGE = {"teststrip.jpg", "testStrip.png"};

    private static void exportTextImage(BufferedImage image, String fileName) throws IOException, ClassNotFoundException {
        int fontSize = 7;
        // i don't know why
        int width = image.getWidth() * (fontSize == 1 ? fontSize : (int)((double)fontSize/1.5));
        int height = image.getHeight() * fontSize;
        int x = 0;
        int y = 0;
        BufferedImage exportImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphic = exportImage.createGraphics();
        graphic.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY));
        graphic.setColor(Color.WHITE);
//        graphic.setColor(Color.PINK);
        graphic.fillRect(x, y, exportImage.getWidth(), exportImage.getHeight());
        graphic.setColor(Color.BLACK);
        graphic.setFont(new Font("Courier New", Font.PLAIN, fontSize));
        int yClone = y;
        System.out.println(toText(image).split("\n").length);
        for(String line : toText(image).split("\n")) {
            System.out.println(line.length());
            graphic.drawString(line, x, yClone);
            yClone += fontSize;
        }
//        graphic.drawImage(image, 0, 0, null);
        ImageIO.write(exportImage, "png", new File("timg/timg-"+fileName+".png"));
    }

    public static String toText(BufferedImage image) throws IOException, ClassNotFoundException {
//        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("Color-text.txt"));
//        @SuppressWarnings("unchecked") Map<Integer, Integer> colorMap = (HashMap<Integer, Integer>)objectInputStream.readObject();
//        Set<Integer> rightKeys = colorMap.keySet().stream().sorted(Integer::compareTo).collect(Collectors.toSet());
//        Map<Integer, Integer> rightColorMap = new HashMap<>();
//        rightKeys.forEach(rightKey -> rightColorMap.put(rightKey, colorMap.get(rightKey)));
        StringBuilder text = new StringBuilder();
        StringBuilder status = new StringBuilder();
        for(int height = 0; height < image.getHeight(); height++) {
            for (int width = 0; width < image.getWidth(); width++) {
                Color currentColor = new Color(image.getRGB(width, height));
                int red = currentColor.getRed();
                int green = currentColor.getGreen();
                int blue = currentColor.getBlue();

//                status = status.append(h+"/"+s+"/"+b+" ");

                //use base 256
//                int currentValue = (int) (blue + green * 256 + red * Math.pow(256 , 2));
//                int min = 0;
//                for(Integer key : rightColorMap.keySet()) {
//                    if(currentValue >= key) {
//                        min = key;
//                    } else break;
//                }
//                currentValue = (currentValue - min) + rightColorMap.get(min);
//                text.append((char) currentValue);

                float[] hsv = Color.RGBtoHSB(red, green, blue, null);
                float h = hsv[0];
                float s = hsv[1];
                float b = hsv[2];


                //use b of hsb n area
//                String candidate = "█▓▒Ϣ▄۩░®WɸʩԨQŷMNHgK@BGOTC6{ɟ}?I|*z;:!,'`.-ː";

                String candidate = "Ϣ۩ɸɮ®ŴWɸʩԨQŷMNHgK@BGOTC6{ɟ}o?I|>*z;:!,'`.-ː";
                System.out.println(candidate.length());
                text.append(candidate.charAt((int)(b*(candidate.length() - 1))));

                //use hsb space color
//                if(b < 0.1) {
//                    text.append("W");
//                } else if (b < 0.3){
//                    if(s < 0.25) {
//                        text.append("O");
//                    } else if (s < 0.6) {
//                        if (h <= 0.83 && h >= 0.5) {
//                            text.append("V");
//                        } else if (h < 0.5 && h >= 0.17) {
//                            text.append("P");
//                        } else {
//                            text.append("X");
//                        }
//                    } else {
//                        if (h <= 0.83 && h >= 0.5) {
//                            text.append("R");
//                        } else if (h < 0.5 && h >= 0.17) {
//                            text.append("Q");
//                        } else {
//                            text.append("M");
//                        }
//                    }
//                } else if (b < 0.6) {
//                    if(s < 0.25) {
//                        text.append("=");
//                    } else if (s < 0.6) {
//                        if (h <= 0.83 && h >= 0.5) {
//                            text.append("1");
//                        } else if (h < 0.5 && h >= 0.17) {
//                            text.append("/");
//                        } else {
//                            text.append("7");
//                        }
//                    } else {
//                        if (h <= 0.83 && h >= 0.5) {
//                            text.append("@");
//                        } else if (h < 0.5 && h >= 0.17) {
//                            text.append("6");
//                        } else {
//                            text.append("0");
//                        }
//                    }
//                } else {
//                    if(s < 0.25) {
//                        text.append("-");
//                    } else if (s < 0.6) {
//                        if (h <= 0.83 && h >= 0.5) {
//                            text.append(".");
//                        } else if (h < 0.5 && h >= 0.17) {
//                            text.append("'");
//                        } else {
//                            text.append("*");
//                        }
//                    } else {
//                        if (h <= 0.83 && h >= 0.5) {
//                            text.append(":");
//                        } else if (h < 0.5 && h >= 0.17) {
//                            text.append("\"");
//                        } else {
//                            text.append("+");
//                        }
//                    }
//                }

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
//            status.append("\n");
        }
//        exportFile(status.toString(), "window-wall-status.txt");
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
        exportFile(htmlHelper.getContent(), "imgtxthtml/html-"+ fileName + ".html");
    }

    private static boolean testPath(String path) {
        for(String s : EXCEPT_IMAGE) {
            if(path.endsWith(s)) {
                return true;
            }
        }
        return false;
    }

    private static BufferedImage importImage (String path) throws IOException {
        FileInputStream inputStream = new FileInputStream(path);
        BufferedImage image = ImageIO.read(inputStream);
        if(image.getWidth() > MAX_WIDTH && !testPath(path)) {
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

    private static void exportFile(String content, String path) throws IOException {
        FileOutputStream outputStream = new FileOutputStream(path);
        outputStream.write(content.getBytes());
    }

    private static void exportTextToSvg(String content, String fileName, int fontSize) throws IOException {
        int x = 10;
        int y = 10;

        CssNode paragraphStyle = new CssNode("text")
                .addAttribute("font-size", fontSize+"px")
                .addAttribute("font-family", "monospace");
        HtmlNode style = new HtmlNode(HtmlTagName.STYLE).addChildren(paragraphStyle);
        HtmlNode svg = new HtmlNode("svg")
                .addAttribute("height", "5000")
                .addAttribute("width", "5000")
                .addAttribute("xmlns", "http://www.w3.org/2000/svg")
                .addAttribute("version", "1.1");
        HtmlNode text = new HtmlNode("text")
                .addAttribute("x", x+"")
                .addAttribute("y", y+"");
        if(content.contains("\n")) {
            int yClone = y;
            String[] lines = content.split("\n");
            for(String line : lines) {
                HtmlNode tspan = new HtmlNode("tspan")
                        .addAttribute("x", x+"")
                        .addAttribute("y", yClone+"")
                        .addChildren(line);
                text.addChildren(tspan);
                yClone += (fontSize+1);
            }
        } else {
            text.addChildren(content);
        }
        svg.addChildren(style).addChildren(text);
        exportFile(svg.toString(), "imgsvg/svg-"+fileName+".svg");
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
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
            String converted = toText(image);
            exportFile(converted, "imgtxt/text-" + selectedFile.getName()+".txt");
            exportTextToHtml(converted, selectedFile.getName(), 1);
            exportTextToSvg(converted, selectedFile.getName(), 2);
            exportTextImage(image, selectedFile.getName());
        }
        System.exit(0);
    }
}
