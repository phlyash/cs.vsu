import java.util.Scanner;

class Task6 {
    // #3
    // a(n - 1) * x * x
    // a(n-1) * x * x * (2 * n - 3) / (2 * n - 1)
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        double x = scanner.nextDouble(), e = scanner.nextDouble();
        int n = scanner.nextInt();
        double a = 2 * x;
        double sumN = 0, sumE = 0, sumEDiv10 = 0;
        int i = 1;
        while (Math.abs(a) > e / 10 || i < n) {
            if (i <= n) sumN += a;
            if (Math.abs(a) > e) sumE += a;
            if (Math.abs(a) > e / 10) sumEDiv10 += a;
            i++;
            a *= Math.pow(x, 2) * (2 * i - 3) / (2 * i - 1);
        }
        System.out.println(sumN);
        System.out.println(sumE);
        System.out.println(sumEDiv10);
        System.out.println(Math.log((1 + x) / (1 - x)));
    }
}
