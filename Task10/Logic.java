import java.util.ArrayList;
import java.util.List;

public class Logic {
    public static void main(String[] args) {
        List<Candy> candies = new ArrayList<>();
        candies.add(new Candy(560, "пуща"));
        candies.add(new Candy(190, "буревестник"));
        candies.add(new Candy(380, "Золотой ключик"));
        for(var i: solve(candies, 1000).candies) System.out.println(i.name);
    }
    public static CandiesAndMoney solve(List<Candy> candies, int money) {
        sort(candies);
        List<Candy> boughtCandies = new ArrayList<>();
        money = buyMaxCandies(candies, boughtCandies, money);
        if (money == 0 || candies.size() == 0) return new CandiesAndMoney(boughtCandies, money);
        int temp, delta = 1;
        do {
            temp = money;
            candies.add(boughtCandies.remove(boughtCandies.size() - delta));
            Candy swap = candies.get(candies.size() - 1);
            candies.set(candies.size() - 1, candies.get(0));
            candies.set(0, swap);
            money += swap.price;

            for(int i = candies.size() - 1; i >= 0; i--) {
                if (candies.get(i).price <= money) {
                    money -= candies.get(i).price;
                    boughtCandies.add(candies.remove(i));
                    delta += 1;
                    break;
                }
            }

        }while(temp != money && money != 0 && boughtCandies.size() - delta >= 0);
        return new CandiesAndMoney(boughtCandies, money);
    }
    public static int buyMaxCandies(List<Candy> candies, List<Candy> boughtCandies, int money) {
        while(candies.size() > 0 && candies.get(0).price <= money) {
            money -= candies.get(0).price;
            boughtCandies.add(candies.remove(0));
        }
        return money;
    }
    public static void sort(List<Candy> candies) {
        int h = 1;

        while(h <= candies.size() / 3) h = h * 3 + 1;

        while(h > 0) {
            for(int i = h; i < candies.size(); i++) {
                Candy tmp = candies.get(i);
                int inner = i;

                while(inner > h - 1 && candies.get(inner - h).price > tmp.price) {
                    candies.set(inner, candies.get(inner - h));
                    inner -= h;
                }
                candies.set(inner, tmp);
            }
            h = (h - 1) / 3;
        }
    }
}
