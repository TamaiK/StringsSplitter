package myclass;

import java.util.List;

public class StringsMoreSplitter extends StringsSplitter {
    
    protected static final char CHAR_PERIOD = '。';

    public static List<String> splitWithLineBreakCodeAndPeriod(String inpuString) {

        StringsMoreSplitter stringsSplitter = new StringsMoreSplitter();
        List<String> splittedLines = stringsSplitter.splitWithLine(inpuString);

        return splittedLines;
    }

    @Override
    protected int getSplitCharIndex(String inpuString, int beginIndex) {

        int nextEndIndex;
        int nextBreakCode = getCharIndex(inpuString, CHAR_BREAKCODE, beginIndex);
        int nextPeriod = getCharIndex(inpuString, CHAR_PERIOD, beginIndex);

        if (isFasterBreakCode(nextBreakCode, nextPeriod)) {
            nextEndIndex = nextBreakCode;
            return nextBreakCode;
        }

        nextEndIndex = nextPeriod + NEXT_INDEX;
        return nextEndIndex;
    }

    protected final boolean isFasterBreakCode(int nextBreakCode, int nextPeriod) {
        return nextBreakCode < nextPeriod;
    }

    @Override
    protected int getNextStartIndex(int endIndex, String inpuString) {

        if (isReadEnd(endIndex)) {
            return READ_ENDED;
        }

        int nextStart = endIndex;

        if (isOverIndex(endIndex, inpuString)) {

            if(isPeriodBreak(endIndex, inpuString)){
                return nextStart;
            }

            return READ_ENDED;
        }

        if (getChar(inpuString, endIndex) == CHAR_BREAKCODE) {
            nextStart += NEXT_INDEX;
        }

        return nextStart;
    }

    protected final boolean isPeriodBreak(int endIndex, String inpuString) {

        int beforeIndex = endIndex - NEXT_INDEX;

        if (isOverIndex(beforeIndex, inpuString)) {
            return false;
        }

        return getChar(inpuString, beforeIndex) == CHAR_PERIOD;
    }
}
