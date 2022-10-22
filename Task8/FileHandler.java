import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileHandler {
    public static int[][] readFile(File input) {
        int[][] answer;
        try (FileReader fileReader = new FileReader(input)) {
            Scanner scanner = new Scanner(fileReader);
            int it = 0;
            String line = "1";
            while (scanner.hasNextLine()) {
                it++;
                line = scanner.nextLine();
            }
            answer = new int[it][line.split(" ").length];
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
            try (FileReader reader = new FileReader(input)){
                int it = 0;
                Scanner scanner = new Scanner(reader);
                while (scanner.hasNextLine()) {
                    String[] ln = scanner.nextLine().split(" ");
                    int it_inside = 0;
                    for (String i : ln) {
                        answer[it][it_inside] = Integer.parseInt(i);
                        it_inside++;
                    }
                    it++;
                }
            }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        return answer;
    }

    public static void writeFile(File output, int[][] array) {
        try (FileWriter fileWriter = new FileWriter(output)){
            for(int i = 0; i < array.length; i++) {
                for(int j = 0; j < array[i].length; j++) {
                    fileWriter.write(Integer.toString(array[i][j]));
                    fileWriter.write(' ');
                }
                fileWriter.write('\n');
            }
            fileWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}