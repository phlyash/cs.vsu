package ru.vsu.cs;

import java.util.Scanner;

class Program {
    // #3
    // a(n - 1) * x * x
    // a(n-1) * x * x * (2 * n - 3) / (2 * n - 1)
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        double x = scanner.nextDouble(), e = scanner.nextDouble();
        int n = scanner.nextInt();
        double a = 2 * x;
        double sumN = 0, sumE = 0, sumEDiv10 = 0;
        if (e / 10 < 2 * Math.pow(x, n) / n) {
            int i = 1;
            while (a > e / 10) {
                if (i <= n) sumN += a;
                if (a > e) sumE += a;
                sumEDiv10 += a;
                i++;
                a *= Math.pow(x, 2) * (2 * i - 3) / (2 * i - 1);
            }
        }
        else {
            for(int i = 1; i < n + 1; i++) {
                if (a > e) sumE += a;
                if (a > e / 10) sumEDiv10 += a;
                sumN += a;
                a *= Math.pow(x, 2) * (2 * i - 1) / (2 * i + 1);
            }
        }
        System.out.println(sumN);
        System.out.println(sumE);
        System.out.println(sumEDiv10);
        System.out.println(Math.log((1 + x) / (1 - x)));
    }
}
