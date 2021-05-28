package com.example.Service;

import org.bytedeco.ffmpeg.global.avcodec;
import org.bytedeco.ffmpeg.global.avutil;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DirectColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ImageToTextService {
    private final int MAX_WIDTH = 500;

    public ImageToTextService(){
    }

    public String toText(BufferedImage image) throws IOException {
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
                    text.append("W");
                } else if (b < 0.3){
                    if(s < 0.25) {
                        text.append("O");
                    } else if (s < 0.6) {
                        if (h <= 0.83 && h >= 0.5) {
                            text.append("V");
                        } else if (h < 0.5 && h >= 0.17) {
                            text.append("P");
                        } else {
                            text.append("X");
                        }
                    } else {
                        if (h <= 0.83 && h >= 0.5) {
                            text.append("R");
                        } else if (h < 0.5 && h >= 0.17) {
                            text.append("Q");
                        } else {
                            text.append("M");
                        }
                    }
                } else if (b < 0.6) {
                    if(s < 0.25) {
                        text.append("=");
                    } else if (s < 0.6) {
                        if (h <= 0.83 && h >= 0.5) {
                            text.append("1");
                        } else if (h < 0.5 && h >= 0.17) {
                            text.append("/");
                        } else {
                            text.append("7");
                        }
                    } else {
                        if (h <= 0.83 && h >= 0.5) {
                            text.append("@");
                        } else if (h < 0.5 && h >= 0.17) {
                            text.append("6");
                        } else {
                            text.append("0");
                        }
                    }
                } else {
                    if(s < 0.25) {
                        text.append("-");
                    } else if (s < 0.6) {
                        if (h <= 0.83 && h >= 0.5) {
                            text.append(".");
                        } else if (h < 0.5 && h >= 0.17) {
                            text.append("'");
                        } else {
                            text.append("*");
                        }
                    } else {
                        if (h <= 0.83 && h >= 0.5) {
                            text.append(":");
                        } else if (h < 0.5 && h >= 0.17) {
                            text.append("\"");
                        } else {
                            text.append("+");
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
//        exportFile(status.toString(), "window-wall-status.txt");
        return text.toString();
    }

    public BufferedImage toImageText(BufferedImage image, int fontSize) throws IOException {
        BufferedImage resizedImage = resize(image);
        int width = resizedImage.getWidth() * (fontSize == 1 ? fontSize : (int)((double)fontSize/1.5));
        int height = resizedImage.getHeight() * fontSize;
        int x = 0;
        int y = 0;
        String content = toText(resizedImage);
        BufferedImage textImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphic = textImage.createGraphics();
        graphic.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY));
        graphic.setColor(Color.WHITE);
        graphic.fillRect(x, y, textImage.getWidth(), textImage.getHeight());
        graphic.setColor(Color.BLACK);
        graphic.setFont(new Font("Monospaced", Font.PLAIN, fontSize));
        int yClone = y;
        for(String line : content.split("\n")) {
            graphic.drawString(line, x, yClone);
            yClone += fontSize;
        }

        WritableRaster raster = textImage.getRaster();
        WritableRaster newRaster = raster.createWritableChild(0, 0, width, height, 0, 0, new int[] {0, 1, 2});

        // create a ColorModel that represents the one of the ARGB except the alpha channel:
        DirectColorModel cm = (DirectColorModel)textImage.getColorModel();
        DirectColorModel newCM = new DirectColorModel(cm.getPixelSize(),
                cm.getRedMask(), cm.getGreenMask(), cm.getBlueMask());

        // now create the new buffer that is used ot write the image:
        BufferedImage rgbBuffer = new BufferedImage(newCM, newRaster, false, null);

        return rgbBuffer;
    }

    private BufferedImage resize(BufferedImage image) {
        if(image.getWidth() > MAX_WIDTH) {
            double scaleFactor = ((double) MAX_WIDTH) / ((double) image.getWidth());
            int newHeight = (int)(((double)image.getHeight()) * scaleFactor);
            BufferedImage scaledImage = new BufferedImage(MAX_WIDTH, newHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D scaledImageGraphic = scaledImage.createGraphics();
            scaledImageGraphic.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING,
                    RenderingHints.VALUE_RENDER_QUALITY));
            scaledImageGraphic.drawImage(image, 0, 0, MAX_WIDTH, newHeight, null);
            return scaledImage;
        }
        return image;
    }

    public void toTextVideo(File video) {
        int fontSize = 5;
        FFmpegFrameGrabber frameGrabber = new FFmpegFrameGrabber(video);
        Java2DFrameConverter frameConverter = new Java2DFrameConverter();
        FFmpegFrameRecorder frameRecorder = null;
        try {
            frameGrabber.start();
            frameRecorder = new FFmpegFrameRecorder("txtvid/txt-"+video.getName()+".mp4", frameGrabber.getImageWidth(), frameGrabber.getImageHeight(), frameGrabber.getAudioChannels());
            //set codec
            frameRecorder.setVideoCodec(avcodec.AV_CODEC_ID_H264);
            //set frame rate
            frameRecorder.setFrameRate(frameGrabber.getFrameRate()); // change later
            frameRecorder.setPixelFormat(avutil.AV_PIX_FMT_YUV420P);
            frameRecorder.setFormat("mp4");
            frameRecorder.start();
            Frame currentFrame;
            while ((currentFrame = frameGrabber.grabFrame()) != null){
                if(currentFrame.image == null) {
                    frameRecorder.record(currentFrame);
                } else {
                    Frame textFrame = frameConverter.getFrame(toImageText(frameConverter.getBufferedImage(currentFrame), fontSize));
                    frameRecorder.record(textFrame);
                }
            }
            frameGrabber.stop();
            frameRecorder.stop();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(frameGrabber != null) {
                    frameGrabber.release();
                }
                if(frameRecorder != null) {
                    frameRecorder.release();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
