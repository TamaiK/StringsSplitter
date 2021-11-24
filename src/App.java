import java.util.List;

import myclass.*;

public class App {

    // 定数

    private static final String MESSAGE_FORMAT_FOR_SEPARATION = "-----課題%d---------------";

    public static void main(String[] args) {
        
        String inputString;
        List<String> lines;
        int fixedLength;
        
        dispTaskNo(1);
        inputString = "１行目。\n２行目。\n３行目。\n４行目。\n\n５行目\n";
        lines = splitWithLineBreakCode(inputString);
        for (String line : lines) {
            println(line);
        }

        dispTaskNo(2);
        inputString = "１行目。２行目。\n３行目。４行目。\n\n５行目。";
        lines = splitWithLineBreakCodeAndPeriod(inputString);
        for (String line : lines) {
            println(line);
        }

        dispTaskNo(3);
        inputString = "このプログラムは、文字列を指定された幅で改行するサンプルプログラムです。";
        fixedLength = 6;
        lines = splitFixedLengthWithLineBreakCodeAndPeriod(inputString, fixedLength);
        for (String line : lines) {
            println(line);
        }

        dispTaskNo(4);
        inputString = "このプログラムは、句読点を行頭禁則処理するサンプル。\n" + "最後の行です";
        fixedLength = 8;
        lines = splitFixedLengthJaHyphenationWithLineBreakCodeAndPeriod(inputString, fixedLength);
        for (String line : lines) {
            println(line);
        }
    }

    // 課題1

    private static List<String> splitWithLineBreakCode(String inpuString) {

        List<String> splittedLines = StringsSplitter.splitWithLineBreakCode(inpuString);
        return splittedLines;
    }

    // 課題2

    private static List<String> splitWithLineBreakCodeAndPeriod(String inpuString) {
        
        List<String> splittedLines = StringsMoreSplitter.splitWithLineBreakCodeAndPeriod(inpuString);
        return splittedLines;
    }

    // 課題3

    private static List<String> splitFixedLengthWithLineBreakCodeAndPeriod(String inpuString, int fixedLength) {
        
        List<String> splittedLines = StringsFixedLengthSplitter.splitFixedLengthWithLineBreakCodeAndPeriod(inpuString, fixedLength);
        return splittedLines;
    }

    // 課題4

    private static List<String> splitFixedLengthJaHyphenationWithLineBreakCodeAndPeriod(String inpuString, int fixedLength) {
        
        List<String> splittedLines = StringsJaHyphenationSplitter.splitFixedLengthJaHyphenationWithLineBreakCodeAndPeriod(inpuString, fixedLength);
        return splittedLines;
    }

    // 共通関数

    private static void dispTaskNo(int num) {
        println(MESSAGE_FORMAT_FOR_SEPARATION, num);
    }

    // 汎用関数

    private static void println(String str, Object... args) {
        System.out.println(String.format(str, args));
    }
}