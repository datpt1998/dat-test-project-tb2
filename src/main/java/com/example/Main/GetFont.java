package com.example.Main;

import java.awt.*;

public class GetFont {
    public static void main(String[] args) {
        GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        for(Font font : graphicsEnvironment.getAllFonts()) {
            System.out.println(font.getFontName());
        }
    }
}
