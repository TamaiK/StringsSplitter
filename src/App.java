import java.util.List;

import myclass.*;

public class App {

    // 定数

    private static final String MESSAGE_FORMAT_FOR_SEPARATION = "-----課題%d---------------";

    public static void main(String[] args) {
        
        String inpuString;
        List<String> lines;
        
        dispTask(1);
        inpuString = "１行目。\n２行目。\n３行目。\n４行目。\n\n５行目\n";
        lines = splitWithLineBreakCode(inpuString);
        for (String line : lines) {
            println(line);
        }

        dispTask(2);
        inpuString = "１行目。２行目。\n３行目。４行目。\n\n５行目。";
        lines = splitWithLineBreakCodeAndPeriod(inpuString);
        for (String line : lines) {
            println(line);
        }
    }

    // 課題１

    private static List<String> splitWithLineBreakCode(String inpuString) {

        List<String> splittedLines = StringsSplitter.splitWithLineBreakCode(inpuString);
        return splittedLines;
    }

    // 課題2

    private static List<String> splitWithLineBreakCodeAndPeriod(String inpuString) {
        
        List<String> splittedLines = StringsMoreSplitter.splitWithLineBreakCodeAndPeriod(inpuString);
        return splittedLines;
    }

    // 共通関数

    private static void dispTask(int num) {
        println(MESSAGE_FORMAT_FOR_SEPARATION, num);
    }

    // 汎用関数

    private static void println(String str, Object... args) {
        System.out.println(String.format(str, args));
    }
}