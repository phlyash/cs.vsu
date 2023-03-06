import java.io.File;

public class ConsoleApplication {
    public static void main(String[] args) {
        InputArgs parsedArgs = parseArgs(args);
        CandiesAndMoney cAM = FileHandler.readFile(parsedArgs.input);
        FileHandler.writeFile(parsedArgs.output, Logic.solve(cAM.candies, cAM.money));
    }
    public static InputArgs parseArgs(String[] args) {
        try {
            if (args.length == 2) {
                if (args[0].contains("=")) {
                    if (args[0].split("=")[0].equals("--input-file"))
                        return new InputArgs(parsePath(args[0].split("=")[1]), parsePath(args[1].split("=")[1]));
                    return new InputArgs(parsePath(args[1].split("=")[1]), parsePath(args[0].split("=")[1]));
                }
                return new InputArgs(parsePath(args[0]), parsePath(args[1]));
            }
            if (args[0].equals("-i")) return new InputArgs(parsePath(args[1]), parsePath(args[3]));
            return new InputArgs(parsePath(args[3]), parsePath(args[1]));
        }
        catch (IndexOutOfBoundsException e) {
            System.err.println("wrong input");
            System.exit(-1);
            return new InputArgs("idk", "idk");
        }
    }
    public static String parsePath(String path) {
        if (!path.contains("./")) return path;

        int upperLevels = path.split("\\.\\./", -1).length - 1;
        File file = new File(System.getProperty("user.dir"));
        if (upperLevels != 0) {
            for(int i = 0; i < upperLevels; i++) file = file.getParentFile();
            return file.toString() + "/" + path.split("\\.\\./")[path.split("\\.\\./").length - 1];
        }

        return file + "/" + path.split("\\./")[1];
    }
}