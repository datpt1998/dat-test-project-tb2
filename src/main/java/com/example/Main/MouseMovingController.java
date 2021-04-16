package com.example.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Scanner;

public class MouseMovingController {
    public static void main(String[] args) throws AWTException {
        String moves = "wasdc";
        Scanner scanner = new Scanner(System.in);
        Robot robot = new Robot();
        while(true) {
            String s = scanner.nextLine();
            if(s.isEmpty()) continue;
            char c = s.charAt(0);
            char cInsensitive = Character.toLowerCase(c);
            if(moves.contains(cInsensitive + "")) {
                Point mouseLocation = MouseInfo.getPointerInfo().getLocation();
                switch (cInsensitive) {
                    case 'w':
                        robot.mouseMove(mouseLocation.x, mouseLocation.y - 10);
                        break;
                    case 'a':
                        robot.mouseMove(mouseLocation.x - 10, mouseLocation.y);
                        break;
                    case 's':
                        robot.mouseMove(mouseLocation.x, mouseLocation.y + 10);
                        break;
                    case 'd':
                        robot.mouseMove(mouseLocation.x + 10, mouseLocation.y);
                        break;
                    case 'c':
                        robot.mousePress(InputEvent.BUTTON1_MASK);
                        robot.mouseRelease(InputEvent.BUTTON1_MASK);
                        break;
                    default:
                        break;
                }
            } else {
                System.exit(0);
            }
        }
    }
}
