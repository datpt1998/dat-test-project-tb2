package com.example.Main;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

public class AsciiDensity {
  private static void exportFile(String content, String path) throws IOException {
    FileOutputStream outputStream = new FileOutputStream(path);
    outputStream.write(content.getBytes());
  }

  private static String getContent() {
    StringBuilder content = new StringBuilder();
    int min = 0;
    int max = 1000;
    char compare = '۩';
    for (int i = min; i < max; i++) {
      if (String.valueOf((char) i).matches("\\p{C}") || String.valueOf((char) i).trim().equals("")) {
        max++;
        continue;
      }
      for (int b = 0; b < 10; b++) {
        for (int a = 0; a < 200; a++) {
          content.append((char) i);
        }
        for (int a = 0; a < 200; a++) {
          content.append(compare);
        }
        content.append("\n");
      }
    }
//        for(int b = 0; b < 10; b++) {
//            for(int a = 0; a < 200; a++) {
//                content.append("░");
//            }
//            content.append("\n");
//        }
//        for(int b = 0; b < 10; b++) {
//            for(int a = 0; a < 200; a++) {
//                content.append("▒");
//            }
//            content.append("\n");
//        }
//        for(int b = 0; b < 10; b++) {
//            for(int a = 0; a < 200; a++) {
//                content.append("▓");
//            }
//            content.append("\n");
//        }
//        for(int b = 0; b < 10; b++) {
//            for(int a = 0; a < 200; a++) {
//                content.append("█");
//            }
//            content.append("\n");
//        }
    return content.toString();
  }

  private static Map<Integer, Integer> getColorText() {
    Map<Integer, Integer> colorMap = new HashMap<>();
    boolean isAccept = false;
    int current = 0;
    int max = (int) Math.pow(256, 3);
    for (int i = 0; i < max; i++) {
      if (String.valueOf((char) i).matches("\\p{C}")) {
        if (isAccept) {
          current = current + ((i - 1) - colorMap.get(current));
          colorMap.put(current, i - 1);
          isAccept = false;
          current++;
        }
        i++;
        max++;
      } else {
        if (!isAccept) {
          colorMap.put(current, i - 1);
          isAccept = true;
        }
      }
    }
    return colorMap;
  }

  public static void main(String[] args) throws IOException {
    exportFile(getContent(), "Ascii-density.txt");

//        exportFile(getColorText(), "Color-text.txt");

//        FileOutputStream outputStream = new FileOutputStream("Color-text.txt");
//        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
//        objectOutputStream.writeObject(getColorText());
  }
}
