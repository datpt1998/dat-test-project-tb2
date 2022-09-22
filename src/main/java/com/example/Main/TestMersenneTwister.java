package com.example.Main;

import org.apache.commons.math3.random.MersenneTwister;
import org.apache.commons.math3.random.Well512a;

public class TestMersenneTwister {
  static MersenneTwister mt = new MersenneTwister();
  static Well512a w512 = new Well512a();


  public static void main(String[] args) {
    System.out.println(mt.nextInt(15));
    System.out.println(mt.nextInt(15));

    System.out.println(w512.nextInt());
    System.out.println(w512.nextInt());
  }
}
