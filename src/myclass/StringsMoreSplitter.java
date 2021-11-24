package myclass;

import java.util.ArrayList;
import java.util.List;

public class StringsMoreSplitter implements IStringsSplitter {

    protected static final char CHAR_BREAKCODE = '\n';
    protected static final char CHAR_PERIOD = '。';

    @Override
    public List<String> split(String splitString) {

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

    // 追加関数

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
}
