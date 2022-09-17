import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

public class Second {
    public static void main(String[] args){
        int month = input(), day = input(month);
        output(resolve(month, day));
    }
    public static int input(){
        Scanner scanner = new Scanner(System.in);
        while (true) {
            int month = scanner.nextInt();
            if (month <= 12)
                return month;
            System.out.println("ошибка ввода месяцев 12");
        }
    }
    public static int input(int month){
        Calendar calendar = Calendar.getInstance();
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat format1 = new SimpleDateFormat("MMMM");
        calendar.set(2022, month - 1, 1);
        while(true){
            int days = scanner.nextInt();
            if (days <= calendar.getActualMaximum(Calendar.DAY_OF_MONTH))
                return days;
            System.out.printf("ошибка ввода дней в %s %d дней", format1.format(calendar.getTime()), calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        }

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

