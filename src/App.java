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
        dispLines(lines);

        dispTaskNo(2);
        inputString = "１行目。２行目。\n３行目。４行目。\n\n５行目。";
        lines = splitWithLineBreakCodeAndPeriod(inputString);
        dispLines(lines);

        dispTaskNo(3);
        inputString = "このプログラムは、文字列を指定された幅で改行するサンプルプログラムです。";
        fixedLength = 6;
        lines = splitFixedLengthWithLineBreakCodeAndPeriod(inputString, fixedLength);
        dispLines(lines);

        dispTaskNo(4);
        inputString = "このプログラムは、句読点を行頭禁則処理するサンプル。\n" + "最後の行です";
        fixedLength = 8;
        lines = splitFixedLengthJaHyphenationWithLineBreakCodeAndPeriod(inputString, fixedLength);
        dispLines(lines);
    }

    // 課題1

    private static List<String> splitWithLineBreakCode(String inpuString) {

        IStringsSplitter splitter = new StringsSplitter();
        List<String> splittedLines = splitter.split(inpuString);
        return splittedLines;
    }

    // 課題2

    private static List<String> splitWithLineBreakCodeAndPeriod(String inpuString) {

        IStringsSplitter splitter = new StringsMoreSplitter();
        List<String> splittedLines = splitter.split(inpuString);
        return splittedLines;
    }

    // 課題3

    private static List<String> splitFixedLengthWithLineBreakCodeAndPeriod(String inpuString, int fixedLength) {

        IStringsSplitterExtendFixedLength splitter = new StringsFixedLengthSplitter();
        List<String> splittedLines = splitter.split(inpuString, fixedLength);
        return splittedLines;
    }

    // 課題4

    private static List<String> splitFixedLengthJaHyphenationWithLineBreakCodeAndPeriod(String inpuString,
            int fixedLength) {

        IStringsSplitterExtendFixedLength splitter = new StringsJaHyphenationSplitter();
        List<String> splittedLines = splitter.split(inpuString, fixedLength);
        return splittedLines;
    }

    // 共通関数

    private static void dispTaskNo(int num) {
        println(MESSAGE_FORMAT_FOR_SEPARATION, num);
    }

    private static void dispLines(List<String> lines) {

        for (String line : lines) {
            println(line);
        }
    }

    // 汎用関数

    private static void println(String str, Object... args) {
        System.out.println(String.format(str, args));
    }
}