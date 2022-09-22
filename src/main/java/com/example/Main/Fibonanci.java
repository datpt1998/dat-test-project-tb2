package com.example.Main;

public class Fibonanci {
  public static void main(String[] args) {
    System.out.println(fib(2));
  }

  private static int fib(int n) {
    if (n <= 1) {
      return n;
    }
//    return fib(0, 1, 2, n);
    return fib(n, 0, 1);
  }

//  private static int fib(int e1, int e2, int count, int n) {
//    if (count == n) {
//      return e1 + e2;
//    }
//    return fib(e2, e1 + e2, ++count, n);
//  }

  private static int fib(int n, int a, int b) {
    if (n == 2) {
      return a + b;
    }
    return fib(n - 1, b, a + b);
  }
}
