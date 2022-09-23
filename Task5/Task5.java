import java.util.Scanner;

public class Task5 {
    public static void main(String[] args) {
        draw(input());
    }
    public static int input() {
        return new Scanner(System.in).nextInt();
    }
    public static void draw(int s) {
        for(int i = 0; i < (s + 1) / 2; i++) {
            int chars = s - (s - (i * 2 - 1)) / 2 - i * 2 + 1;
            drawGrids(chars);
            if (i != (s + 1) / 2 - 1) drawWhiteSpaces(i * 2 - 1);
            if (i == 0) chars--;
            if (chars != 1 || i == 0) drawStars(chars);
            if (i != (s + 1) / 2 - 1) System.out.print('\n');
        }
        for(int i = (s + 1) / 2; i >= 0; i--){
            int chars = s - (s - (i * 2 - 1)) / 2 - i * 2 + 1;
            if (i == 0) chars--;
            if (chars != 1 || i == 0) drawStars(chars);
            if (i == 0) chars++;
            if (i != (s + 1) / 2) drawWhiteSpaces(i * 2 - 1);
            drawGrids(chars);
            if (i != (s + 1) / 2) System.out.print('\n');
        }
    }
    public static void drawGrids(int n) {
        for(int z = 0; z < n; z++) System.out.print('#');
    }
    public static void drawWhiteSpaces(int n) {
        for(int z = 0; z < n; z++) System.out.print(' ');
    }
    public static void drawStars(int n) {
        for(int z = 0; z < n; z++) System.out.print('*');
    }
}
