package myclass;

import java.util.List;

public class StringsSplitter extends StringsSplitterBase {

    // 定数

    protected static final char CHAR_BREAKCODE = '\n';

    public static List<String> splitWithLineBreakCode(String inpuString) {

        StringsSplitter stringsSplitter = new StringsSplitter();
        List<String> splittedLines = stringsSplitter.splitWithLine(inpuString);

        return splittedLines;
    }

    @Override
    protected int getSplitCharIndex(String inpuString, int beginIndex) {
        return getCharIndex(inpuString, CHAR_BREAKCODE, beginIndex);
    }

    @Override
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
}
