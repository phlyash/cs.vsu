import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        tests();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите количетсво элементов");
        int n = scanner.nextInt();
        int[] input = new int[n];
        for(int i = 0; i < n; i++) input[i] = scanner.nextInt();

        printArray(input);
        for(int i: solution(input)) System.out.print(Integer.toString(i) + ' ');
    }
    public static int[] solution(int[] array) {
        int[] answer = new int[2];
        for(int i = 0; i < array.length; i++) {
            int target = array[i], fault = 0, length = 0;
            for(int j = i; j < array.length; j++) {
                if (array[j] == target && fault <= 2) {
                    length++;
                }
                else if (fault < 2) {
                    length++;
                    fault++;
                    if (length == 3 && fault == 2 && array[j] == array[j - 1]) {
                        target = array[j];
                        fault = 1;
                    }
                }
                else break;
            }
            if (length > answer[1]) {
                answer[0] = i;
                answer[1] = length;
            }
        }
        return answer;
    }
    public static void printArray(int[] array) {
        System.out.println();
        System.out.print('{');
        int it = 0;
        for(int i: array) {
            System.out.print(i);
            if (it != array.length - 1) System.out.print(", ");
            it++;
        }
        System.out.println('}');
    }
    public static void tests() {
        printArray(new int[] {0});
        for(int i: solution(new int[] {0})) System.out.print(Integer.toString(i) + ' ');

        printArray(new int[]  {4, 5, 3, 3, 7, 3, 3, 7, 6, 4, 6, 7, 7, 7, 7, 1});
        for(int i: solution(new int[]  {4, 5, 3, 3, 7, 3, 3, 7, 6, 4, 6, 7, 7, 7, 7, 1})) System.out.print(Integer.toString(i) + ' ');

        printArray(new int[] {1, 1, 1, 1, 1, 1});
        for(int i: solution(new int[] {1, 1, 1, 1, 1, 1})) System.out.print(Integer.toString(i) + ' ');

        printArray(new int[] {2, 1, 1, 1, 1, 3});
        for(int i: solution(new int[] {2, 1, 1, 1, 1, 3})) System.out.print(Integer.toString(i) + ' ');

        printArray(new int[] {1, 1, 1, 1, 2, 1, 1, 3, 1});
        for(int i: solution(new int[] {1, 1, 1, 1, 2, 1, 1, 3, 1})) System.out.print(Integer.toString(i) + ' ');

        printArray(new int[] {1, 1, 1, 1, 1, 3, 1, 1, 1 ,3});
        for(int i: solution(new int[] {1, 1, 1, 1, 1, 3, 1, 1, 1 ,3})) System.out.print(Integer.toString(i) + ' ');

        printArray(new int[] {3, 1, 1, 1, 1, 2, 5 ,6, 7, 8});
        for(int i: solution(new int[] {3, 1, 1, 1, 1, 2, 5 ,6, 7, 8})) System.out.print(Integer.toString(i) + ' ');

        printArray(new int[] {3, 3, 3, 1, 1, 1, 1, 1, 1, 5, 6});
        for(int i: solution(new int[] {3, 3, 3, 1, 1, 1, 1, 1, 1, 5, 6})) System.out.print(Integer.toString(i) + ' ');

        printArray(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9});
        for(int i: solution(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9})) System.out.print(Integer.toString(i) + ' ');

        printArray(new int[] {});
        for(int i: solution(new int[] {})) System.out.print(Integer.toString(i) + ' ');
        System.out.println();
    }
}
