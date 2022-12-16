import java.util.Scanner;

public class First {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        while (number != 0){
            System.out.println(sumLastNums(number));
            number = scanner.nextInt();
        }
    }

    public static int sumLastNums(int n){
        int counter = 0, to_return = 0;
        while (n != 0 && counter < 3){
            to_return += n % 10;
            n /= 10;
            counter++;
        }
        return to_return;
    }
}
