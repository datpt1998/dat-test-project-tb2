package com.example.Main;

import java.io.FileOutputStream;
import java.io.IOException;

public class AsciiDensity {
    private static void exportFile(String content, String path) throws IOException {
        FileOutputStream outputStream = new FileOutputStream(path);
        outputStream.write(content.getBytes());
    }

    private static String getContent() {
        StringBuilder content = new StringBuilder();
        for(int i = 0; i < 256; i++) {
            for(int b = 0; b < 10; b++) {
                for(int a = 0; a < 200; a++) {
                    content.append((char)i);
                }
                content.append("\n");
            }
        }
        return content.toString();
    }

    public static void main(String[] args) throws IOException {
        exportFile(getContent(), "Ascii-density.txt");
    }
}
