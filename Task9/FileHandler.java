import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileHandler {
    public static List<Integer> readFile(File input) {
        ArrayList<Integer> answer = new ArrayList<>();
        try (FileReader reader = new FileReader(input)){
            Scanner scanner = new Scanner(reader);
            while (scanner.hasNextInt()) {
                answer.add(scanner.nextInt());
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        return answer;
    }

    public static void writeFile(File output, List<Integer> array) {
        try (FileWriter fileWriter = new FileWriter(output)){
            for(var i: array) {
                fileWriter.write(i.toString());
                fileWriter.write('\n');
            }
            fileWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}