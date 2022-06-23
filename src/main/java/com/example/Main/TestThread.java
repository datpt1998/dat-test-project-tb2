package com.example.Main;

public class TestThread {
  public static void main(String[] args) {
    Thread t = new Thread(() -> {
      for(int i = 0; i < 10000; i++) {
        System.out.println(String.format("Max int: %d, value: %d", Integer.MAX_VALUE, i));
      }
    });
    t.start();
    System.out.println("Just a placeholder");
  }
}
