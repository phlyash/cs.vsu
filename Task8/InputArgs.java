import java.io.File;

public class InputArgs {
    File input, output;
    public InputArgs(String inputPath, String outputPath) {
        input = new File(inputPath);
        output = new File(outputPath);
    }
}