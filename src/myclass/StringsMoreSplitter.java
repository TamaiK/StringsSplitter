package myclass;

import java.util.ArrayList;
import java.util.List;

public class StringsMoreSplitter implements IStringsSplitter {

    private static StringsSplitter splitter;

    @Override
    public List<String> split(String splitString) {

        splitter = new StringsSplitter();

        List<String> splittedLines = new ArrayList<>();

        int beginIndex = 0;
        int endIndex = 0;
        while (!IStringsSplitter.isReadEnd(beginIndex)) {

            endIndex = getSplitIndex(splitString, beginIndex);

            String splitLine = getSplitLine(splitString, beginIndex, endIndex);
            splittedLines.add(splitLine);

            beginIndex = getNextStartIndex(endIndex, splitString);
        }

        return splittedLines;
    }

    @Override
    public int getSplitIndex(String splitString, int beginIndex) {

        if (isOverIndex(beginIndex, splitString)) {
            return READ_ENDED;
        }

        int nextEndIndex;
        int nextBreakCode = getCharIndex(splitString, CHAR_BREAKCODE, beginIndex);
        int nextPeriod = getCharIndex(splitString, CHAR_PERIOD, beginIndex);

        if (isFasterBreakCode(nextBreakCode, nextPeriod)) {
            nextEndIndex = nextBreakCode;
            return nextBreakCode;
        }

        nextEndIndex = nextPeriod + NEXT_INDEX;
        return nextEndIndex;
    }

    @Override
    public int getNextStartIndex(int endIndex, String splitString) {

        if (IStringsSplitter.isReadEnd(endIndex)) {
            return READ_ENDED;
        }

        int nextStart = endIndex;

        if (isOverIndex(endIndex, splitString)) {

            if (isPeriodBreak(endIndex, splitString)) {
                return nextStart;
            }

            return READ_ENDED;
        }

        if (getChar(splitString, endIndex) == CHAR_BREAKCODE) {
            nextStart += NEXT_INDEX;
        }

        return nextStart;
    }

    @Override
    public String getSplitLine(String splitString, int beginIndex, int endIndex) {
        return splitter.getSplitLine(splitString, beginIndex, endIndex);
    }

    @Override
    public boolean isOverIndex(int index, String splitString) {
        return splitter.isOverIndex(index, splitString);
    }

    @Override
    public char getChar(String splitString, int endIndex) {
        return splitter.getChar(splitString, endIndex);
    }

    @Override
    public int getCharIndex(String splitString, char splitChar, int index) {
        return splitter.getCharIndex(splitString, splitChar, index);
    }

    // 追加関数

    public final boolean isFasterBreakCode(int nextBreakCode, int nextPeriod) {
        return nextBreakCode < nextPeriod;
    }

    public final boolean isPeriodBreak(int endIndex, String splitString) {

        int beforeIndex = endIndex - NEXT_INDEX;

        if (isOverIndex(beforeIndex, splitString)) {
            return false;
        }

        return getChar(splitString, beforeIndex) == CHAR_PERIOD;
    }
}
