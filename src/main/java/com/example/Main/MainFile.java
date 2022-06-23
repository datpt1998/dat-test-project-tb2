package com.example.Main;

import java.io.BufferedReader;
import java.io.FileReader;

public class MainFile {
    public static void main(String[] args) {
        try {
//            File file = new File("myfile");
//            Image image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
//            System.out.println(ImageIO.write((BufferedImage)image, ".png", file));
////            Files.delete(file.toPath());
//            Media video = new Media(new File("sample_video.mp4").toURI().toString());
//            MediaPlayer mediaPlayer = new MediaPlayer(video);
//            System.out.println(mediaPlayer.getTotalDuration());

            BufferedReader reader = new BufferedReader(new FileReader("test.txt"));
            String s = reader.readLine();
            while (s != null) {
                System.out.println(s);
                s = reader.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
