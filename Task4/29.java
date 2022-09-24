import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println(findNumber(input()));

    }
    public static int input() {
        return new Scanner(System.in).nextInt();
    }
    public static boolean checkNumberCorrect(int number) {
        int last = number % 10, direction = 0;
        number /= 10;

        while (number != 0) {
            int new_last = number % 10;
            number /= 10;
            if (last >= new_last || direction == 0 || direction == 1) {
                if (direction == 1 && last < new_last) return false;
                if (direction == 0 && last > new_last) direction = 1;
            }
            if (last <= new_last || direction == 0 || direction == -1) {
                if (direction == -1 && last > new_last) return false;
                if (direction == 0 && last < new_last) direction = -1;
            }
            last = new_last;
        }

        return true;
    }
    public static int findNumber(int index) {
        int it = 0, num = 0;
        for(int i = 0;;i++){
            if (checkNumberCorrect(i)) it++;
            if (it > index) return i;
        }
    }
}
