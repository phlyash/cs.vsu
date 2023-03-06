import java.util.Scanner;

class Task6 {

    // #3
    // a(n - 1) * x * x
    // a(n-1) * x * x * (2 * n - 3) / (2 * n - 1)

    public static void main(String[] args){
        double x, e;
        int n;

        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите x - ");
        x = scanner.nextDouble();
        System.out.print("Введите e - ");
        e = scanner.nextDouble();
        System.out.print("Введите n - ");
        n = scanner.nextInt();

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

        System.out.printf("Сумма первых n чисел - %.15f\n", sumN);
        System.out.printf("Сумма чисел меньших e по абс. - %.15f\n", sumE);
        System.out.printf("Сумма чисел меньших e/10 по абс. - %.15f\n", sumEDiv10);
        System.out.printf("Значение при помощи функции в Math - %.15f\n", Math.log((1 + x) / (1 - x)));
    }
}
