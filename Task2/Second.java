import java.util.Calendar;
import java.util.Scanner;

public class Second {
    public static void main(String[] args){
        output(resolve(input(), input()));
    }
    public static int input(){
        return new Scanner(System.in).nextInt();
    }
    public static int getWeekDay(int month, int day){
        Calendar calendar = Calendar.getInstance();
        calendar.set(2022, month - 1, day - 1);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }
    public static boolean resolve(int month, int day){
        int dayOfWeek = getWeekDay(month, day);
        Holidays holidays = new Holidays();
        return (dayOfWeek == 6 || dayOfWeek == 7) && (month != 3 && day != 5) || holidays.holidaysArray[month - 1][day - 1];
    }
    public static void output(boolean holiday){
        if (holiday) System.out.println("Выходной!!!");
        else System.out.println("РАБОТАТЬ");
    }

}
