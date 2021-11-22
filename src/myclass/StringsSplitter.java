package myclass;

import java.util.ArrayList;
import java.util.List;

public class StringsSplitter {

    // 定数

    protected static final int READ_ENDED = -1;
    protected static final int MISSING_INDEX = -1;
    protected static final int NEXT_INDEX = 1;

    protected static final char CHAR_BREAKCODE = '\n';

    protected static final String STRING_BLANK = "";

    public static List<String> splitWithLineBreakCode(String inpuString) {

        StringsSplitter stringsSplitter = new StringsSplitter();

        List<String> splittedLines = stringsSplitter.splitWithLine(inpuString);

        return splittedLines;
    }

    protected final List<String> splitWithLine(String inpuString) {

        List<String> splittedLines = new ArrayList<>();

        int beginIndex = 0;
        int endIndex = 0;
        while (!isReadEnd(beginIndex)) {

            endIndex = getSplitIndex(inpuString, beginIndex);

            String splitLine = getSplitLine(inpuString, beginIndex, endIndex);
            splittedLines.add(splitLine);

            beginIndex = getNextStartIndex(endIndex, inpuString);
        }

        return splittedLines;
    }

    protected final boolean isReadEnd(int splitIndex) {
        return splitIndex <= READ_ENDED;
    }

    protected final int getSplitIndex(String inpuString, int beginIndex) {

        if (isOverIndex(beginIndex, inpuString)) {
            return READ_ENDED;
        }

        int nextEndIndex = getSplitCharIndex(inpuString, beginIndex);

        return nextEndIndex;
    }

    protected int getSplitCharIndex(String inpuString, int beginIndex) {
        return getCharIndex(inpuString, CHAR_BREAKCODE, beginIndex);
    }

    protected final String getSplitLine(String inpuString, int beginIndex, int endIndex) {

        if (endIndex == READ_ENDED) {
            return STRING_BLANK;
        }

        return inpuString.substring(beginIndex, endIndex);
    }

    protected int getNextStartIndex(int endIndex, String inpuString) {

        if (isReadEnd(endIndex)) {
            return READ_ENDED;
        }

        if (isOverIndex(endIndex, inpuString)) {
            return READ_ENDED;
        }

        int nextStart = endIndex + NEXT_INDEX;
        return nextStart;
    }

    protected final boolean isOverIndex(int index, String inpuString) {
        return index >= inpuString.length();
    }

    protected final int getCharIndex(String inpuString, char splitChar, int beginIndex) {
        
        int splitIndex = inpuString.indexOf(splitChar, beginIndex);
        if (splitIndex == MISSING_INDEX) {
            splitIndex = inpuString.length();
        }
        
        return splitIndex;
    }
}
