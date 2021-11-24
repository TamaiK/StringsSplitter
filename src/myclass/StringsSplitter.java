package myclass;

import java.util.ArrayList;
import java.util.List;

public class StringsSplitter implements IStringsSplitter {

    // 定数

    protected static final char CHAR_BREAKCODE = '\n';

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

    // オーバーライド関数

    @Override
    public int getSplitIndex(String splitString, int beginIndex) {

        if (isOverIndex(beginIndex, splitString)) {
            return READ_ENDED;
        }

        return getCharIndex(splitString, CHAR_BREAKCODE, beginIndex);
    }

    @Override
    public int getNextStartIndex(int endIndex, String splitString) {

        if (IStringsSplitter.isReadEnd(endIndex)) {
            return READ_ENDED;
        }

        if (isOverIndex(endIndex, splitString)) {
            return READ_ENDED;
        }

        int nextStart = endIndex + NEXT_INDEX;
        return nextStart;
    }
}
