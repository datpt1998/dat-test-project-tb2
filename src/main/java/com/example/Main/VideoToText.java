package com.example.Main;

import com.example.Service.ImageToTextService;
import org.bytedeco.ffmpeg.global.avcodec;
import org.bytedeco.ffmpeg.global.avutil;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.Buffer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class VideoToText {

    public static void main(String[] args) {
        try {
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("image", "MP4");
            fileChooser.setFileFilter(filter);
            fileChooser.showOpenDialog(new JFrame());
            if(fileChooser.getSelectedFile() != null) {
                File selectedFile = fileChooser.getSelectedFile();
                ImageToTextService imageToTextService = new ImageToTextService();
                imageToTextService.toTextVideo(selectedFile);
            }
        } catch (Throwable e) {
            e.printStackTrace();
            System.exit(-1);
        }
        System.exit(0);
    }
}
