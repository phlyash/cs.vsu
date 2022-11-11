import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileHandler {
    public static CandiesAndMoney readFile(File input) {
        List<Candy> answer = new ArrayList<>();
        int money = 0;
        String[] ln = {};

        try (FileReader reader = new FileReader(input)){
            Scanner scanner = new Scanner(reader);
            while (scanner.hasNextLine()) {
                ln = scanner.nextLine().split(" ");
                if (ln.length > 2) ln = parseLongName(ln);
                answer.add(new Candy(ln[0], Integer.parseInt(ln[1])));
            }
        }
        catch (ArrayIndexOutOfBoundsException e) {
            money = Integer.parseInt(ln[0]);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new CandiesAndMoney(answer, money);
    }

    public static void writeFile(File output, CandiesAndMoney cAM) {
        List<Candy> candies = cAM.candies;
        try (FileWriter fileWriter = new FileWriter(output)){
            for(int i = 0; i < candies.size(); i++) {
                fileWriter.write(candies.get(i).name);
                fileWriter.write(' ');
                fileWriter.write(Integer.toString(candies.get(i).price));
                fileWriter.write('\n');
            }
            fileWriter.write(Integer.toString(cAM.money));
            fileWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static String[] parseLongName(String[] line) {
        String[] answer = new String[2];
        answer[0] = "";
        int i = 0;
        while(i != line.length - 1) {
            answer[0] += line[i];
            if (i != line.length - 2) answer[0] += ' ';
            i++;
        }
        answer[1] = line[line.length - 1];
        return answer;
    }
}