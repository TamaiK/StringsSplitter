package myclass;

import java.util.ArrayList;
import java.util.List;

public class StringsFixedLengthSplitter implements IStringsSplitterExtendFixedLength {

    private static StringsMoreSplitter splitter;

    @Override
    public List<String> split(String splitString) {

        throw new IllegalStateException();
    }

    @Override
    public List<String> split(String splitString, int fixedLength) {

        splitter = new StringsMoreSplitter();

        List<String> splittedLines = new ArrayList<>();

        int beginIndex = 0;
        int endIndex = 0;
        while (!IStringsSplitter.isReadEnd(beginIndex)) {

            endIndex = getSplitIndex(splitString, beginIndex, fixedLength);

            String splitLine = getSplitLine(splitString, beginIndex, endIndex);
            splittedLines.add(splitLine);

            beginIndex = getNextStartIndex(endIndex, splitString);
        }

        return splittedLines;
    }

    @Override
    public int getSplitIndex(String splitString, int beginIndex) {

        throw new IllegalStateException();
    }

    @Override
    public int getSplitIndex(String splitString, int beginIndex, int fixedLength) {

        if (isOverIndex(beginIndex, splitString)) {
            return READ_ENDED;
        }

        int charSplitIndex = getSplitCharIndex(splitString, beginIndex);
        int fixedLengthSplitIndex = getFixedLengthSplitIndex(splitString, beginIndex, fixedLength);

        int splitIndex = getFasterIndex(charSplitIndex, fixedLengthSplitIndex);

        return splitIndex;
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

    @Override
    public int getSplitCharIndex(String splitString, int beginIndex) {

        int charSplitIndex;
        int nextBreakCode = getCharIndex(splitString, CHAR_BREAKCODE, beginIndex);
        int nextPeriod = getCharIndex(splitString, CHAR_PERIOD, beginIndex);

        if (isFasterBreakCode(nextBreakCode, nextPeriod)) {
            charSplitIndex = nextBreakCode;
            return nextBreakCode;
        }

        charSplitIndex = nextPeriod + NEXT_INDEX;
        return charSplitIndex;
    }

    @Override
    public int getFixedLengthSplitIndex(String splitString, int beginIndex, int fixedLength) {

        int splitIndex = beginIndex + fixedLength;

        if (isOverIndex(splitIndex, splitString)) {
            return splitString.length();
        }

        return splitIndex;
    }

    @Override
    public int getFasterIndex(int index1, int index2) {

        if (index1 < index2) {
            return index1;
        }

        return index2;
    }

    @Override
    public boolean isFasterBreakCode(int nextBreakCode, int nextPeriod) {
        return splitter.isFasterBreakCode(nextBreakCode, nextPeriod);
    }

    @Override
    public boolean isPeriodBreak(int endIndex, String splitString) {
        return splitter.isPeriodBreak(endIndex, splitString);
    }
}
