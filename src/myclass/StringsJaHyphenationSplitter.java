package myclass;

import java.util.ArrayList;
import java.util.List;

public class StringsJaHyphenationSplitter implements IStringsSplitterExtendFixedLength {

    protected static final char CHAR_BREAKCODE = '\n';
    protected static final char CHAR_PERIOD = '。';
    protected static final char CHAR_COMMA = '、';

    @Override
    public List<String> split(String splitString) {

        throw new IllegalStateException();
    }

    @Override
    public List<String> split(String splitString, int fixedLength) {

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

    // 追加関数

    protected int getSplitCharIndex(String splitString, int beginIndex) {

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

    protected int getFixedLengthSplitIndex(String splitString, int beginIndex, int fixedLength) {

        int splitIndex = beginIndex + fixedLength;

        if (isOverIndex(splitIndex, splitString)) {
            return splitString.length();
        }

        if (isJaHyphenation(splitIndex, splitString)) {
            splitIndex += NEXT_INDEX;
        }

        return splitIndex;
    }

    protected final boolean isFasterBreakCode(int nextBreakCode, int nextPeriod) {
        return nextBreakCode < nextPeriod;
    }

    protected final boolean isPeriodBreak(int endIndex, String splitString) {

        int beforeIndex = endIndex - NEXT_INDEX;

        if (isOverIndex(beforeIndex, splitString)) {
            return false;
        }

        return getChar(splitString, beforeIndex) == CHAR_PERIOD;
    }

    protected boolean isJaHyphenation(int splitIndex, String splitString) {

        char splitChar = splitString.charAt(splitIndex);

        if (splitChar == CHAR_COMMA || splitChar == CHAR_PERIOD) {
            return true;
        }

        return false;
    }
}
