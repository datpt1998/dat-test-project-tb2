package com.example.Main;

public class TestFinal {
    public static void main(String[] args) {
        String target = "";
        try {
            target = "try";
//            return;
            throw new Exception("");
        } catch (Exception e) {
            target = "catch";
            return;
        } finally {
            System.out.println(target + ", you think you can kill me?");
        }
    }
}
